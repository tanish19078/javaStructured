/*  Interfaces are way to achieve abstraction,multi level inheritance 
abstract class can have function definition , interfaces can have only abstract methods(by default also abstract and public)
*/

interface Student {
    void getAttendance(); // all these funcs are by default public and abstract
    void getMarks();
    void getName();
}

class BtechStudent implements Student {
    String name;
    int attendance;
    double marks;
    long phone;
    
    public BtechStudent(String name, int attendance, double marks) {
        this.name = name;
        this.attendance = attendance;
        this.marks = marks;
    }
    // private BtechStudent(long phone){
    //     this.phone=phone;}
    
    
    public void getAttendance() {
        System.out.println("Attendance of " + name + ": " + attendance + "%");
    }
    
    public void getMarks() {
        System.out.println("Marks of " + name + ": " + marks);
    }
    
    public void getName() {
        System.out.println("Student Name: " + name);
    }
}


interface List {
    void add(int element);
    void display();
}

class ArrayList implements List {
    int[] arr = new int[10];
    int count = 0;
    
    public void add(int element) {
        arr[count++] = element;
    }
    
    public void display() {
        System.out.print("ArrayList: ");
        for (int i = 0; i < count; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

class LinkedList implements List {
    Node head;
    
    class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }
    
    public void add(int element) {
        Node newNode = new Node(element);
        if (head == null) head = newNode;
        else {
            Node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
    }
    
    public void display() {
        System.out.print("LinkedList: ");
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}




public class day15_interface {
    public static void main(String[] args) {
        Student s1 = new BtechStudent("Rahul", 85, 78.5);
   
        s1.getName();
        s1.getAttendance();
        s1.getMarks();

        List l1 = new ArrayList();
        List l2 = new LinkedList();
        
        l1.add(10); l1.add(20);
        l2.add(100); l2.add(200);
        
        l1.display();
        l2.display();

    }
}