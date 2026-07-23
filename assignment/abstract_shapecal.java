/*
Create an abstract class Shape with:
 • Variable: color 
 • Abstract methods: area(), perimeter()
  • Concrete method: displayColor() 
  Create subclasses Circle, Rectangle, and Triangle. 
  Each subclass must implement the abstract methods using the appropriate mathematical formulas. 
  
  Requirements 1. Accept required dimensions from the user.
   2. Store all shape objects in an array of Shape. 
   3. Display the color of each shape. 
   4. Calculate and display area and perimeter for every object. 
   5. Use runtime polymorphism while invoking overridden methods. 
   6. Do not use Collections or Exception Handling.
   
   Expected Output For every shape, display: • Shape Type • Color • Area • Perimeter 
   Submission Guidelines Submit source code with proper comments and meaningful class, method, and variable names.


*/
import java.util.*;

abstract class Shape {
    String color;
    
    Shape(String color) {
        this.color = color;
    }
    
    abstract double area();
    abstract double perimeter();
    
    void displayColor() {
        System.out.println("Color: " + this.color);
    }
}

class Circle extends Shape {
    double radius;
    
    Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    double area() {
        return 3.14 * radius * radius;
    }
    
    @Override
    double perimeter() {
        return 2 * radius * 3.14;
    }
}

class Rectangle extends Shape {
    double l;
    double b;
    
    Rectangle(String color, double l, double b) {
        super(color);
        this.l = l;
        this.b = b;
    }
    
    @Override
    double area() {
        return l * b;
    }
    
    @Override
    double perimeter() {
        return 2 * (l + b);
    }
}

class Triangle extends Shape {
    double side1;
    double side2;
    double side3;
    
    Triangle(String color, double side1, double side2, double side3) {
        super(color);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
    
    @Override
    double area() {
        double s = perimeter() / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }
    
    @Override
    double perimeter() {
        return side1 + side2 + side3;
    }
}

public class abstract_shapecal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Shape[] shapes = new Shape[3];
        
        System.out.println("========== Circle ==========");
        System.out.print("Enter color: ");
        String color = sc.next();
        System.out.print("Enter radius: ");
        double radius = sc.nextDouble();
        shapes[0] = new Circle(color, radius);
        
        System.out.println("\n========== Rectangle ==========");
        System.out.print("Enter color: ");
        color = sc.next();
        System.out.print("Enter length: ");
        double length = sc.nextDouble();
        System.out.print("Enter breadth: ");
        double breadth = sc.nextDouble();
        shapes[1] = new Rectangle(color, length, breadth);
        
        System.out.println("\n========== Triangle ==========");
        System.out.print("Enter color: ");
        color = sc.next();
        System.out.print("Enter side 1: ");
        double side1 = sc.nextDouble();
        System.out.print("Enter side 2: ");
        double side2 = sc.nextDouble();
        System.out.print("Enter side 3: ");
        double side3 = sc.nextDouble();
        shapes[2] = new Triangle(color, side1, side2, side3);

        
        for (int i = 0; i < shapes.length; i++) {
            Shape shape = shapes[i];
            
            System.out.println("\nShape " + (i + 1) + ":");
            System.out.println("  Type: " + shape.getClass().getSimpleName());
            shape.displayColor();
            System.out.printf("  Area: %.2f\n", shape.area());
            System.out.printf("  Perimeter: %.2f\n", shape.perimeter());
        }
        
        sc.close();
    }
}