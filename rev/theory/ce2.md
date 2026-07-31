# ☕ Java Study Guide — CE2

> **Platform:** TestPad
> **Continues from:** `ce1.md` (fundamentals → constructors)
> **Covers:** Static members & blocks · Inheritance, Polymorphism, Abstraction, Interfaces · String / StringBuilder / comparisons · Multidimensional arrays · Packages & access modifiers
> **Format note:** Written for MCQs — every section ends with traps and output-prediction cases, because that's where marks are actually lost.

---

## Table of Contents

1. [Static Members and Blocks](#1-static-members-and-blocks)
2. [Initialization Order — The Master Sequence](#2-initialization-order--the-master-sequence)
3. [Inheritance](#3-inheritance)
4. [Polymorphism](#4-polymorphism)
5. [Abstraction and Abstract Classes](#5-abstraction-and-abstract-classes)
6. [Interfaces](#6-interfaces)
7. [String Class — Deep Dive](#7-string-class--deep-dive)
8. [StringBuilder and StringBuffer](#8-stringbuilder-and-stringbuffer)
9. [String Comparison — `==` vs `equals` vs `compareTo`](#9-string-comparison----vs-equals-vs-compareto)
10. [Multidimensional Arrays](#10-multidimensional-arrays)
11. [Packages](#11-packages)
12. [Access Modifiers](#12-access-modifiers)
13. [Quick Reference Cheat Sheet](#13-quick-reference-cheat-sheet)
14. [Rapid-Fire MCQ Traps](#14-rapid-fire-mcq-traps)

---

## 1. Static Members and Blocks

### The One Idea Behind `static`

`static` means **"belongs to the class, not to any object."**

Everything else about static follows from that single sentence. There is exactly **one copy** of a static member in memory, created when the class is loaded, and it exists whether you create zero objects or a thousand.

```
NON-STATIC (instance)          STATIC (class-level)
─────────────────────          ────────────────────
One copy PER OBJECT            ONE copy for the whole class
Needs an object to access      Accessed via the class name
Dies when object is GC'd       Lives as long as the class is loaded
Stored in Heap                 Stored in Method Area / Metaspace
```

### 1.1 Static Variables (Class Variables)

```java
class Student {
    String name;                          // instance — one per object
    static String college = "ABC Univ";   // static — ONE, shared by all objects
    static int totalStudents = 0;         // shared counter

    Student(String name) {
        this.name = name;
        totalStudents++;                  // every object increments the SAME variable
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Tanish");
        Student s2 = new Student("Rahul");

        System.out.println(Student.totalStudents);   // 2  ← preferred access
        System.out.println(s1.totalStudents);        // 2  ← works, but bad style
        System.out.println(s2.totalStudents);        // 2  ← same variable!

        Student.college = "XYZ Univ";                // change once...
        System.out.println(s1.college);              // XYZ Univ ← ...visible everywhere
        System.out.println(s2.college);              // XYZ Univ
    }
}
```

**Why the counter works:** `totalStudents` is not copied into each `Student`. All three references point to the same slot in the Method Area.

**Access rule:** you *can* write `s1.totalStudents`, and Java allows it, but the compiler silently rewrites it to `Student.totalStudents`. This causes a famous trap:

```java
Student s = null;
System.out.println(s.totalStudents);   // prints 2 — NO NullPointerException!
```

Because the reference is never actually dereferenced — only its *type* is used to find the class.

### 1.2 Static Methods

```java
class MathUtil {
    static int square(int n) {         // no object needed
        return n * n;
    }
}

System.out.println(MathUtil.square(5));   // 25 — called on the class
```

**Hard rules for static methods:**

| Rule | Why |
|------|-----|
| Cannot use `this` | `this` means "current object" — a static method has no current object |
| Cannot use `super` | Same reason — no object, no parent object |
| Cannot **directly** access instance variables | There's no object to read them from |
| Cannot **directly** call instance methods | Same reason |
| **Can** access static variables and static methods | Both are class-level |
| Can access instance members **through an object reference** | `obj.field` is fine — you supplied the object |

```java
class Demo {
    int x = 10;                // instance
    static int y = 20;         // static

    static void staticMethod() {
        System.out.println(y);          // ✅ static → static, fine
        // System.out.println(x);       // ❌ COMPILE ERROR: non-static variable x
        // this.x = 5;                  // ❌ COMPILE ERROR: cannot use 'this'

        Demo d = new Demo();
        System.out.println(d.x);        // ✅ fine — we made an object ourselves
    }

    void instanceMethod() {
        System.out.println(x);          // ✅ instance → instance
        System.out.println(y);          // ✅ instance → static is ALWAYS allowed
    }
}
```

> **Remember the direction:** instance → static ✅ always. static → instance ❌ never (without an object).

**Why is `main` static?** Because the JVM must call it **before any object exists**. If `main` were an instance method, the JVM would have to create an object first — but it wouldn't know which constructor to use.

### 1.3 Static Blocks (Static Initializer Blocks)

```java
static {
    // code
}
```

A static block runs **exactly once**, when the class is first loaded into memory — **before** `main`, **before** any object is created, **before** any static method is called.

```java
public class Demo {
    static int x;

    static {
        System.out.println("Static block 1");
        x = 100;                      // complex static initialization
    }

    static {
        System.out.println("Static block 2");   // multiple blocks are allowed
    }

    public static void main(String[] args) {
        System.out.println("Main method, x = " + x);
    }
}
```

**Output:**
```
Static block 1
Static block 2
Main method, x = 100
```

**Key properties:**
- Runs **once per class**, not once per object.
- You can have **multiple** static blocks — they run **top to bottom in source order**.
- Static variable declarations and static blocks are executed **interleaved, in the order they appear in the file**.
- Used for: loading drivers, reading config, initialising complex static structures (arrays, maps), anything that can't be done in a single-line assignment.
- Cannot throw **checked** exceptions. If any exception escapes a static block, the JVM wraps it in **`ExceptionInInitializerError`**.

**Interleaving demo (very common MCQ):**

```java
class Test {
    static int a = print("a");
    static { System.out.println("block 1"); }
    static int b = print("b");
    static { System.out.println("block 2"); }

    static int print(String s) {
        System.out.println(s);
        return 1;
    }

    public static void main(String[] args) { System.out.println("main"); }
}
```

**Output:** `a` → `block 1` → `b` → `block 2` → `main`

### 1.4 Instance Initializer Blocks

A block **without** the `static` keyword. It runs **every time an object is created**, just before the constructor body.

```java
class Demo {
    int x;

    {                                       // instance block — no 'static'
        System.out.println("Instance block");
        x = 50;
    }

    Demo() {
        System.out.println("Constructor");
    }
}

new Demo();   // "Instance block" then "Constructor"
new Demo();   // "Instance block" then "Constructor"   ← runs again
```

**Why it exists:** code common to *all* constructors can be written once in an instance block instead of duplicated in every constructor. The compiler physically copies the block into the top of each constructor (after the `super()` call).

| | Static block | Instance block |
|---|---|---|
| Syntax | `static { }` | `{ }` |
| Runs | Once, at class load | Every object creation |
| Runs before | `main` and everything else | Constructor body |
| Can access | Static members only | Static + instance members |
| Sees `this` | ❌ No | ✅ Yes |

### 1.5 `static final` — Constants

```java
class Config {
    static final double PI = 3.14159;        // compile-time constant
    static final int MAX_USERS;              // blank final — must be set in a static block

    static {
        MAX_USERS = 100;                     // ✅ legal — assigned exactly once
    }
}
```

- Naming convention: `ALL_CAPS_WITH_UNDERSCORES`.
- `static final` = one copy, never changes = a true constant.
- A `static final` initialised with a **literal** is a *compile-time constant* — the compiler inlines its value at every usage site.

### 1.6 Static Nested Classes (Brief)

```java
class Outer {
    static int data = 30;

    static class Nested {                    // static nested class
        void show() { System.out.println(data); }   // can access static members of Outer
    }

    class Inner {                            // inner (non-static) class
        void show() { System.out.println("inner"); }
    }
}

Outer.Nested n = new Outer.Nested();          // no Outer object needed
n.show();                                     // 30

Outer o = new Outer();
Outer.Inner i = o.new Inner();                // Outer object REQUIRED
```

| | Static nested | Inner (non-static) |
|---|---|---|
| Needs outer object | ❌ No | ✅ Yes |
| Can access outer's instance members | ❌ No | ✅ Yes |
| Creation | `new Outer.Nested()` | `outerObj.new Inner()` |

### 1.7 Static Import (Brief)

```java
import static java.lang.Math.*;
import static java.lang.Math.PI;

double r = sqrt(16);        // instead of Math.sqrt(16)
System.out.println(PI);     // instead of Math.PI
```

Imports **static members**, not classes. Overuse hurts readability.

### ⚠️ MCQ Traps — Static

| Question | Answer |
|----------|--------|
| Can a static method be **overloaded**? | ✅ Yes |
| Can a static method be **overridden**? | ❌ No — it is **hidden** (see §4.5) |
| Can a **local variable** be `static`? | ❌ No — compile error |
| Can a **constructor** be `static`? | ❌ No |
| Can a class have **multiple** static blocks? | ✅ Yes — run in source order |
| Can a static block access instance variables? | ❌ No |
| Can an instance method access static variables? | ✅ Yes |
| Can a **top-level class** be `static`? | ❌ No — only nested classes can |
| Can a static method be `final`? | ✅ Yes (but pointless — it can't be overridden anyway) |
| Where are static variables stored? | Method Area / **Metaspace** (Java 8+), not the heap |
| `obj.staticVar` where `obj` is `null` | ✅ Works, no NPE |
| Java 7+: class with only a static block and no `main` | ❌ `NoSuchMethodError` — the block does **not** run. (In Java 6 and earlier it *did* print first, then error.) |

---

## 2. Initialization Order — The Master Sequence

This is the highest-yield MCQ topic in this whole unit. Memorise the sequence.

```
CLASS LOAD (happens once, ever)
  1. Parent static variables + static blocks   (in source order)
  2. Child  static variables + static blocks   (in source order)

OBJECT CREATION (happens every `new`)
  3. Parent instance variables + instance blocks (in source order)
  4. Parent constructor body
  5. Child  instance variables + instance blocks (in source order)
  6. Child  constructor body
```

**The rule in words:** *statics first and only once, parent before child, fields and init-blocks before the constructor body.*

### Worked Example

```java
class Parent {
    static { System.out.println("1. Parent static block"); }
    { System.out.println("3. Parent instance block"); }
    Parent() { System.out.println("4. Parent constructor"); }
}

class Child extends Parent {
    static { System.out.println("2. Child static block"); }
    { System.out.println("5. Child instance block"); }
    Child() { System.out.println("6. Child constructor"); }
}

public class Main {
    public static void main(String[] args) {
        new Child();
        System.out.println("--- second object ---");
        new Child();
    }
}
```

**Output:**
```
1. Parent static block
2. Child static block
3. Parent instance block
4. Parent constructor
5. Child instance block
6. Child constructor
--- second object ---
3. Parent instance block
4. Parent constructor
5. Child instance block
6. Child constructor
```

> Notice: static blocks print **only once**, even though two objects were created. Everything else repeats.

### When Is a Class Actually Loaded?

A class is initialised **lazily** — on first *active use*:

- Creating an instance (`new`)
- Calling a static method
- Accessing (or assigning) a **non-constant** static field
- It is the class containing `main`

**Not** triggered by:
- Declaring a reference (`Demo d;` alone loads nothing)
- Accessing a `static final` **compile-time constant** (the value was inlined by the compiler, so the class is never touched)

```java
class A {
    static final int CONST = 10;              // compile-time constant
    static final int RUNTIME = compute();     // NOT a compile-time constant
    static { System.out.println("A loaded"); }
    static int compute() { return 20; }
}

System.out.println(A.CONST);      // 10   — "A loaded" is NOT printed
System.out.println(A.RUNTIME);    // "A loaded" printed, then 20
```

---

## 3. Inheritance

### What It Is

Inheritance lets a class **acquire the fields and methods of another class**. It models an **IS-A** relationship and exists to enable **code reuse** and **runtime polymorphism**.

```java
class Animal {                      // superclass / parent / base class
    String name;
    void eat() { System.out.println(name + " is eating"); }
}

class Dog extends Animal {          // subclass / child / derived class
    void bark() { System.out.println(name + " says Woof"); }
}

Dog d = new Dog();
d.name = "Bruno";     // inherited field
d.eat();              // inherited method  → "Bruno is eating"
d.bark();             // own method        → "Bruno says Woof"
```

**A `Dog` IS-A `Animal`.** If that sentence sounds wrong, don't use inheritance — use composition (HAS-A) instead.

### 3.1 Types of Inheritance in Java

```
SINGLE                MULTILEVEL             HIERARCHICAL
  A                      A                        A
  ↑                      ↑                      ↗   ↖
  B                      B                     B     C
                         ↑
                         C

MULTIPLE (classes)  ❌ NOT SUPPORTED     HYBRID ❌ NOT SUPPORTED (via classes)
  A     B
   ↖   ↗
     C
```

| Type | Supported with classes? | Supported with interfaces? |
|------|------------------------|---------------------------|
| Single | ✅ Yes | ✅ Yes |
| Multilevel | ✅ Yes | ✅ Yes |
| Hierarchical | ✅ Yes | ✅ Yes |
| **Multiple** | ❌ **No** | ✅ **Yes** |
| **Hybrid** | ❌ **No** | ✅ Yes |

**Why multiple inheritance of classes is banned — the Diamond Problem:**

```
      A                 If both B and C override show(),
    ↗   ↖               which show() does D inherit?
   B     C              → Ambiguous. Java forbids it entirely.
    ↖   ↗
      D
```

Interfaces avoided this historically because they had **no method bodies** — nothing to be ambiguous about. (Java 8 `default` methods reintroduced the possibility, so Java added an explicit resolution rule — see §6.5.)

### 3.2 What Is and Isn't Inherited

| Member | Inherited? |
|--------|-----------|
| `public` methods and fields | ✅ Yes |
| `protected` methods and fields | ✅ Yes |
| default (package-private) members | ✅ Only if subclass is in the **same package** |
| `private` members | ❌ No (they exist in memory, but are not accessible) |
| **Constructors** | ❌ **Never inherited** |
| `static` members | ✅ Accessible, but shared — not "copied" |
| `final` methods | ✅ Inherited, but ❌ cannot be overridden |

> A `private` field still occupies memory in the subclass object — you just can't touch it directly. Use an inherited `public` getter.

### 3.3 The `super` Keyword

`super` is a reference to the **immediate parent class portion** of the current object. Three uses:

**Use 1 — access a parent field hidden by a child field:**

```java
class Parent { String name = "Parent"; }

class Child extends Parent {
    String name = "Child";                     // hides the parent's field

    void show() {
        System.out.println(name);              // Child
        System.out.println(this.name);         // Child
        System.out.println(super.name);        // Parent
    }
}
```

**Use 2 — call a parent method that the child has overridden:**

```java
class Parent {
    void greet() { System.out.println("Hello from Parent"); }
}

class Child extends Parent {
    @Override
    void greet() {
        super.greet();                          // reuse the parent's logic first
        System.out.println("Hello from Child");
    }
}
// Output: "Hello from Parent" then "Hello from Child"
```

**Use 3 — call a parent constructor (`super(...)`):**

```java
class Person {
    String name;
    Person(String name) {
        this.name = name;
        System.out.println("Person constructor");
    }
}

class Student extends Person {
    int roll;
    Student(String name, int roll) {
        super(name);                 // MUST be the first statement
        this.roll = roll;
        System.out.println("Student constructor");
    }
}
```

### 3.4 Constructors and Inheritance

**Every constructor's first statement is either `super(...)` or `this(...)`.** If you write neither, the compiler silently inserts a no-argument **`super()`**.

```java
class Child extends Parent {
    Child() {
        // super();   ← compiler inserts this invisibly
        System.out.println("Child");
    }
}
```

This is why the parent constructor always finishes before the child constructor body starts.

> **⚠️ The single most common inheritance compile error:**
> If the parent has **only** a parameterized constructor, the implicit `super()` has nothing to call.
>
> ```java
> class Parent {
>     Parent(String s) { }          // no no-arg constructor exists now!
> }
> class Child extends Parent {
>     Child() { }                   // ❌ COMPILE ERROR:
> }                                 // "constructor Parent in class Parent
>                                   //  cannot be applied to given types"
> ```
> **Fix:** call `super("something")` explicitly, or add a no-arg constructor to `Parent`.

**`super()` vs `this()`:**

| | `super()` | `this()` |
|---|---|---|
| Calls | Parent class constructor | Another constructor in the **same** class |
| Position | Must be first statement | Must be first statement |
| Both in one constructor? | ❌ Impossible — both demand the first line | |

### 3.5 The `Object` Class

Every class in Java implicitly extends **`java.lang.Object`**. `Object` is the root of the entire class hierarchy.

Inherited from `Object`: `toString()`, `equals()`, `hashCode()`, `getClass()`, `clone()`, `finalize()`, `wait()`, `notify()`, `notifyAll()`.

```java
class Demo { }
Demo d = new Demo();
System.out.println(d);            // Demo@1b6d3586  ← Object's default toString()
                                  // = ClassName@hexHashCode
```

### 3.6 `final` and Inheritance

```java
final class Constants { }              // ❌ cannot be extended
// class Sub extends Constants { }     → COMPILE ERROR

class Parent {
    final void show() { }              // ❌ cannot be overridden
}

final int MAX = 10;                    // ❌ value cannot be reassigned
```

| `final` on | Means |
|-----------|-------|
| variable | Value can't be reassigned (constant) |
| method | Can't be overridden by subclasses |
| class | Can't be extended (e.g. `String`, `Integer`, `Math`) |

### ⚠️ MCQ Traps — Inheritance

- `extends` is for classes; `implements` is for interfaces. A class can `extend` **exactly one** class but `implement` **many** interfaces.
- Constructors are **not** inherited — but they *are* invoked via `super()`.
- `super()` and `this()` must each be the **first statement**, so they can never coexist.
- A subclass can be in a different package and still inherit `protected` members.
- Private methods are **not** overridden — a same-named method in the child is a brand-new, unrelated method.
- `Object` is the superclass of every class, including arrays.

---

## 4. Polymorphism

**Polymorphism** = "many forms." The same method name behaves differently depending on context.

```
POLYMORPHISM
├── Compile-time (Static)  → Method OVERLOADING   → resolved by the COMPILER
└── Runtime (Dynamic)      → Method OVERRIDING    → resolved by the JVM
```

### 4.1 Compile-Time Polymorphism — Method Overloading

Same method name, **different parameter list** (different count, types, or order). Resolved entirely at compile time based on the arguments you pass.

```java
class Calculator {
    int add(int a, int b)            { return a + b; }
    int add(int a, int b, int c)     { return a + b + c; }   // different COUNT
    double add(double a, double b)   { return a + b; }       // different TYPE
    void add(String s, int n)        { }                     // different ORDER...
    void add(int n, String s)        { }                     // ...is also valid
}
```

**What counts as a valid difference:**

| Difference | Valid overload? |
|-----------|----------------|
| Number of parameters | ✅ Yes |
| Type of parameters | ✅ Yes |
| Order of parameter types | ✅ Yes |
| **Return type only** | ❌ **No — compile error** |
| **Parameter names only** | ❌ **No — compile error** |
| `static` vs non-static (same params) | ❌ No |
| Different access modifier only | ❌ No |

```java
int  f(int x) { return x; }
// double f(int x) { return x; }   ❌ COMPILE ERROR — return type is not part of the signature
```

**Overload resolution order** — the compiler tries, in this exact order:

```
1. Exact match
2. Widening primitive conversion    (int → long → float → double)
3. Autoboxing / unboxing            (int → Integer)
4. Varargs                          (int → int...)
```

```java
static void f(long x)    { System.out.println("long"); }
static void f(Integer x) { System.out.println("Integer"); }
static void f(int... x)  { System.out.println("varargs"); }

f(5);    // prints "long"  ← widening beats boxing, boxing beats varargs
```

```java
static void g(Object o) { System.out.println("Object"); }
static void g(String s) { System.out.println("String"); }

g(null);   // prints "String" ← the MOST SPECIFIC applicable type wins
```

### 4.2 Runtime Polymorphism — Method Overriding

A subclass provides its **own implementation** of a method that already exists in the parent, with the **same signature**.

```java
class Animal {
    void sound() { System.out.println("Some sound"); }
}

class Dog extends Animal {
    @Override
    void sound() { System.out.println("Woof"); }
}

class Cat extends Animal {
    @Override
    void sound() { System.out.println("Meow"); }
}

public class Main {
    public static void main(String[] args) {
        Animal a;

        a = new Dog();   a.sound();     // Woof   ← decided at RUNTIME
        a = new Cat();   a.sound();     // Meow   ← decided at RUNTIME

        Animal[] zoo = { new Dog(), new Cat(), new Animal() };
        for (Animal x : zoo) x.sound();  // Woof, Meow, Some sound
    }
}
```

This is **dynamic method dispatch**: the reference type is `Animal`, but the JVM looks at the **actual object** in the heap to pick the method.

**Rules of overriding:**

| Rule | Detail |
|------|--------|
| Method name | Must be **identical** |
| Parameter list | Must be **identical** (else it's overloading, not overriding) |
| Return type | Same, or a **subtype** (covariant return, Java 5+) |
| Access modifier | Same or **less restrictive**. `protected` → `public` ✅ ; `public` → `protected` ❌ |
| Checked exceptions | Can throw **same, narrower, or fewer** — never new/broader ones |
| Unchecked exceptions | No restriction at all |
| `final` methods | ❌ Cannot be overridden |
| `static` methods | ❌ Cannot be overridden — they are **hidden** |
| `private` methods | ❌ Not inherited, so not overridden |
| Constructors | ❌ Cannot be overridden |

**Covariant return type:**

```java
class Animal { Animal reproduce() { return new Animal(); } }
class Dog extends Animal {
    @Override
    Dog reproduce() { return new Dog(); }      // ✅ Dog IS-A Animal — legal
}
```

**`@Override` annotation:** optional, but always use it. It makes the compiler verify you actually overrode something — catching typos like `sound()` vs `Sound()` that would otherwise silently create a new method.

### 4.3 Overloading vs Overriding — The Comparison Table

| Feature | **Overloading** | **Overriding** |
|---------|----------------|----------------|
| Also called | Compile-time / Static polymorphism | Runtime / Dynamic polymorphism |
| Where | Same class (or subclass) | Subclass only |
| Inheritance needed | ❌ No | ✅ Yes |
| Parameter list | **Must differ** | **Must be identical** |
| Return type | Can be anything | Same or covariant |
| Access modifier | Anything | Same or wider |
| `private`/`static`/`final` methods | ✅ Can be overloaded | ❌ Cannot be overridden |
| Resolved by | **Compiler** (reference type) | **JVM** (object type) |
| Binding | Early / static binding | Late / dynamic binding |

### 4.4 Upcasting, Downcasting, `instanceof`

```java
// UPCASTING — child → parent. Automatic, always safe.
Animal a = new Dog();          // ✅ implicit

// DOWNCASTING — parent → child. Manual, needs a cast, can fail at runtime.
Dog d = (Dog) a;               // ✅ works — the object really IS a Dog

Animal a2 = new Animal();
Dog d2 = (Dog) a2;             // ✅ compiles, ❌ ClassCastException at RUNTIME
```

**Guard downcasts with `instanceof`:**

```java
if (a instanceof Dog) {
    Dog d = (Dog) a;
    d.bark();
}

// Java 16+ pattern matching (shorter):
if (a instanceof Dog d) {
    d.bark();
}
```

> `null instanceof AnyType` returns **`false`** — never a NullPointerException.

**What upcasting costs you:**

```java
Animal a = new Dog();
a.sound();     // ✅ Dog's version runs — the method is in Animal, so it compiles
// a.bark();   // ❌ COMPILE ERROR — bark() doesn't exist in Animal
```

> **The rule:** the **reference type** decides what you're *allowed to call* (compile time). The **object type** decides *which overridden version runs* (runtime).

### 4.5 The Three Big Traps: Fields, Statics, and Methods

Only **instance methods** are polymorphic. Fields and static methods are **not**.

```java
class Parent {
    String name = "Parent";
    void show()        { System.out.println("Parent show"); }
    static void stat() { System.out.println("Parent stat"); }
}

class Child extends Parent {
    String name = "Child";
    @Override void show() { System.out.println("Child show"); }
    static void stat()    { System.out.println("Child stat"); }
}

public class Main {
    public static void main(String[] args) {
        Parent p = new Child();

        p.show();                        // "Child show"   ← RUNTIME  (object type)
        System.out.println(p.name);      // "Parent"       ← COMPILE  (reference type)
        p.stat();                        // "Parent stat"  ← COMPILE  (reference type)
    }
}
```

| Member | Resolved by | Called | Term |
|--------|------------|--------|------|
| **Instance method** | Object type (runtime) | Child's | **Overriding** |
| **Field** | Reference type (compile time) | Parent's | **Field hiding** |
| **Static method** | Reference type (compile time) | Parent's | **Method hiding** |

Memorise this one block — it appears in almost every Java MCQ paper.

### ⚠️ MCQ Traps — Polymorphism

- Overloading is resolved by the **compiler**; overriding by the **JVM**.
- Changing **only** the return type is not overloading — it's a compile error.
- You **can** overload `main`, but the JVM only ever calls `main(String[])`.
- Overriding a method with a **more restrictive** access modifier is a compile error.
- An overriding method may throw **fewer** checked exceptions, never more.
- `static` + same signature in a subclass = **hiding**, not overriding. If one is static and the other isn't, it's a **compile error**.
- Constructors cannot be overridden but **can** be overloaded.
- `instanceof` on `null` → `false`.

---

## 5. Abstraction and Abstract Classes

### What Abstraction Means

**Abstraction = showing *what* an object does, hiding *how* it does it.**

Driving a car: you use the steering wheel and pedals (the *what*). You don't touch the fuel injection timing (the *how*). Java achieves abstraction with **abstract classes** (0–100% abstraction) and **interfaces** (100% abstraction by design).

> **Abstraction vs Encapsulation** — a classic MCQ pair:
> **Abstraction** hides *implementation complexity* — achieved with `abstract` and `interface`. It's a **design-level** concern.
> **Encapsulation** hides *data* — achieved with `private` fields + public getters/setters. It's an **implementation-level** concern.

### 5.1 Abstract Classes

```java
abstract class Shape {
    String color;                          // ✅ fields allowed

    Shape(String color) {                  // ✅ constructors allowed
        this.color = color;
    }

    abstract double area();                // abstract — NO body, ends with ;

    void describe() {                      // ✅ concrete method allowed
        System.out.println(color + " shape with area " + area());
    }
}

class Circle extends Shape {
    double r;
    Circle(String color, double r) {
        super(color);
        this.r = r;
    }
    @Override
    double area() { return Math.PI * r * r; }    // MUST implement
}

class Rectangle extends Shape {
    double w, h;
    Rectangle(String color, double w, double h) {
        super(color);
        this.w = w; this.h = h;
    }
    @Override
    double area() { return w * h; }
}

// Shape s = new Shape("red");        ❌ COMPILE ERROR — cannot instantiate
Shape s = new Circle("red", 5);       // ✅ upcasting an abstract type
s.describe();                          // red shape with area 78.53981...
```

### 5.2 The Rules

| Rule | Detail |
|------|--------|
| Cannot be instantiated | `new Shape()` → compile error |
| **Can** have constructors | Called via `super()` from the subclass |
| Can have abstract **and** concrete methods | Both, in any mix |
| Can have **zero** abstract methods | Still abstract, still uninstantiable |
| A class with ≥1 abstract method **must** be abstract | Otherwise → compile error |
| Can have instance fields, static fields, static methods, `final` methods | ✅ All allowed |
| Subclass must implement **all** inherited abstract methods... | ...or be declared `abstract` itself |
| Can have a `main` method | ✅ Yes — and it will run |

**Illegal combinations with `abstract`:**

```java
abstract final class A { }        // ❌ final = can't extend, abstract = must extend
abstract static void m();         // ❌ static can't be overridden
abstract private void m();        // ❌ private isn't inherited, so can't be implemented
abstract void m() { }             // ❌ abstract method cannot have a body
```

> **Memory hook:** `abstract` cannot pair with **`final`**, **`static`**, or **`private`** — all three block overriding, which is the entire point of `abstract`.

**Passing the buck:** an abstract subclass may leave methods unimplemented.

```java
abstract class A { abstract void x(); abstract void y(); }

abstract class B extends A {
    void x() { }                  // implements one, leaves y() abstract
}                                 // ✅ legal because B is abstract

class C extends B {
    void y() { }                  // the first CONCRETE class must finish the job
}
```

### 5.3 Anonymous Subclass (Brief)

You can't instantiate an abstract class, but you *can* instantiate an anonymous subclass on the spot:

```java
Shape s = new Shape("blue") {              // creates an unnamed subclass
    @Override double area() { return 0; }
};
```

---

## 6. Interfaces

### What an Interface Is

An interface is a **pure contract**: a list of capabilities a class promises to provide. It says *what* must be done, never *how*.

```java
interface Drawable {
    int MAX = 100;                  // implicitly: public static final int MAX = 100;
    void draw();                    // implicitly: public abstract void draw();
}

class Circle implements Drawable {
    @Override
    public void draw() {            // MUST be public — the interface method is public
        System.out.println("Drawing circle");
    }
}

Drawable d = new Circle();          // interface as a reference type ✅
d.draw();
```

### 6.1 Implicit Modifiers — The Biggest Source of MCQs

Everything in an interface has modifiers you don't have to write, but which are always there:

| Declared as | Actually means |
|------------|----------------|
| `void draw();` | `public abstract void draw();` |
| `int MAX = 100;` | `public static final int MAX = 100;` |

**Consequences you must know:**

1. **Interface fields are constants.** They are `final`, so `MAX = 200;` is a compile error. They must be **initialised at declaration** (no blank finals).
2. **Interface fields are `static`.** Access them as `Drawable.MAX` — no object needed.
3. **Interface methods are `public`.** So an implementing class **must** declare them `public` — using default or `protected` access is a compile error ("attempting to assign weaker access privileges").
4. Interfaces **cannot** have instance fields, constructors, or instance initializer blocks.

```java
class Circle implements Drawable {
    void draw() { }      // ❌ COMPILE ERROR: attempting to assign weaker access
}                        //    (default access is weaker than public)
```

### 6.2 Interface Evolution by Java Version

| Java version | What interfaces gained |
|-------------|----------------------|
| ≤ 7 | Only `public abstract` methods + `public static final` fields |
| **8** | **`default` methods** and **`static` methods** (with bodies) |
| **9** | **`private`** and **`private static`** methods (helpers for default methods) |

```java
interface Vehicle {
    void start();                                        // abstract

    default void honk() {                                // Java 8 — has a body
        log("Beep beep!");                               //   inherited by implementors
    }

    static Vehicle create() {                            // Java 8 — belongs to the interface
        return new Car();                                //   NOT inherited by implementors
    }

    private void log(String msg) {                       // Java 9 — internal helper
        System.out.println("[LOG] " + msg);
    }
}

class Car implements Vehicle {
    public void start() { System.out.println("Car starting"); }
    // honk() comes for free — no need to implement it
}

Car c = new Car();
c.honk();                      // "Beep beep!"      ← inherited default method
Vehicle.create();              // ✅ static method — called on the INTERFACE
// c.create();                 // ❌ static interface methods are NOT inherited
```

**Why `default` methods exist:** so new methods could be added to existing interfaces (like `Collection.stream()` in Java 8) **without breaking** the millions of classes already implementing them.

### 6.3 Multiple Inheritance with Interfaces

This is the whole reason interfaces matter for inheritance:

```java
interface Flyable  { void fly(); }
interface Swimmable { void swim(); }

class Duck implements Flyable, Swimmable {     // ✅ MULTIPLE interfaces
    public void fly()  { System.out.println("Duck flies"); }
    public void swim() { System.out.println("Duck swims"); }
}
```

**Combining `extends` and `implements`:**

```java
class Duck extends Bird implements Flyable, Swimmable { }
//          ↑ ONE class          ↑ MANY interfaces
//    'extends' must come BEFORE 'implements' — the reverse is a syntax error
```

**Interfaces extending interfaces** — and an interface *can* extend multiple:

```java
interface A { void a(); }
interface B { void b(); }
interface C extends A, B { void c(); }   // ✅ an INTERFACE can extend MANY interfaces

class Impl implements C {
    public void a() { }    // must implement all three
    public void b() { }
    public void c() { }
}
```

| Relationship | Keyword | How many? |
|-------------|---------|-----------|
| class → class | `extends` | **1 only** |
| class → interface | `implements` | many |
| interface → interface | `extends` | **many** |
| interface → class | — | ❌ impossible |

### 6.4 Abstract Class vs Interface

| Feature | **Abstract Class** | **Interface** |
|---------|-------------------|--------------|
| Keyword | `abstract class` | `interface` |
| Subclass keyword | `extends` | `implements` |
| Instantiable | ❌ No | ❌ No |
| Method types | abstract + concrete | abstract, `default`, `static`, `private` (J9+) |
| Fields | Any kind (instance, static, final, non-final) | **Only `public static final` constants** |
| Constructor | ✅ Yes | ❌ **No** |
| Instance/static init blocks | ✅ Yes | ❌ No |
| Member access modifiers | `public`/`protected`/default/`private` | `public` (+ `private` for helpers, J9+) |
| Multiple inheritance | ❌ One superclass only | ✅ Many interfaces |
| Can hold state | ✅ Yes (instance fields) | ❌ No |
| Models | **IS-A** — shared identity + code | **CAN-DO** — a capability |
| Use when | Related classes share code and state | Unrelated classes share a capability |

**Choosing:** `Dog` and `Cat` share fields and behaviour → **abstract class `Animal`**. `Bird` and `Aeroplane` are unrelated but both fly → **interface `Flyable`**.

### 6.5 The Diamond Problem with `default` Methods

Java 8's default methods reintroduced ambiguity, so Java added an explicit rule: **you must resolve it yourself.**

```java
interface A { default void hello() { System.out.println("A"); } }
interface B { default void hello() { System.out.println("B"); } }

class C implements A, B {
    // ❌ COMPILE ERROR without this — "inherits unrelated defaults for hello()"
    @Override
    public void hello() {
        A.super.hello();          // explicit disambiguation syntax
        // or B.super.hello();
        // or your own body entirely
    }
}
```

> Syntax to remember: **`InterfaceName.super.methodName()`**

**"The class always wins" rule:** if a method is inherited from both a **superclass** and an **interface default**, the **class implementation wins** — no error, no ambiguity.

### 6.6 Functional Interfaces and Marker Interfaces (Brief)

**Functional interface** — exactly **one** abstract method. Can be implemented by a lambda.

```java
@FunctionalInterface                       // optional; makes the compiler enforce the rule
interface Calculator {
    int operate(int a, int b);             // exactly ONE abstract method
    default void info() { }                // defaults and statics don't count
}

Calculator add = (a, b) -> a + b;          // lambda expression
System.out.println(add.operate(3, 4));     // 7
```

Built-in examples: `Runnable`, `Comparable`, `Comparator`, `Predicate`, `Function`.

**Marker (tagging) interface** — **zero** members. It exists purely to flag a class for special JVM/library treatment.

Examples: `Serializable`, `Cloneable`, `RandomAccess`.

### ⚠️ MCQ Traps — Abstraction & Interfaces

- Interface variables are **always** `public static final` — you can never reassign them.
- Implementing methods **must be `public`**, or it's a compile error.
- An interface **cannot** have a constructor; an abstract class **can**.
- An interface **can** have a `main` method (Java 8+ static method) and it will run.
- `static` interface methods are **not inherited** — call them on the interface name.
- A class can implement two interfaces with the **same abstract method signature** — no problem, one implementation satisfies both.
- Two conflicting **`default`** methods → compile error until you override.
- An abstract class with **no** abstract methods is still legal and still uninstantiable.
- An interface can extend **multiple** interfaces, but can never extend a class.
- `extends` must be written **before** `implements`.

---

## 7. String Class — Deep Dive

### 7.1 Immutability — And Why It Matters

`String` objects are **immutable**: once created, the character content can never change. `String` is also a **`final` class**, so it can't be subclassed.

```java
String s = "Hello";
s.concat(" World");
System.out.println(s);             // "Hello"  — unchanged! concat() returned a NEW string

s = s.concat(" World");            // reassignment is what actually changes s
System.out.println(s);             // "Hello World"
```

> **Every "modifying" String method returns a new String and leaves the original alone.** If you don't capture the return value, nothing happens.

**Why Java made String immutable:**

| Reason | Explanation |
|--------|------------|
| **String Pool works** | Sharing one `"Hello"` between variables is only safe if nobody can mutate it |
| **Security** | Usernames, file paths, and URLs can't be changed after a security check passes |
| **Thread safety** | Immutable objects are inherently safe to share between threads |
| **hashCode caching** | `String` caches its hash — legal only because the content never changes. Makes `HashMap` keys fast |
| **Class loading safety** | Class names are Strings; a mutable one could be swapped after validation |

### 7.2 The String Pool (String Constant Pool)

Java keeps a special memory area for string **literals**. Identical literals are stored **once** and shared.

```java
String s1 = "Java";                 // → String Pool
String s2 = "Java";                 // → SAME pooled object, nothing new created
String s3 = new String("Java");     // → forces a NEW object on the Heap
String s4 = new String("Java");     // → another NEW object on the Heap

System.out.println(s1 == s2);       // true  — same pooled reference
System.out.println(s1 == s3);       // false — pool vs heap
System.out.println(s3 == s4);       // false — two distinct heap objects
System.out.println(s1.equals(s3));  // true  — content is identical
```

```
   STRING POOL                    HEAP
   ┌──────────┐                ┌──────────┐  ┌──────────┐
   │  "Java"  │ ← s1, s2       │  "Java"  │  │  "Java"  │
   └──────────┘                └──────────┘  └──────────┘
                                     ↑             ↑
                                     s3            s4
```

> `new String("Java")` creates **two** objects if `"Java"` isn't pooled yet: one in the pool (for the literal) and one on the heap.

**`intern()`** manually pushes a heap string into the pool and returns the pooled reference:

```java
String s3 = new String("Java");
String s5 = s3.intern();
System.out.println(s1 == s5);       // true
```

**Compile-time constant folding** — a trap that catches almost everyone:

```java
String a = "Hello";
String b = "Hel" + "lo";              // folded to "Hello" AT COMPILE TIME → pooled
System.out.println(a == b);           // true

String part = "Hel";
String c = part + "lo";               // 'part' is a variable → computed at RUNTIME → heap
System.out.println(a == c);           // false

final String fpart = "Hel";
String d = fpart + "lo";              // 'fpart' is a final compile-time constant → folded
System.out.println(a == d);           // true
```

### 7.3 Complete String Method Reference

Assume `String s = "Hello World";` unless shown otherwise.

**Length, characters, searching**

| Method | Returns | Example → Result |
|--------|---------|-----------------|
| `length()` | `int` | `s.length()` → `11` |
| `charAt(int i)` | `char` | `s.charAt(0)` → `'H'` |
| `indexOf(String)` | `int` | `s.indexOf("o")` → `4` |
| `indexOf(String, int from)` | `int` | `s.indexOf("o", 5)` → `7` |
| `lastIndexOf(String)` | `int` | `s.lastIndexOf("o")` → `7` |
| `contains(CharSequence)` | `boolean` | `s.contains("World")` → `true` |
| `isEmpty()` | `boolean` | `"".isEmpty()` → `true` (length == 0) |
| `isBlank()` *(J11)* | `boolean` | `"   ".isBlank()` → `true` |
| `startsWith(String)` | `boolean` | `s.startsWith("Hello")` → `true` |
| `endsWith(String)` | `boolean` | `s.endsWith("World")` → `true` |
| `matches(String regex)` | `boolean` | `"abc123".matches("[a-z]+\\d+")` → `true` |

> `indexOf` returns **`-1`** when not found — not 0, not an exception.

**Extracting and transforming** (all return a **new** String)

| Method | Example → Result |
|--------|-----------------|
| `substring(int begin)` | `s.substring(6)` → `"World"` |
| `substring(int begin, int end)` | `s.substring(0, 5)` → `"Hello"` (**end exclusive**) |
| `concat(String)` | `"Ja".concat("va")` → `"Java"` |
| `replace(char, char)` | `"aaa".replace('a','b')` → `"bbb"` |
| `replace(CharSequence, CharSequence)` | `s.replace("World","Java")` → `"Hello Java"` |
| `replaceAll(String regex, String)` | `"a1b2".replaceAll("\\d","")` → `"ab"` |
| `replaceFirst(String regex, String)` | `"a1b2".replaceFirst("\\d","")` → `"ab2"` |
| `toUpperCase()` / `toLowerCase()` | `s.toUpperCase()` → `"HELLO WORLD"` |
| `trim()` | `"  hi  ".trim()` → `"hi"` (removes chars ≤ U+0020) |
| `strip()` *(J11)* | Unicode-aware version of `trim()` |
| `repeat(int n)` *(J11)* | `"ab".repeat(3)` → `"ababab"` |
| `split(String regex)` | `"a,b,c".split(",")` → `["a","b","c"]` |
| `toCharArray()` | `"abc".toCharArray()` → `['a','b','c']` |

**`substring` arithmetic** — memorise this:
```java
"Hello World".substring(0, 5)     // "Hello"  → chars at index 0,1,2,3,4
                                  // length of result = end - begin = 5 - 0 = 5
"Hello".substring(2, 2)           // ""       → empty string, NOT an error
"Hello".substring(6)              // ❌ StringIndexOutOfBoundsException
"Hello".substring(5)              // ""       → index == length is legal
```

**`split` gotcha:** trailing empty strings are removed by default.
```java
"a,b,,c,,".split(",")             // ["a", "b", "", "c"]   ← trailing empties dropped
"a,b,,c,,".split(",", -1)         // ["a", "b", "", "c", "", ""]  ← limit -1 keeps them
```

**`split` takes a REGEX**, so special characters must be escaped:
```java
"1.2.3".split(".")                // []  ← '.' means "any char" in regex! Everything matched.
"1.2.3".split("\\.")              // ["1","2","3"]  ← correct
```

**Static methods**

| Method | Example → Result |
|--------|-----------------|
| `String.valueOf(x)` | `String.valueOf(42)` → `"42"` (works for any type, `null`-safe) |
| `String.format(fmt, args)` | `String.format("%.2f", 3.14159)` → `"3.14"` |
| `String.join(delim, parts)` *(J8)* | `String.join("-", "a","b","c")` → `"a-b-c"` |

**Type conversion**

```java
// number → String
String s1 = String.valueOf(42);          // "42"    ← null-safe, preferred
String s2 = Integer.toString(42);        // "42"
String s3 = "" + 42;                     // "42"    ← quick trick

// String → number
int    n = Integer.parseInt("42");       // 42
double d = Double.parseDouble("3.14");   // 3.14
Integer boxed = Integer.valueOf("42");   // Integer object (not int)

Integer.parseInt("abc");                 // ❌ NumberFormatException
Integer.parseInt("4.2");                 // ❌ NumberFormatException (not an integer)
```

### 7.4 Concatenation Rules

```java
System.out.println(1 + 2 + "Java");       // "3Java"    ← 1+2 is arithmetic, then concat
System.out.println("Java" + 1 + 2);       // "Java12"   ← left-to-right, all concat
System.out.println(1 + 2 + "Java" + 1 + 2); // "3Java12"
System.out.println('A' + 1);              // 66         ← char + int = int arithmetic!
System.out.println("" + 'A' + 1);         // "A1"       ← empty string forces concat
System.out.println('A' + 'B');            // 131        ← 65 + 66, NOT "AB"
```

> **The rule:** `+` is evaluated **strictly left to right**. It means *concatenation* only when at least one operand is a `String`; otherwise it's arithmetic.

### 7.5 The Loop Concatenation Problem

```java
// ❌ BAD — creates 10,000 throwaway String objects
String result = "";
for (int i = 0; i < 10000; i++) {
    result += i;              // each += builds a whole NEW String
}

// ✅ GOOD — one object, mutated in place
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 10000; i++) {
    sb.append(i);
}
String result = sb.toString();
```

Because Strings are immutable, `result += i` must allocate a new String and copy every existing character. Over `n` iterations that's **O(n²)** character copies. `StringBuilder` is **O(n)**.

---

## 8. StringBuilder and StringBuffer

### 8.1 Why They Exist

`StringBuilder` is a **mutable** sequence of characters. Modifying it changes the **same object** — no new allocation, no copying.

```java
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");
System.out.println(sb);          // "Hello World" — sb ITSELF changed
```

Compare with String: `s.concat(" World")` leaves `s` alone; `sb.append(" World")` modifies `sb`.

### 8.2 Constructors and Capacity

```java
StringBuilder sb1 = new StringBuilder();          // capacity 16, length 0
StringBuilder sb2 = new StringBuilder("Hello");   // capacity = 16 + 5 = 21, length 5
StringBuilder sb3 = new StringBuilder(50);        // capacity 50, length 0
```

- **Default capacity is 16.**
- `new StringBuilder(String s)` → capacity = `s.length() + 16`.
- When full, capacity grows by the formula **`(oldCapacity * 2) + 2`**.
  → 16 → 34 → 70 → 142 → …
- **`length()`** = characters actually stored. **`capacity()`** = buffer size allocated. They are different numbers.

### 8.3 Method Reference

| Method | Effect | Example (`sb = "Hello"`) |
|--------|--------|-------------------------|
| `append(x)` | Adds to end; accepts **any** type; returns `this` | `sb.append(" World")` → `"Hello World"` |
| `insert(int i, x)` | Inserts at index `i` | `sb.insert(0, "Say ")` → `"Say Hello"` |
| `delete(int s, int e)` | Removes `[s, e)` — **end exclusive** | `sb.delete(1, 3)` → `"Hlo"` |
| `deleteCharAt(int i)` | Removes one char | `sb.deleteCharAt(0)` → `"ello"` |
| `replace(int s, int e, String)` | Replaces `[s, e)` | `sb.replace(0, 5, "Bye")` → `"Bye"` |
| `reverse()` | Reverses **in place** | `sb.reverse()` → `"olleH"` |
| `setCharAt(int i, char c)` | Replaces one char; returns **`void`** | `sb.setCharAt(0,'J')` → `"Jello"` |
| `charAt(int i)` | Reads a char | `sb.charAt(1)` → `'e'` |
| `indexOf(String)` | First index, or `-1` | `sb.indexOf("ll")` → `2` |
| `substring(int s[, int e])` | Returns a **`String`** (not a StringBuilder) | `sb.substring(0,2)` → `"He"` |
| `length()` | Character count | `5` |
| `capacity()` | Buffer size | `21` |
| `setLength(int n)` | Truncates, or pads with `' '` | `sb.setLength(3)` → `"Hel"` |
| `ensureCapacity(int n)` | Grows the buffer if needed | — |
| `toString()` | Converts to an immutable `String` | `"Hello"` |

**Method chaining** — most methods return `this`, so calls chain:

```java
StringBuilder sb = new StringBuilder("Java");
String r = sb.append(" is").append(" fun").insert(0, ">> ").reverse().toString();
System.out.println(r);          // "nuf si avaJ >>"
```

> `setCharAt()` returns `void`, so it **cannot** be chained. Common trap.

### 8.4 StringBuilder vs StringBuffer vs String

| Feature | **String** | **StringBuilder** | **StringBuffer** |
|---------|-----------|------------------|-----------------|
| Mutable | ❌ No | ✅ Yes | ✅ Yes |
| Thread-safe | ✅ (immutable) | ❌ **No** | ✅ **Yes** (`synchronized`) |
| Performance | Slowest for edits | **Fastest** | Slower than StringBuilder |
| Introduced | Java 1.0 | **Java 5** | Java 1.0 |
| Stored in | String Pool / Heap | Heap | Heap |
| `equals()` overridden | ✅ Yes (compares content) | ❌ **No** | ❌ **No** |
| Default capacity | — | 16 | 16 |
| Use when | Value never changes | Single-threaded edits (**default choice**) | Multi-threaded edits |

**API-wise `StringBuilder` and `StringBuffer` are identical** — same method names, same signatures. The only difference is that every `StringBuffer` method is `synchronized`.

> **Choosing:** rarely modified → `String`. Modified often, one thread → `StringBuilder`. Modified often, shared across threads → `StringBuffer`.

### ⚠️ The `equals()` Trap

`StringBuilder` and `StringBuffer` **do not override `equals()`**. They inherit `Object.equals()`, which compares **references**.

```java
StringBuilder a = new StringBuilder("Hi");
StringBuilder b = new StringBuilder("Hi");

System.out.println(a == b);                          // false
System.out.println(a.equals(b));                     // ❌ FALSE! (identity comparison)
System.out.println(a.toString().equals(b.toString())); // ✅ true — the correct way
```

Contrast with `String`, which **does** override `equals()` for content comparison.

---

## 9. String Comparison — `==` vs `equals` vs `compareTo`

### The Three Tools

| Operator/Method | Compares | Returns | Use for |
|----------------|----------|---------|---------|
| `==` | **References** (memory addresses) | `boolean` | "Are these literally the same object?" |
| `.equals()` | **Content**, case-sensitive | `boolean` | "Do these have the same text?" ← **use this** |
| `.equalsIgnoreCase()` | Content, case-insensitive | `boolean` | Case-insensitive equality |
| `.compareTo()` | Content **lexicographically** | `int` | Sorting / ordering |
| `.compareToIgnoreCase()` | Same, case-insensitive | `int` | Case-insensitive sorting |

```java
String a = "Java";
String b = "Java";
String c = new String("Java");
String d = "java";

a == b                    // true   — both point to the pooled literal
a == c                    // false  — pool vs heap
a.equals(b)               // true
a.equals(c)               // true   — same content
a.equals(d)               // false  — case matters
a.equalsIgnoreCase(d)     // true
```

> **Rule for the exam:** to compare string *content*, always use **`.equals()`**. `==` only accidentally works for pooled literals, and relying on that is a bug.

### `compareTo()` — What the Number Means

`s1.compareTo(s2)` returns an `int`:

| Return | Meaning |
|--------|---------|
| **`0`** | Strings are equal |
| **Negative** | `s1` comes **before** `s2` (s1 < s2) |
| **Positive** | `s1` comes **after** `s2` (s1 > s2) |

**The exact value:**
1. If the strings differ at some index, the result is `s1.charAt(i) - s2.charAt(i)` for the **first differing index**.
2. If one is a prefix of the other, the result is `s1.length() - s2.length()`.

```java
"apple".compareTo("banana")     // 'a'-'b' = 97-98 = -1
"banana".compareTo("apple")     // 'b'-'a' = 98-97 =  1
"apple".compareTo("apple")      // 0
"Apple".compareTo("apple")      // 'A'-'a' = 65-97 = -32   ← UPPERCASE sorts FIRST
"abc".compareTo("abcd")         // 3 - 4 = -1              ← prefix, length difference
"abcd".compareTo("abc")         // 4 - 3 =  1
```

> Uppercase letters (65–90) come **before** lowercase (97–122) in ASCII, so `"Zebra".compareTo("apple")` is **negative**.

### Null Safety

```java
String s = null;

s.equals("Java");                    // ❌ NullPointerException
"Java".equals(s);                    // ✅ false — safe, no exception
"Java".equals(null);                 // ✅ false

// Defensive pattern ("Yoda condition"): put the LITERAL first
if ("admin".equals(username)) { }    // safe even when username is null
```

### ⚠️ MCQ Traps — Comparison

```java
String s1 = "Java";
String s2 = "Ja" + "va";                    // compile-time constant folding
String s3 = new String("Java");
String s4 = s3.intern();
String part = "Ja";
String s5 = part + "va";                    // runtime concatenation

s1 == s2      // true   ← folded at compile time, so it's the SAME literal
s1 == s3      // false  ← heap object
s1 == s4      // true   ← intern() returns the pooled reference
s1 == s5      // false  ← built at runtime on the heap
s1.equals(s5) // true   ← content is what matters
```

- `==` on `StringBuilder` and `.equals()` on `StringBuilder` behave **identically** (both compare references).
- `"abc" == "abc"` → `true`, but never write code that depends on it.
- `compareTo` returns an `int`, not a `boolean` — `if (a.compareTo(b))` is a **compile error**.
- `switch` on `String` (Java 7+) uses `equals()` internally, and it is **case-sensitive**. `switch(null)` throws NPE.

---

## 10. Multidimensional Arrays

### 10.1 The Core Idea: Arrays of Arrays

Java has **no true 2D arrays**. A `int[][]` is a **1D array whose elements are references to other 1D arrays**. Everything else follows from that.

```
int[][] a = new int[3][4];

  a ──→ ┌───┬───┬───┐          Each cell of the outer array
        │ • │ • │ • │          holds a REFERENCE to a row.
        └─┬─┴─┬─┴─┬─┘
          │   │   └──→ [0][0][0][0]   ← a[2]
          │   └──────→ [0][0][0][0]   ← a[1]
          └──────────→ [0][0][0][0]   ← a[0]
```

### 10.2 Declaration and Initialization

```java
// All three declaration syntaxes are legal and identical:
int[][] a;
int a[][];
int[] a[];               // ✅ valid, though unusual

// 1. Allocate a rectangular array (all cells default to 0)
int[][] m = new int[3][4];            // 3 rows × 4 columns

// 2. Initialise with literal values (size inferred)
int[][] n = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// 3. Anonymous array (needed when not declaring inline)
int[][] p;
p = new int[][]{{1,2},{3,4}};

// 4. Jagged / ragged — rows created separately, with DIFFERENT lengths
int[][] jag = new int[3][];           // rows are null right now
jag[0] = new int[2];                  // row 0 has 2 columns
jag[1] = new int[4];                  // row 1 has 4 columns
jag[2] = new int[1];                  // row 2 has 1 column

// 5. Jagged with literals
int[][] jag2 = {
    {1},
    {2, 3},
    {4, 5, 6}
};
```

**The dimension rule:** when using `new`, the **leftmost dimension must be specified**; later ones may be omitted.

```java
int[][] a = new int[3][];      // ✅ legal — rows filled in later
int[][] b = new int[3][4];     // ✅ legal
int[][] c = new int[][4];      // ❌ COMPILE ERROR — cannot omit the first dimension
int[][] d = new int[][];       // ❌ COMPILE ERROR
```

### 10.3 Accessing Elements and Lengths

```java
int[][] m = {
    {1, 2, 3},
    {4, 5},
    {6, 7, 8, 9}
};

m[0][0]           // 1  → row 0, column 0
m[2][3]           // 9
m[1][2]           // ❌ ArrayIndexOutOfBoundsException — row 1 has only 2 elements

m.length          // 3  → number of ROWS (the outer array)
m[0].length       // 3  → columns in row 0
m[1].length       // 2  → columns in row 1  ← rows can DIFFER
m[2].length       // 4
```

> **`arr.length` is the number of rows. `arr[i].length` is the number of columns in row `i`.** Never assume `arr[0].length` applies to all rows.

### 10.4 Traversal

```java
int[][] m = {{1,2,3},{4,5,6}};

// Standard nested for — gives you the indices
for (int i = 0; i < m.length; i++) {              // rows
    for (int j = 0; j < m[i].length; j++) {       // ← m[i].length, NOT m[0].length
        System.out.print(m[i][j] + " ");
    }
    System.out.println();
}

// Nested for-each — cleaner when you don't need indices
for (int[] row : m) {                             // each element is an int[]
    for (int val : row) {
        System.out.print(val + " ");
    }
    System.out.println();
}
```

### 10.5 Default Values and 3D Arrays

```java
int[][]     a = new int[2][2];      // all 0
double[][]  b = new double[2][2];   // all 0.0
boolean[][] c = new boolean[2][2];  // all false
String[][]  d = new String[2][2];   // all null
int[][]     e = new int[2][];       // e[0] and e[1] are BOTH null (not empty arrays!)

// 3D array — an array of 2D arrays
int[][][] cube = new int[2][3][4];  // 2 blocks × 3 rows × 4 cols = 24 ints
cube[0][1][2] = 7;
cube.length          // 2
cube[0].length       // 3
cube[0][0].length    // 4
```

### 10.6 Common Operations

```java
int[][] m = {{1,2,3},{4,5,6},{7,8,9}};

// Sum of all elements
int sum = 0;
for (int[] row : m) for (int v : row) sum += v;      // 45

// Row sums
for (int i = 0; i < m.length; i++) {
    int rs = 0;
    for (int j = 0; j < m[i].length; j++) rs += m[i][j];
    System.out.println("Row " + i + " sum = " + rs);
}

// Column sums (only valid for rectangular arrays)
for (int j = 0; j < m[0].length; j++) {
    int cs = 0;
    for (int i = 0; i < m.length; i++) cs += m[i][j];
    System.out.println("Col " + j + " sum = " + cs);
}

// Main diagonal (i == j) and anti-diagonal (i + j == n-1)
int n = m.length, d1 = 0, d2 = 0;
for (int i = 0; i < n; i++) {
    d1 += m[i][i];                  // 1 + 5 + 9 = 15
    d2 += m[i][n - 1 - i];          // 3 + 5 + 7 = 15
}

// Transpose (rows ↔ columns)
int[][] t = new int[m[0].length][m.length];
for (int i = 0; i < m.length; i++)
    for (int j = 0; j < m[i].length; j++)
        t[j][i] = m[i][j];

// Matrix multiplication: (a×b) · (b×c) = (a×c)
int[][] A = {{1,2},{3,4}}, B = {{5,6},{7,8}};
int[][] C = new int[2][2];
for (int i = 0; i < 2; i++)
    for (int j = 0; j < 2; j++)
        for (int k = 0; k < 2; k++)
            C[i][j] += A[i][k] * B[k][j];       // {{19,22},{43,50}}
```

### 10.7 The `Arrays` Utility Class

```java
import java.util.Arrays;

int[]   a = {5, 2, 8, 1};
int[][] m = {{1,2},{3,4}};

Arrays.toString(a)          // "[5, 2, 8, 1]"        ← 1D only
Arrays.toString(m)          // "[[I@1b6d3586, [I@4554617c]"  ← ❌ useless for 2D!
Arrays.deepToString(m)      // "[[1, 2], [3, 4]]"    ← ✅ correct for 2D+

Arrays.sort(a);             // a becomes {1, 2, 5, 8}  — sorts IN PLACE, returns void
Arrays.fill(a, 0);          // a becomes {0, 0, 0, 0}
Arrays.copyOf(a, 6)         // {0,0,0,0,0,0} — new array, padded with defaults
Arrays.copyOfRange(a, 1, 3) // elements [1, 3) as a new array
Arrays.binarySearch(a, 5)   // index of 5 — array MUST be sorted first
Arrays.equals(m1, m2)       // ❌ shallow — compares row REFERENCES for 2D
Arrays.deepEquals(m1, m2)   // ✅ deep — compares actual contents
```

> For anything multidimensional, use **`deepToString`** and **`deepEquals`**.

### ⚠️ MCQ Traps — Multidimensional Arrays

- `new int[][3]` is a **compile error**; `new int[3][]` is fine.
- `int[] a[];` is **valid** syntax for a 2D array.
- `arr.length` = rows; `arr[i].length` = columns of row `i`. `length` is a **field**, no parentheses.
- Rows in a jagged array can have different lengths — never hardcode `arr[0].length` for all rows.
- `new int[3][]` leaves each row as **`null`**, not an empty array. Touching `a[0][0]` gives a **NullPointerException**, not an ArrayIndexOutOfBoundsException.
- `Arrays.toString()` on a 2D array prints garbage — use `deepToString()`.
- Array size is **fixed** at creation; there's no resize.
- Negative index → `ArrayIndexOutOfBoundsException` at runtime (not a compile error).
- `new int[-1]` compiles fine but throws **`NegativeArraySizeException`** at runtime.
- Arrays are **objects** — they live on the heap and are passed by value-of-reference.

---

## 11. Packages

### 11.1 What and Why

A **package** is a named grouping of related classes and interfaces — essentially a folder with a namespace attached.

**Four reasons packages exist:**

| Purpose | Explanation |
|---------|------------|
| **Namespace management** | Two `Date` classes can coexist as `java.util.Date` and `java.sql.Date` |
| **Access control** | `default` and `protected` visibility are defined *in terms of* packages |
| **Organisation** | Thousands of classes stay navigable |
| **Reusability** | A package can be shipped as a JAR and reused |

### 11.2 Declaring a Package

```java
package com.college.student;      // MUST be the first statement in the file

import java.util.Scanner;         // imports come after the package statement

public class Student {
    // ...
}
```

**Rules:**
- The `package` statement must be the **first non-comment statement** in the file.
- A file may contain **at most one** `package` statement.
- Convention: **all lowercase**, reverse domain name — `com.company.project.module`.
- The package name **must mirror the directory structure**:
  `package com.college.student;` → file lives at `com/college/student/Student.java`
- No `package` statement → the class is in the **default (unnamed) package**. Classes in the default package **cannot be imported** by classes in named packages.

### 11.3 Compiling and Running Packaged Classes

```bash
# Compile — -d creates the directory structure automatically
javac -d . Student.java
# creates ./com/college/student/Student.class

# Run — use the FULLY QUALIFIED name, with dots (not slashes, no .class)
java com.college.student.Student
```

### 11.4 Importing

```java
// 1. Single-type import — preferred, most explicit
import java.util.Scanner;

// 2. On-demand (wildcard) import — all classes in that package
import java.util.*;

// 3. Static import — imports static MEMBERS, not classes
import static java.lang.Math.sqrt;
import static java.lang.Math.*;

// 4. No import at all — use the fully qualified name inline
java.util.Scanner sc = new java.util.Scanner(System.in);
```

**Import facts that get tested:**

- **`import java.util.*;` does NOT import sub-packages.** It gives you `java.util.Scanner` but **not** `java.util.regex.Pattern`. Package nesting is naming convention only — there is no true containment.
- **`java.lang` is imported automatically.** That's why `String`, `System`, `Math`, `Integer`, `Object`, and `Thread` need no import.
- Wildcard imports have **no runtime cost** — the compiler resolves only what you use. It's a readability choice, not a performance one.
- Importing a class does **not** import its members. `import java.lang.Math;` still requires `Math.sqrt(x)`.

**Ambiguity — a classic MCQ:**

```java
import java.util.Date;
import java.sql.Date;              // ❌ COMPILE ERROR — duplicate class Date

import java.util.*;
import java.sql.*;
Date d = new Date();               // ❌ COMPILE ERROR — "reference to Date is ambiguous"
java.util.Date d = new java.util.Date();   // ✅ fully qualified name resolves it
```

> A single-type import **wins over** a wildcard import. `import java.util.*; import java.sql.Date;` → plain `Date` means `java.sql.Date`.

### 11.5 Built-in Packages Worth Knowing

| Package | Contains | Auto-imported? |
|---------|----------|:--------------:|
| `java.lang` | `String`, `System`, `Math`, `Object`, wrapper classes, `Thread`, `Exception` | ✅ **Yes** |
| `java.util` | `Scanner`, `Arrays`, `ArrayList`, `HashMap`, `Random`, `Date`, collections | ❌ No |
| `java.io` | `File`, `FileReader`, `BufferedReader`, `InputStream`, `PrintWriter` | ❌ No |
| `java.net` | `Socket`, `URL`, `ServerSocket` | ❌ No |
| `java.sql` | `Connection`, `Statement`, `ResultSet` (JDBC) | ❌ No |
| `java.time` | `LocalDate`, `LocalDateTime`, `Duration` (Java 8+) | ❌ No |
| `java.awt` / `javax.swing` | GUI components | ❌ No |

### 11.6 File Structure Rules

- One **`public`** class (or interface) per `.java` file, **maximum**.
- The public class name **must match the filename** exactly, case included.
- A file may contain any number of **non-public** classes.
- Order in the file: `package` → `import`s → class/interface declarations. No other order compiles.

---

## 12. Access Modifiers

### 12.1 The Four Levels

Java has **four** access levels but only **three** keywords — "default" has no keyword; it's what you get by writing nothing.

| Modifier | Keyword | Scope in one line |
|----------|---------|-------------------|
| **private** | `private` | This class only |
| **default** | *(none)* | This package only — a.k.a. package-private |
| **protected** | `protected` | This package + subclasses anywhere |
| **public** | `public` | Everywhere |

### 12.2 The Access Matrix — Memorise This

| Access from ↓ | `private` | *default* | `protected` | `public` |
|--------------|:---------:|:---------:|:-----------:|:--------:|
| Same class | ✅ | ✅ | ✅ | ✅ |
| Same package, non-subclass | ❌ | ✅ | ✅ | ✅ |
| Same package, subclass | ❌ | ✅ | ✅ | ✅ |
| **Different package, subclass** | ❌ | ❌ | ✅ *(inheritance only)* | ✅ |
| Different package, non-subclass | ❌ | ❌ | ❌ | ✅ |

**Restrictiveness order (least → most visible):**
```
private  <  default  <  protected  <  public
```

Only **one row differs** between `default` and `protected` — the "different package, subclass" row. That single row is what `protected` buys you.

### 12.3 Examples

```java
package pack1;

public class A {
    private   int p = 1;    // only inside A
    /*default*/int d = 2;   // anywhere in pack1
    protected int q = 3;    // pack1 + subclasses anywhere
    public    int r = 4;    // everywhere

    void test() {
        System.out.println(p + d + q + r);   // ✅ all four — same class
    }
}
```

```java
package pack1;

class B {                    // same package, NOT a subclass
    void test() {
        A a = new A();
        // a.p;              ❌ private
        System.out.println(a.d);   // ✅ default — same package
        System.out.println(a.q);   // ✅ protected — same package
        System.out.println(a.r);   // ✅ public
    }
}
```

```java
package pack2;
import pack1.A;

class C extends A {          // different package, IS a subclass
    void test() {
        // System.out.println(p);   ❌ private
        // System.out.println(d);   ❌ default — different package
        System.out.println(q);      // ✅ protected — inherited into C
        System.out.println(r);      // ✅ public
    }
}

class D {                    // different package, NOT a subclass
    void test() {
        A a = new A();
        // a.q;              ❌ protected — not a subclass
        System.out.println(a.r);    // ✅ public only
    }
}
```

### 12.4 The `protected` Subtlety

In a **different package**, a subclass can only access a `protected` member **through inheritance** — via `this`, or through a reference of **its own type**. Not through a `Parent`-typed reference.

```java
package pack2;
import pack1.A;

class C extends A {
    void test() {
        System.out.println(this.q);       // ✅ own inherited copy
        System.out.println(q);            // ✅ same thing

        C c = new C();
        System.out.println(c.q);          // ✅ reference of subclass type

        A a = new A();
        // System.out.println(a.q);       // ❌ COMPILE ERROR — parent-typed reference
    }
}
```

> `protected` means "you may use *your own* inherited copy," not "you may inspect any Parent object."

### 12.5 Where Each Modifier Is Legal

| Applied to | `private` | *default* | `protected` | `public` |
|-----------|:---------:|:---------:|:-----------:|:--------:|
| **Top-level class / interface** | ❌ | ✅ | ❌ | ✅ |
| Fields | ✅ | ✅ | ✅ | ✅ |
| Methods | ✅ | ✅ | ✅ | ✅ |
| Constructors | ✅ | ✅ | ✅ | ✅ |
| **Nested / inner classes** | ✅ | ✅ | ✅ | ✅ |
| Local variables | ❌ *(never any modifier)* | — | ❌ | ❌ |
| Interface members | *(J9 methods only)* | ❌ | ❌ | ✅ implicit |

> **A top-level class can only be `public` or default.** `private class Foo { }` at file level is a compile error — a private top-level class would be unusable by anyone.

**A `private` constructor is legal and useful** — it prevents outside instantiation (Singleton pattern, or a utility class like `Math`):

```java
class Singleton {
    private static Singleton instance;
    private Singleton() { }                        // nobody outside can call new

    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }
}
```

### 12.6 Access Modifiers and Overriding

An overriding method **cannot reduce** visibility. It may keep it the same or widen it.

```java
class Parent { protected void show() { } }

class Child extends Parent {
    public void show() { }        // ✅ protected → public   (widening — allowed)
}

class Child2 extends Parent {
    void show() { }               // ❌ protected → default  (narrowing — COMPILE ERROR)
}                                 //    "attempting to assign weaker access privileges"
```

**Why:** the Liskov substitution principle. If `Parent p = new Child();` compiles, then `p.show()` must be callable — the child cannot silently take that away.

### 12.7 Access Modifiers and Encapsulation

Access modifiers exist so you can implement **encapsulation** — private data, public controlled access:

```java
public class BankAccount {
    private double balance;                        // 1. hide the data

    public double getBalance() {                   // 2. controlled read
        return balance;
    }

    public void deposit(double amount) {           // 3. controlled write, WITH validation
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }
}
```

Making `balance` public would allow `account.balance = -5000;` — no validation, broken invariant. That is exactly what `private` prevents.

### ⚠️ MCQ Traps — Access Modifiers

- There are **4 access levels** but only **3 keywords**.
- A **top-level class** cannot be `private` or `protected`.
- **Local variables** can never have an access modifier (only `final` is allowed).
- Interface methods are implicitly `public` — declaring them `protected` or `private` (pre-Java 9) is a compile error.
- `private` members **are** in the subclass's memory, they're just inaccessible.
- The default modifier is called **package-private**, not "friendly" and not "protected."
- Overriding can only **widen** access, never narrow it.
- `protected` in a different package works only via inheritance, not via a parent-typed reference.
- `private` constructors are perfectly legal.

---

## 13. Quick Reference Cheat Sheet

| Topic | Key Takeaway |
|-------|-------------|
| **`static`** | Belongs to the class. One copy. No `this`. static ✗→ instance; instance ✓→ static. |
| **Static block** | `static { }` — runs **once** at class load, **before** `main`, in source order. |
| **Instance block** | `{ }` — runs on **every** object creation, before the constructor body. |
| **Init order** | Parent static → Child static → Parent instance+ctor → Child instance+ctor. |
| **Inheritance** | `extends`, one class only. Constructors are **not** inherited. `super()` is implicit and must be first. |
| **`super` vs `this`** | `super` = parent portion; `this` = current object. Both `super()`/`this()` want line 1 → mutually exclusive. |
| **Multiple inheritance** | ❌ with classes (diamond problem), ✅ with interfaces. |
| **Overloading** | Same name, **different params**. Compile-time. Return type alone is not enough. |
| **Overriding** | Same signature, subclass. Runtime. Can't override `static`/`final`/`private`. |
| **The big trap** | Methods → object type (runtime). **Fields and statics → reference type (compile time).** |
| **Casting** | Upcast is automatic; downcast needs `(Type)` and can throw `ClassCastException`. Guard with `instanceof`. |
| **Abstract class** | Can't instantiate. Has constructors, fields, concrete methods. `abstract` ✗ with `final`/`static`/`private`. |
| **Interface** | Fields = `public static final`. Methods = `public abstract` (+ `default`/`static` J8, `private` J9). No constructor. |
| **Diamond defaults** | Two conflicting defaults → must override, resolve with `A.super.m()`. Class always beats interface. |
| **String** | Immutable, `final` class. Literals are pooled. Every "modifier" method returns a new String. |
| **`==` vs `equals`** | `==` compares references, `.equals()` compares content. **Always use `.equals()`.** |
| **`compareTo`** | Returns `int`: 0 equal, negative if before, positive if after. Value = char difference or length difference. |
| **StringBuilder** | Mutable, not thread-safe, fastest. Default capacity **16**, grows to `2n+2`. **`equals()` is NOT overridden.** |
| **StringBuffer** | Same API as StringBuilder, but `synchronized` (thread-safe, slower). |
| **2D arrays** | Array of arrays. `a.length` = rows, `a[i].length` = cols of row i. Rows may differ (jagged). |
| **Array `new`** | Leftmost dimension is mandatory: `new int[3][]` ✅, `new int[][3]` ❌. |
| **Arrays utility** | Use `deepToString()` / `deepEquals()` for 2D+, not `toString()` / `equals()`. |
| **Packages** | `package` is the first statement, one per file, mirrors the directory. `java.lang` is auto-imported. |
| **Imports** | `import p.*` does **not** cover sub-packages. Single-type import beats wildcard. |
| **Access** | `private < default < protected < public`. Top-level class: only `public` or default. |
| **`protected`** | Different package = accessible only through **inheritance**, not a parent-typed reference. |

---

## 14. Rapid-Fire MCQ Traps

Cover the answers and predict the output.

**1. Static vs instance access**
```java
class A {
    int x = 10;
    static void m() { System.out.println(x); }
}
```
→ **Compile error:** non-static variable `x` cannot be referenced from a static context.

**2. Static block ordering**
```java
class A {
    static { System.out.println("static"); }
    { System.out.println("instance"); }
    A()  { System.out.println("ctor"); }
    public static void main(String[] a) { new A(); new A(); }
}
```
→ `static`, `instance`, `ctor`, `instance`, `ctor` *(static prints once only)*

**3. Null static access**
```java
A a = null;
System.out.println(a.staticField);
```
→ Works fine, **no NullPointerException** (compiled to `A.staticField`).

**4. The field/method/static trap**
```java
class P { String n = "P"; void m(){print("Pm");} static void s(){print("Ps");} }
class C extends P { String n = "C"; void m(){print("Cm");} static void s(){print("Cs");} }
P p = new C();
p.m();  System.out.println(p.n);  p.s();
```
→ `Cm`, `P`, `Ps` *(method = runtime, field & static = compile time)*

**5. Missing super constructor**
```java
class P { P(int x) { } }
class C extends P { C() { } }
```
→ **Compile error** — implicit `super()` has no matching no-arg constructor in `P`.

**6. Overload resolution**
```java
static void f(long x)   { print("long"); }
static void f(Integer x){ print("Integer"); }
f(5);
```
→ `long` *(widening beats autoboxing)*

**7. Return-type-only overload**
```java
int  f(int a) { return a; }
long f(int a) { return a; }
```
→ **Compile error** — return type is not part of the method signature.

**8. Narrowing access on override**
```java
class P { public void m() { } }
class C extends P { protected void m() { } }
```
→ **Compile error** — attempting to assign weaker access privileges.

**9. Interface method access**
```java
interface I { void m(); }
class C implements I { void m() { } }
```
→ **Compile error** — interface methods are implicitly `public`; `m()` must be `public`.

**10. Interface constant reassignment**
```java
interface I { int X = 10; }
class C implements I { void m() { X = 20; } }
```
→ **Compile error** — `X` is implicitly `final`.

**11. Conflicting defaults**
```java
interface A { default void m(){} }
interface B { default void m(){} }
class C implements A, B { }
```
→ **Compile error** — inherits unrelated defaults; must override and use `A.super.m()`.

**12. Abstract instantiation**
```java
abstract class A { abstract void m(); }
A a = new A();
```
→ **Compile error** — `A` is abstract; cannot be instantiated.

**13. String immutability**
```java
String s = "Hello";
s.toUpperCase();
System.out.println(s);
```
→ `Hello` *(the return value was discarded)*

**14. String pool identity**
```java
String a = "Java";
String b = new String("Java");
System.out.println(a == b);
System.out.println(a.equals(b));
```
→ `false`, `true`

**15. Compile-time folding**
```java
String a = "Java";
String b = "Ja" + "va";
System.out.println(a == b);
```
→ `true` *(folded to one literal at compile time)*

**16. Runtime concatenation**
```java
String p = "Ja";
System.out.println("Java" == p + "va");
```
→ `false` *(built on the heap at runtime)*

**17. `+` left-to-right**
```java
System.out.println(1 + 2 + "A" + 1 + 2);
System.out.println('A' + 'B');
```
→ `3A12` and `131`

**18. substring bounds**
```java
System.out.println("Hello".substring(1, 3));
System.out.println("Hello".substring(5));
System.out.println("Hello".substring(6));
```
→ `el`, `""` (empty), then **`StringIndexOutOfBoundsException`**

**19. split with a regex metacharacter**
```java
System.out.println("1.2.3".split(".").length);
```
→ `0` *(`.` is the regex "any character")*

**20. StringBuilder equals**
```java
StringBuilder a = new StringBuilder("Hi");
StringBuilder b = new StringBuilder("Hi");
System.out.println(a.equals(b));
```
→ `false` *(`equals()` is not overridden — reference comparison)*

**21. StringBuilder capacity**
```java
System.out.println(new StringBuilder("Hello").capacity());
```
→ `21` *(16 + 5)*

**22. compareTo values**
```java
System.out.println("Apple".compareTo("apple"));
System.out.println("abc".compareTo("abcd"));
```
→ `-32` *(65 − 97)* and `-1` *(3 − 4)*

**23. Null-safe equals**
```java
String s = null;
System.out.println("Java".equals(s));
System.out.println(s.equals("Java"));
```
→ `false`, then **NullPointerException**

**24. Array dimension syntax**
```java
int[][] a = new int[][3];
```
→ **Compile error** — the leftmost dimension cannot be omitted.

**25. Jagged rows are null**
```java
int[][] a = new int[3][];
System.out.println(a[0]);
System.out.println(a[0][0]);
```
→ `null`, then **NullPointerException**

**26. Row lengths**
```java
int[][] m = {{1,2,3},{4,5}};
System.out.println(m.length + " " + m[0].length + " " + m[1].length);
```
→ `2 3 2`

**27. Printing a 2D array**
```java
int[][] m = {{1,2},{3,4}};
System.out.println(Arrays.toString(m));
```
→ Something like `[[I@1b6d3586, [I@4554617c]` — use **`Arrays.deepToString(m)`** for `[[1, 2], [3, 4]]`.

**28. Negative array size**
```java
int[] a = new int[-1];
```
→ Compiles fine; throws **`NegativeArraySizeException`** at runtime.

**29. Wildcard import scope**
```java
import java.util.*;
Pattern p;                    // java.util.regex.Pattern
```
→ **Compile error** — `*` does not include sub-packages.

**30. Ambiguous import**
```java
import java.util.*;
import java.sql.*;
Date d = new Date();
```
→ **Compile error** — reference to `Date` is ambiguous.

**31. Top-level access modifier**
```java
private class A { }
```
→ **Compile error** — a top-level class may only be `public` or default.

**32. protected across packages**
```java
// in pack2, class C extends pack1.A
A a = new A();
System.out.println(a.protectedField);
```
→ **Compile error** — a parent-typed reference doesn't grant `protected` access outside the package.

---

*Read the trap tables twice — that's where the marks are. Good luck! 🚀*
