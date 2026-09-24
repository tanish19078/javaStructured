# Java Q1-Q15 — Simple Solutions

> No BufferedReader, no StringBuilder, no StringTokenizer. Just Scanner and basic loops.

---

## Q1 — Uppercase and word count

```java
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        System.out.println(s.toUpperCase());
        System.out.println(s.trim().split("\\s+").length);
        sc.close();
    }
}
```

---

## Q2 — Maximum in matrix

```java
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int val = sc.nextInt();
                if (val > max) {
                    max = val;
                }
            }
        }

        System.out.println(max);
        sc.close();
    }
}
```

---

## Q3 — Vehicle interface / fuel efficiency

```java
import java.util.Scanner;

interface Vehicle {
    double fuelEfficiency(double distance, double fuel);
}

class Car implements Vehicle {
    public double fuelEfficiency(double distance, double fuel) {
        return distance / fuel;
    }
}

class Bike implements Vehicle {
    public double fuelEfficiency(double distance, double fuel) {
        return distance / fuel;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        double distance = sc.nextDouble();
        double fuel = sc.nextDouble();

        Vehicle v;
        if (choice == 1) {
            v = new Car();
        } else {
            v = new Bike();
        }

        System.out.printf("%.2f", v.fuelEfficiency(distance, fuel));
        sc.close();
    }
}
```

---

## Q4 — Count a character

```java
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char ch = sc.nextLine().charAt(0);

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ch) {
                count++;
            }
        }

        System.out.println(count);
        sc.close();
    }
}
```

---

## Q5 — Static Visitor counter

```java
import java.util.Scanner;

class Visitor {
    static int count;

    Visitor() {
        count++;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            new Visitor();
        }

        System.out.println(Visitor.count);
        sc.close();
    }
}
```

---

## Q6 — Employee salary polymorphism

```java
import java.util.Scanner;

class Employee {
    double salary;

    Employee(double salary) {
        this.salary = salary;
    }

    double calculateSalary() {
        return salary;
    }
}

class Manager extends Employee {
    Manager(double salary) {
        super(salary);
    }

    double calculateSalary() {
        return salary + 5000;
    }
}

class Developer extends Employee {
    int projects;

    Developer(double salary, int projects) {
        super(salary);
        this.projects = projects;
    }

    double calculateSalary() {
        return salary + 2000 * projects;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        double salary = sc.nextDouble();

        Employee e;
        if (choice == 1) {
            e = new Manager(salary);
        } else {
            int projects = sc.nextInt();
            e = new Developer(salary, projects);
        }

        System.out.println(e.calculateSalary());
        sc.close();
    }
}
```

---

## Q7 — Palindrome check

```java
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().toLowerCase();

        String reversed = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            reversed += s.charAt(i);
        }

        if (s.equals(reversed)) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }
        sc.close();
    }
}
```

---

## Q8 — Sum of matrix elements

```java
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();

        int sum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sum += sc.nextInt();
            }
        }

        System.out.println(sum);
        sc.close();
    }
}
```

---

## Q9 — Abstract Shape area

```java
import java.util.Scanner;

abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double area() {
        return 3.14 * radius * radius;
    }
}

class Rectangle extends Shape {
    double length, breadth;

    Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    double area() {
        return length * breadth;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        Shape s;
        if (choice == 1) {
            double radius = sc.nextDouble();
            s = new Circle(radius);
        } else {
            double length = sc.nextDouble();
            double breadth = sc.nextDouble();
            s = new Rectangle(length, breadth);
        }

        System.out.printf("%.2f", s.area());
        sc.close();
    }
}
```

---

## Q10 — Static Student counter

```java
import java.util.Scanner;

class Student {
    static int count;

    Student() {
        count++;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            new Student();
        }

        System.out.println("Total Students: " + Student.count);
        sc.close();
    }
}
```

---

## Q11 — Animal runtime polymorphism

```java
import java.util.Scanner;

class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("The dog barks");
    }
}

class Cat extends Animal {
    void sound() {
        System.out.println("The cat meows");
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        Animal a;
        if (s.equals("Dog")) {
            a = new Dog();
        } else if (s.equals("Cat")) {
            a = new Cat();
        } else {
            a = new Animal();
        }

        a.sound();
        sc.close();
    }
}
```

---

## Q12 — Exact username match

```java
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String first = sc.nextLine();
        String second = sc.nextLine();

        if (first.equals(second)) {
            System.out.println("Match");
        } else {
            System.out.println("No Match");
        }
        sc.close();
    }
}
```

---

## Q13 — Vehicle start/stop interface

```java
import java.util.Scanner;

interface Vehicle {
    void start();
    void stop();
}

class Car implements Vehicle {
    public void start() {
        System.out.println("Car started");
    }

    public void stop() {
        System.out.println("Car stopped");
    }
}

class Bike implements Vehicle {
    public void start() {
        System.out.println("Bike started");
    }

    public void stop() {
        System.out.println("Bike stopped");
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        Vehicle v = null;
        if (s.equals("Car")) {
            v = new Car();
        } else if (s.equals("Bike")) {
            v = new Bike();
        }

        if (v == null) {
            System.out.println("Invalid vehicle type");
        } else {
            v.start();
            v.stop();
        }
        sc.close();
    }
}
```

---

## Q14 — Reverse a string

```java
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        String reversed = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            reversed += s.charAt(i);
        }

        System.out.println(reversed);
        sc.close();
    }
}
```

---

## Q15 — Row-wise matrix sum

```java
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();

        for (int i = 0; i < r; i++) {
            int sum = 0;
            for (int j = 0; j < c; j++) {
                sum += sc.nextInt();
            }
            System.out.println(sum);
        }
        sc.close();
    }
}
```
