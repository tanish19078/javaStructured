abstract class Person {
    String name;
    int id;
    int takeattendance = 0;
    
    Person(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    void display() {
        System.out.println("Name: " + this.name);
        System.out.println("ID: " + this.id);
    }
    
    void takeattendance() {
        takeattendance++;
        System.out.println("Attendance taken! Total: " + takeattendance);
    }
}

class Teacher extends Person {
    int phone;
    
    Teacher() {
        super("XTZ", 105);
        this.phone = 9854;
        takeattendance();
    }
    
    Teacher(String name, int id, int phone) {
        super(name, id);
        this.phone = phone;
    }
    
    void displayInfo() {
        super.display();
        System.out.println("Phone: " + this.phone);
        System.out.println("Attendance: " + takeattendance);
    }

    @Override
    void takeattendance() {
        throw new UnsupportedOperationException("Unimplemented method 'takeattendance'");
    }
}

class Student extends Person {
    int rollno;
    
    Student() {
        super("ty", 231);
        this.rollno = 45;
    }
    
    Student(String name, int id, int rollno) {
        super(name, id);
        this.rollno = rollno;
    }
    
    void displayInfo() {
        super.display();
        System.out.println("Roll No: " + this.rollno);
        System.out.println("Attendance: " + takeattendance);
    }


}

public class day13_inhertcont {
    public static void main(String[] args) {
        Teacher t1 = new Teacher();
        t1.displayInfo();
        
        System.out.println();
                Teacher t2 = new Teacher("John", 106, 9876);
        t2.takeattendance();
        t2.displayInfo();
        
        System.out.println();

        Student s1 = new Student();
        s1.displayInfo();
        
        System.out.println();
        

        Student s2 = new Student("Tanish", 102, 45);
        s2.takeattendance();
        s2.takeattendance();
        s2.displayInfo();
    }
}