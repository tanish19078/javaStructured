import java.util.*;
class X{
    interface A{
        void display();
    }
    interface B{
        void display();
    }
    interface C extends A,B{
        void display();
    }
}




interface Vehicle{
    double fuelEfficiency(double distance, double fuelUsed);
}
class Car implements Vehicle{
    public double fuelEfficiency(double distance, double fuelUsed){
        return distance / fuelUsed;
    }}
class Bike implements Vehicle{
    public double fuelEfficiency(double distance, double fuelUsed){
        return distance / fuelUsed;
    }
} 

public class Main{
    public static void main(String[] args){
Scanner sc=new Scanner(System.in);
int choice=sc.nextInt();
Vehicle v;
if(choice==1){
     v=new Car();}
    else{
        v=new Bike();
    }
    System.out.printf("%.2f",v.fuelEfficiency(100,5));

}
}


// static visit counter

// class visitor{
//     static int count=0;
//     visitor(){
//         count++;
//     }
// }
// public class Main{
//     public static void main(String[] args){
//         int n = 5; // Example value
//         for(int i=0;i<n;i++){
//             new visitor();
//         }
//         System.out.println(visitor.count);
//     }
// }

// employee

class Employee{
    double salary;
    private int id;
    Employee(double salary, int id){
        this.salary=salary;
        this.id=id;
    }
    double calculateSalary(){
        return salary;
    }
}
class Manager extends Employee{
    Manager(double salary, int id){
        super(salary, id);
    }
    double calculateSalary(){
        return salary+5000;
    }
}
class Developer extends Employee{
    int projects;
    Developer(double salary, int id, int projects){
        super(salary, id);
        this.projects=projects;
    }
    double calculateSalary(){
        return salary+2000*projects;
    }
}

abstract class Shape{

    abstract double area();
}

class Circle extends Shape{
    double radius;
    Circle(double radius){
        this.radius=radius;
    }
    double area(){
        return Math.PI*radius*radius;
    }
}

class Rectangle extends Shape{
    double length;double breadth;
    Rectangle(double length,double breadth){
        this.length=length;
        this.breadth=breadth;

    }
    double area(){
        return length*breadth;
    }
}