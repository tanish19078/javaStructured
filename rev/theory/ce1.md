# ☕ Java Test Study Guide

> **Platform:** TestPad
> Covers: Java fundamentals through Object-Oriented basics

---

## Table of Contents

1. [Java Introduction — JDK, JRE, JVM](#1-java-introduction--jdk-jre-jvm)
2. [Data Types](#2-data-types)
3. [Variables](#3-variables)
4. [Operators](#4-operators)
5. [Input/Output and Comments](#5-inputoutput-and-comments)
6. [Control Statements](#6-control-statements)
7. [Loops](#7-loops)
8. [Methods and Parameter Passing](#8-methods-and-parameter-passing)
9. [Arrays](#9-arrays)
10. [Strings](#10-strings)
11. [Classes and Objects](#11-classes-and-objects)
12. [Constructors and `this` Keyword](#12-constructors-and-this-keyword)

---

## 1. Java Introduction — JDK, JRE, JVM

### What is Java?

Java is a **high-level, object-oriented, platform-independent** programming language. The key idea behind Java is **"Write Once, Run Anywhere" (WORA)** — you write your code once, and it runs on any device that has a JVM.

### How Java Code Runs

```
YourCode.java  →  javac (compiler)  →  YourCode.class (bytecode)  →  JVM (runs it)
```

1. You write a `.java` file (source code — human readable).
2. The **compiler (`javac`)** converts it into `.class` file (bytecode — not machine code, not human readable).
3. The **JVM** reads this bytecode and converts it to machine code that your OS understands.

This two-step process is why Java is platform-independent — bytecode is the same everywhere, the JVM handles the OS-specific translation.

### JDK vs JRE vs JVM

| Component | Full Form | What It Is | Contains |
|-----------|-----------|-----------|----------|
| **JVM** | Java Virtual Machine | The engine that actually runs your bytecode. It converts bytecode → machine code at runtime. | Interpreter, JIT Compiler, Garbage Collector |
| **JRE** | Java Runtime Environment | Everything needed to **run** a Java program. | JVM + core libraries (like `java.lang`, `java.util`) |
| **JDK** | Java Development Kit | Everything needed to **develop + run** Java programs. | JRE + `javac` compiler + debugger + dev tools |

**Think of it as nesting dolls:**

```
JDK  ⊃  JRE  ⊃  JVM
```

- If you only want to **run** Java programs → you need JRE
- If you want to **write and compile** Java programs → you need JDK

### Key Points for the Test

- Java is **compiled AND interpreted** — compiled to bytecode, then interpreted/JIT-compiled by JVM.
- **Platform independence** comes from bytecode + JVM, not from the source code itself.
- The JVM itself **is platform-dependent** (there's a different JVM for Windows, Mac, Linux) — but it makes your code platform-independent.
- **Garbage Collection** is handled by the JVM automatically — you don't manually free memory in Java.

### Minimal Java Program Structure

```java
public class HelloWorld {           // class name must match filename
    public static void main(String[] args) {   // entry point
        System.out.println("Hello, World!");
    }
}
```

- `public class HelloWorld` — every Java file has at least one class, and the public class name **must match the filename** (`HelloWorld.java`).
- `public static void main(String[] args)` — the **main method** is where execution begins. The JVM looks for exactly this signature.
  - `public` → accessible from anywhere
  - `static` → can run without creating an object
  - `void` → returns nothing
  - `String[] args` → command-line arguments

---

## 2. Data Types

Java is a **statically typed** language — every variable must have a declared type, and that type **cannot change** once declared.

### Primitive Data Types (8 total)

These store actual values directly in memory.

| Type | Size | Default | Range | Use Case |
|------|------|---------|-------|----------|
| `byte` | 1 byte | 0 | -128 to 127 | Saving memory in large arrays |
| `short` | 2 bytes | 0 | -32,768 to 32,767 | Rarely used |
| `int` | 4 bytes | 0 | -2.1B to 2.1B | **Default choice for whole numbers** |
| `long` | 8 bytes | 0L | Very large range | When `int` isn't big enough |
| `float` | 4 bytes | 0.0f | ~7 decimal digits precision | Rarely used, prefer `double` |
| `double` | 8 bytes | 0.0d | ~15 decimal digits precision | **Default choice for decimals** |
| `char` | 2 bytes | '\u0000' | 0 to 65,535 (Unicode) | Single character |
| `boolean` | 1 bit* | false | `true` or `false` | Conditions and flags |

> *boolean size is JVM-dependent, often stored as a byte.

### Non-Primitive (Reference) Types

These store **references (addresses)** pointing to objects in memory, not the actual data.

- `String`, Arrays, Classes, Interfaces
- Default value is `null`

### Type Casting

```java
// Widening (automatic) — smaller to larger, no data loss
int num = 10;
double d = num;            // int → double, automatically

// Narrowing (manual) — larger to smaller, possible data loss
double pi = 3.14;
int rounded = (int) pi;    // double → int, you must cast explicitly
                           // rounded = 3 (decimal part is lost, NOT rounded)
```

**Widening order:** `byte → short → int → long → float → double`

### Key Points for the Test

- `int` is default for integers, `double` is default for decimals.
- `float` literals need an `f` suffix: `float x = 3.14f;`
- `long` literals need an `L` suffix: `long x = 100000L;`
- `char` uses single quotes: `'A'` — `String` uses double quotes: `"hello"`
- Narrowing cast can cause **data loss** — the decimal is truncated, not rounded.

---

## 3. Variables

A variable is a **named container** that holds a value of a specific type.

### Declaration and Initialization

```java
int age;             // declaration (default value 0 for instance vars, no default for local)
age = 20;            // initialization
int marks = 95;      // declaration + initialization in one line
```

### Types of Variables

```java
public class Student {
    // 1. INSTANCE VARIABLE — belongs to each object, different for every object
    String name;
    int age;

    // 2. STATIC (CLASS) VARIABLE — belongs to the class, shared across all objects
    static String college = "ABC University";

    // 3. LOCAL VARIABLE — exists only inside a method/block, must be initialized before use
    void display() {
        int x = 10;    // local variable — dies when display() ends
        System.out.println(name + " " + x);
    }
}
```

| Type | Where Declared | Scope | Default Value |
|------|---------------|-------|---------------|
| **Local** | Inside a method/block | Only within that method/block | **No default — must initialize** |
| **Instance** | Inside class, outside methods | Entire class (per object) | Type default (0, null, false) |
| **Static** | Inside class with `static` keyword | Entire class (shared) | Type default (0, null, false) |

### Key Points for the Test

- **Local variables have no default value** — using them without initialization gives a compile error.
- Instance and static variables get default values automatically.
- `final` keyword makes a variable **constant** — once assigned, cannot change: `final int MAX = 100;`

---

## 4. Operators

### Arithmetic Operators

```java
int a = 10, b = 3;
a + b    // 13  — addition
a - b    // 7   — subtraction
a * b    // 30  — multiplication
a / b    // 3   — integer division (truncates decimal)
a % b    // 1   — modulus (remainder)
```

> **Important:** `10 / 3 = 3` (not 3.33) because both operands are `int`. To get decimal result: `10.0 / 3` or `(double) 10 / 3`.

### Relational (Comparison) Operators

```java
a == b    // false — equal to
a != b    // true  — not equal to
a > b     // true  — greater than
a < b     // false — less than
a >= b    // true  — greater than or equal
a <= b    // false — less than or equal
```

These always return a `boolean` (`true`/`false`).

### Logical Operators

```java
true && false    // false — AND (both must be true)
true || false    // true  — OR (at least one must be true)
!true            // false — NOT (flips the value)
```

**Short-circuit evaluation:** In `&&`, if left side is `false`, Java skips the right side. In `||`, if left side is `true`, Java skips the right side. This matters when the right side has side effects (like method calls).

### Assignment Operators

```java
int x = 10;
x += 5;     // x = x + 5  → 15
x -= 3;     // x = x - 3  → 12
x *= 2;     // x = x * 2  → 24
x /= 4;     // x = x / 4  → 6
x %= 4;     // x = x % 4  → 2
```

### Unary Operators

```java
int a = 5;
a++    // post-increment: uses current value, THEN adds 1
++a    // pre-increment: adds 1 FIRST, then uses new value
a--    // post-decrement
--a    // pre-decrement
```

**Classic test question:**
```java
int a = 5;
int b = a++;   // b = 5, a = 6 (uses 5 first, then increments)
int c = ++a;   // c = 7, a = 7 (increments first to 7, then uses 7)
```

### Ternary Operator

```java
// condition ? valueIfTrue : valueIfFalse
int max = (a > b) ? a : b;
```

### Operator Precedence (Simplified — Most Common)

```
Highest → Lowest:
  ()  →  unary (++, --, !)  →  * / %  →  + -  →  < > <= >=  →  == !=  →  &&  →  ||  →  = += -=
```

When in doubt, use parentheses `()` to make intent clear.

---

## 5. Input/Output and Comments

### Output

```java
System.out.println("Hello");   // prints and moves to next line
System.out.print("Hello");     // prints without newline
System.out.printf("Age: %d, Name: %s", 20, "Tanish");  // formatted output
```

Common format specifiers for `printf`:
- `%d` — integer
- `%f` — float/double (use `%.2f` for 2 decimal places)
- `%s` — string
- `%c` — character
- `%n` — newline (platform-independent)

### Input (using Scanner)

```java
import java.util.Scanner;           // must import

Scanner sc = new Scanner(System.in); // create scanner object

int age = sc.nextInt();              // reads an integer
double gpa = sc.nextDouble();        // reads a double
String word = sc.next();             // reads a single word (stops at space)
String line = sc.nextLine();         // reads entire line (including spaces)
char ch = sc.next().charAt(0);       // reads first character

sc.close();                          // good practice to close scanner
```

**Common gotcha:** After `nextInt()` or `nextDouble()`, the newline character `\n` is left in the buffer. If you call `nextLine()` right after, it reads that leftover `\n` and returns an empty string. Fix:

```java
int num = sc.nextInt();
sc.nextLine();              // consume the leftover newline
String name = sc.nextLine(); // now this works correctly
```

### Comments

```java
// Single-line comment — for quick notes

/*
   Multi-line comment
   Spans multiple lines
*/

/**
 * Javadoc comment — used for documentation
 * @param name the student's name
 * @return the greeting message
 */
```

---

## 6. Control Statements

Control statements let your program **make decisions** — run different code based on different conditions.

### if, else if, else

```java
int marks = 75;

if (marks >= 90) {
    System.out.println("Grade A");
} else if (marks >= 75) {
    System.out.println("Grade B");    // this runs
} else if (marks >= 60) {
    System.out.println("Grade C");
} else {
    System.out.println("Fail");
}
```

- Conditions are checked **top to bottom** — the first `true` condition runs, rest are skipped.
- `else` is optional — it's the fallback when nothing else matches.

### Nested if

```java
if (age >= 18) {
    if (hasID) {
        System.out.println("Entry allowed");
    } else {
        System.out.println("Bring your ID");
    }
}
```

### switch

Best when comparing **one variable** against **multiple fixed values**.

```java
int day = 3;

switch (day) {
    case 1:
        System.out.println("Monday");
        break;                          // without break, it "falls through" to next case
    case 2:
        System.out.println("Tuesday");
        break;
    case 3:
        System.out.println("Wednesday");  // this runs
        break;
    default:
        System.out.println("Other day");  // fallback (like else)
}
```

**Key rules:**
- `switch` works with: `byte`, `short`, `int`, `char`, `String`, enums. **NOT** with `long`, `float`, `double`, `boolean`.
- `break` is important — without it, execution **falls through** to the next case.
- `default` is optional but good practice.

**Fall-through example (sometimes intentional):**
```java
switch (day) {
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
        System.out.println("Weekday");    // runs for 1-5
        break;
    case 6:
    case 7:
        System.out.println("Weekend");    // runs for 6-7
        break;
}
```

---

## 7. Loops

Loops let you **repeat** a block of code multiple times.

### for loop

Use when you **know how many times** to repeat.

```java
for (int i = 0; i < 5; i++) {
    System.out.println(i);    // prints 0, 1, 2, 3, 4
}
```

Structure: `for (initialization; condition; update)`
- **initialization** runs once at the start
- **condition** is checked before each iteration — if `false`, loop stops
- **update** runs after each iteration

### while loop

Use when you **don't know how many times** but have a condition.

```java
int i = 0;
while (i < 5) {
    System.out.println(i);    // prints 0, 1, 2, 3, 4
    i++;
}
```

Condition is checked **before** each iteration — if `false` from the start, the body never runs.

### do-while loop

Use when the body must run **at least once**.

```java
int i = 0;
do {
    System.out.println(i);    // prints 0, 1, 2, 3, 4
    i++;
} while (i < 5);             // notice the semicolon
```

Condition is checked **after** each iteration — so the body always runs at least once, even if the condition is `false`.

### for-each loop (Enhanced for)

Simplified loop for iterating over arrays or collections.

```java
int[] nums = {10, 20, 30};
for (int n : nums) {
    System.out.println(n);    // prints 10, 20, 30
}
```

> You can't modify the array or access the index with for-each — use regular `for` if you need those.

### break and continue

```java
// break — exits the loop entirely
for (int i = 0; i < 10; i++) {
    if (i == 5) break;
    System.out.println(i);    // prints 0, 1, 2, 3, 4
}

// continue — skips the current iteration, moves to next
for (int i = 0; i < 5; i++) {
    if (i == 2) continue;
    System.out.println(i);    // prints 0, 1, 3, 4 (skips 2)
}
```

### Nested Loops (Pattern Printing)

```java
// Prints a right triangle pattern
for (int i = 1; i <= 4; i++) {        // rows
    for (int j = 1; j <= i; j++) {    // columns
        System.out.print("* ");
    }
    System.out.println();
}
// Output:
// *
// * *
// * * *
// * * * *
```

---

## 8. Methods and Parameter Passing

A method is a **reusable block of code** that performs a specific task. It helps break your program into smaller, manageable pieces.

### Method Syntax

```java
accessModifier returnType methodName(parameterList) {
    // body
    return value;    // if returnType is not void
}
```

### Examples

```java
// Method with no parameters, no return
static void greet() {
    System.out.println("Hello!");
}

// Method with parameters and return value
static int add(int a, int b) {
    return a + b;
}

// Calling methods
public static void main(String[] args) {
    greet();                        // prints "Hello!"
    int result = add(5, 3);         // result = 8
    System.out.println(result);
}
```

### Parameter Passing in Java

Java is **strictly pass-by-value** — always. But what this means depends on whether you're passing a primitive or an object.

#### Primitives — Pass by Value (copy of the value)

```java
static void change(int x) {
    x = 100;    // changes the local copy only
}

public static void main(String[] args) {
    int num = 5;
    change(num);
    System.out.println(num);    // still 5 — original is unaffected
}
```

The method gets a **copy** of the value. Changing the copy doesn't affect the original.

#### Objects/Arrays — Pass by Value of the Reference (copy of the address)

```java
static void modify(int[] arr) {
    arr[0] = 999;    // modifies the original array through the reference
}

public static void main(String[] args) {
    int[] nums = {1, 2, 3};
    modify(nums);
    System.out.println(nums[0]);    // 999 — original IS affected
}
```

The method gets a **copy of the reference** (the address). Both the original and the copy point to the **same object in memory**, so changes to the object's contents are visible everywhere.

But reassigning the reference itself doesn't affect the original:

```java
static void reassign(int[] arr) {
    arr = new int[]{99, 88, 77};    // points to a NEW array — original unaffected
}

public static void main(String[] args) {
    int[] nums = {1, 2, 3};
    reassign(nums);
    System.out.println(nums[0]);    // still 1
}
```

### Method Overloading

Same method name, **different parameter lists** (different number or types of parameters).

```java
static int add(int a, int b) {
    return a + b;
}

static double add(double a, double b) {    // same name, different param types
    return a + b;
}

static int add(int a, int b, int c) {      // same name, different param count
    return a + b + c;
}
```

Java picks the right method based on the arguments you pass. Return type alone is **not enough** to distinguish overloaded methods.

---

## 9. Arrays

An array is a **fixed-size, ordered collection** of elements of the **same type**.

### Declaration and Initialization

```java
// Declare and allocate
int[] nums = new int[5];             // array of 5 ints, all initialized to 0

// Declare and initialize with values
int[] marks = {90, 85, 78, 92, 88}; // size is automatically 5

// Declare separately
int[] arr;
arr = new int[]{10, 20, 30};        // must use new int[] when not inline
```

### Accessing and Modifying

```java
int[] marks = {90, 85, 78, 92, 88};

marks[0]           // 90 (first element — 0-indexed)
marks[4]           // 88 (last element)
marks[2] = 100;    // change third element to 100
marks.length       // 5 (no parentheses — it's a property, not a method)
// marks[5]        // ArrayIndexOutOfBoundsException — index 5 doesn't exist
```

### Iterating Through an Array

```java
int[] nums = {10, 20, 30, 40, 50};

// Using for loop
for (int i = 0; i < nums.length; i++) {
    System.out.println(nums[i]);
}

// Using for-each
for (int n : nums) {
    System.out.println(n);
}
```

### 2D Arrays

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

matrix[0][0]    // 1 (row 0, column 0)
matrix[1][2]    // 6 (row 1, column 2)
matrix.length   // 3 (number of rows)
matrix[0].length // 3 (number of columns in first row)

// Iterating 2D array
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();
}
```

### Key Points for the Test

- Arrays are **fixed size** — once created, size cannot change.
- Index starts at **0**, last index is `length - 1`.
- Default values: `int[]` → 0, `boolean[]` → false, `String[]` → null.
- Arrays are **objects** in Java — passed by reference value to methods.

---

## 10. Strings

A `String` in Java is a **sequence of characters**. Strings are **objects**, not primitives, and they are **immutable** — once created, a String's value cannot be changed. Any operation that seems to modify a String actually creates a new String.

### Creating Strings

```java
String s1 = "Hello";                    // string literal (stored in String Pool)
String s2 = new String("Hello");        // new object (stored in Heap)
String s3 = "Hello";                    // reuses same literal from String Pool as s1
```

### String Pool

Java maintains a special area in memory called the **String Pool**. When you create a string using a literal (`"Hello"`), Java checks if that exact string already exists in the pool. If yes, it reuses it. This saves memory.

```java
String a = "Hello";
String b = "Hello";
System.out.println(a == b);       // true — same reference in String Pool

String c = new String("Hello");
System.out.println(a == c);       // false — different objects (pool vs heap)
System.out.println(a.equals(c));  // true — same content
```

> **Use `.equals()` to compare string content, not `==`.** The `==` compares references (memory addresses), not values.

### Common String Methods

```java
String s = "Hello World";

s.length()              // 11 — number of characters
s.charAt(0)             // 'H' — character at index
s.substring(0, 5)       // "Hello" — from index 0 to 4 (end is exclusive)
s.substring(6)          // "World" — from index 6 to end
s.toLowerCase()         // "hello world"
s.toUpperCase()         // "HELLO WORLD"
s.trim()                // removes leading/trailing whitespace
s.contains("World")     // true
s.indexOf("World")      // 6 — first occurrence index (-1 if not found)
s.replace("World", "Java")  // "Hello Java"
s.equals("Hello World")     // true — content comparison
s.equalsIgnoreCase("hello world")  // true
s.startsWith("Hello")       // true
s.endsWith("World")         // true
s.isEmpty()                  // false
s.toCharArray()              // {'H','e','l','l','o',' ','W','o','r','l','d'}
```

> All these methods return **new** strings — the original `s` is never modified (immutable).

### String Concatenation

```java
String first = "Hello";
String second = "World";
String result = first + " " + second;    // "Hello World"

// With numbers
System.out.println("Age: " + 20);        // "Age: 20" — int is auto-converted to String
System.out.println(5 + 3 + " apples");   // "8 apples" — 5+3 happens first (both ints)
System.out.println("apples " + 5 + 3);   // "apples 53" — concatenation left to right
```

### Converting Between Types

```java
// int to String
String s = String.valueOf(42);       // "42"
String s2 = Integer.toString(42);    // "42"
String s3 = "" + 42;                 // "42" (quick trick)

// String to int
int n = Integer.parseInt("42");      // 42
// Integer.parseInt("abc") → NumberFormatException
```

---

## 11. Classes and Objects

### What is a Class?

A class is a **blueprint/template** that defines what properties (fields) and behaviors (methods) an object will have. Think of it like an architectural plan for a house — the plan itself is not a house, but you use it to build actual houses.

### What is an Object?

An object is an **actual instance** of a class — a concrete thing created from the blueprint with real values.

```
Class = Blueprint         Object = Actual thing
Student (class)     →     tanish (object), rahul (object)
Car (class)         →     myCar (object), yourCar (object)
```

### Defining a Class and Creating Objects

```java
// Defining the class (blueprint)
class Student {
    // Fields (properties) — what the object HAS
    String name;
    int age;
    double gpa;

    // Method (behavior) — what the object DOES
    void display() {
        System.out.println(name + ", Age: " + age + ", GPA: " + gpa);
    }
}

// Using the class (in main method)
public class Main {
    public static void main(String[] args) {
        // Creating objects using 'new' keyword
        Student s1 = new Student();      // s1 is an object of Student
        s1.name = "Tanish";              // setting field values
        s1.age = 20;
        s1.gpa = 9.2;
        s1.display();                    // calling method on object

        Student s2 = new Student();      // another separate object
        s2.name = "Rahul";
        s2.age = 21;
        s2.gpa = 8.5;
        s2.display();
    }
}
```

### What Happens in Memory

```java
Student s1 = new Student();
```

This single line does three things:
1. **`Student s1`** — declares a reference variable `s1` of type `Student` (stored in stack).
2. **`new Student()`** — creates a new Student object in heap memory.
3. **`=`** — assigns the object's address to `s1`.

`s1` doesn't hold the object itself — it holds a **reference (address)** to where the object lives in memory.

### Access Modifiers (Quick Overview)

| Modifier | Accessible From |
|----------|----------------|
| `public` | Everywhere |
| `private` | Only within the same class |
| `protected` | Same class + same package + subclasses |
| (default/no modifier) | Same class + same package |

```java
class BankAccount {
    private double balance;    // can't be accessed from outside

    public void deposit(double amount) {    // can be accessed from anywhere
        if (amount > 0) {
            balance += amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}
```

This is **encapsulation** — hiding internal data and providing controlled access through methods.

---

## 12. Constructors and `this` Keyword

### What is a Constructor?

A constructor is a **special method** that is called automatically when you create an object using `new`. Its job is to **initialize** the object's fields with values.

**Rules:**
- Constructor name **must be the same** as the class name.
- Constructor has **no return type** — not even `void`.
- Called automatically at object creation — you never call it manually.

### Types of Constructors

#### 1. Default Constructor (No-Argument)

If you don't write any constructor, Java provides a hidden **default constructor** that does nothing (sets fields to default values). But you can also write your own:

```java
class Student {
    String name;
    int age;

    // No-arg constructor
    Student() {
        name = "Unknown";
        age = 0;
        System.out.println("Object created!");
    }
}

Student s = new Student();    // prints "Object created!", name = "Unknown", age = 0
```

#### 2. Parameterized Constructor

Takes parameters to initialize fields with custom values at creation time:

```java
class Student {
    String name;
    int age;

    // Parameterized constructor
    Student(String name, int age) {
        this.name = name;    // 'this' distinguishes field from parameter
        this.age = age;
    }
}

Student s = new Student("Tanish", 20);    // name = "Tanish", age = 20
```

> **Important:** If you define ANY constructor (even parameterized), Java **no longer provides** the default no-arg constructor. If you still want a no-arg constructor, you must write it yourself.

#### 3. Constructor Overloading

Multiple constructors with **different parameter lists** — same concept as method overloading:

```java
class Student {
    String name;
    int age;
    String college;

    Student() {                              // no-arg
        this.name = "Unknown";
        this.age = 0;
        this.college = "N/A";
    }

    Student(String name, int age) {          // 2-arg
        this.name = name;
        this.age = age;
        this.college = "N/A";
    }

    Student(String name, int age, String college) {    // 3-arg
        this.name = name;
        this.age = age;
        this.college = college;
    }
}

Student s1 = new Student();                           // uses no-arg
Student s2 = new Student("Tanish", 20);               // uses 2-arg
Student s3 = new Student("Tanish", 20, "ABC Univ");   // uses 3-arg
```

### The `this` Keyword

`this` is a **reference to the current object** — the object on which the method/constructor is being called.

#### Use 1: Resolve Name Conflict Between Field and Parameter

```java
class Student {
    String name;    // field

    Student(String name) {     // parameter has same name as field
        this.name = name;      // this.name = field, name = parameter
    }
}
```

Without `this`, Java would think both `name` refer to the parameter, and the field would never get set.

#### Use 2: Call One Constructor from Another (Constructor Chaining)

```java
class Student {
    String name;
    int age;
    String college;

    Student() {
        this("Unknown", 0, "N/A");    // calls the 3-arg constructor
    }

    Student(String name, int age) {
        this(name, age, "N/A");        // calls the 3-arg constructor
    }

    Student(String name, int age, String college) {
        this.name = name;
        this.age = age;
        this.college = college;
    }
}
```

> `this()` must be the **first statement** in the constructor — you can't put anything before it.

#### Use 3: Pass Current Object as an Argument

```java
class Student {
    String name;

    Student(String name) {
        this.name = name;
    }

    void enroll(Course c) {
        c.addStudent(this);    // passes the current Student object
    }
}
```

### Key Points for the Test

- If you write **no constructor**, Java gives you a default one (no-arg, does nothing).
- If you write **any constructor**, the default one disappears.
- `this` refers to the **current object**.
- `this()` calls another constructor in the **same class** — must be the first line.
- Constructors **cannot be `static`**, `final`, or `abstract`.
- Constructors are **not inherited** by subclasses.

---

## Quick Reference Cheat Sheet

| Topic | Key Takeaway |
|-------|-------------|
| JDK vs JRE vs JVM | JDK ⊃ JRE ⊃ JVM. JDK to develop, JRE to run. |
| Data Types | 8 primitives. `int` and `double` are defaults. |
| Variables | Local vars have no default. Instance/static do. |
| Operators | `==` compares values/references. Post vs pre increment matters. |
| Input | `Scanner` class. Beware `nextLine()` after `nextInt()`. |
| if/else | Checked top to bottom. First true wins. |
| switch | Works with int/char/String. Don't forget `break`. |
| Loops | `for` = known count. `while` = condition. `do-while` = at least once. |
| Methods | Java is pass-by-value always. Objects pass copy of reference. |
| Arrays | Fixed size, 0-indexed. `.length` not `.length()`. |
| Strings | Immutable. Use `.equals()` not `==`. |
| Classes | Blueprint → Object. `new` creates objects in heap. |
| Constructors | Same name as class, no return type. Initialize objects. |
| `this` | Current object reference. Resolves naming conflicts. |

---

*Good luck on your test! 🚀*
