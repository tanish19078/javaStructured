// inheritance is also known as IS A relationship
// parent class default constructor will always run and will run prior to child object creation 
// whenever inheritance is used the class we create object of will call its default constructor
// change default to parameterised constructor in both classes and check if its workung
class X {
    String name;
    int age;
    int id;

    // 1. Parent Parameterized Constructor
    X(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
        System.out.println("1. Parent (X) Parameterized Constructor executed.");
    }

    void fun() {
        System.out.println("yeee");
    }
}

class Employee extends X {
    
    // 2. Child Parameterized Constructor
    Employee(String name, int age, int id) {
        // Essential step: passing arguments to the parent constructor
        super(name, age, id); 
        
        System.out.println("2. Child (Employee) Parameterized Constructor executed.");
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("ID: " + this.id);
    }

    // Method Overloading
    void getinfo(int age, String name) {
        System.out.println("Age from overloaded method: " + this.age);
    }

    void getinfo(String name, int age, int id) {
        System.out.println("Name/Age/ID method called.");
    }
}

class Student {
    String name;
    int marks;
    int id;

    Student(String name, int marks, int id) {
        this.name = name;
        this.marks = marks;
        this.id = id;
        System.out.println("Student Name: " + this.name);
        System.out.println("Student Marks: " + this.marks);
        System.out.println("Student ID: " + this.id);
    }
}

public class day11_inheritance {
    public static void main(String[] args) {
        // Creating parent object
        X a = new X("Parent Asset", 50, 999);
        a.fun();

        System.out.println("-----------------------------------");
        
        // Creating child object triggers parent constructor via super() first, then child constructor.
        Employee emp = new Employee("John", 36, 102);
        
        System.out.println("-----------------------------------");
        
        // Testing overloaded method
        emp.getinfo(36, "John");

        System.out.println("-----------------------------------");
        
        // Creating student object
        // Student y1 = new Student("tanish", 77, 19);

    }
}
