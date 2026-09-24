/* JVM exception handling - when an exception occurs, the JVM creates an exception object and hands it off to the runtime system. 
The runtime system searches the call stack for a method that contains a block of code that can handle the exception.
 If no such method is found, the program terminates.
Exception handling is a mechanism to handle runtime errors so that the normal flow of the application can be maintained.
stack trace is printed when an exception is not handled - 
stack trace is a list of method calls that the program was in the middle of when an exception was thrown

stack overflow error - occurs when a program recurses too deeply and the call stack pointer exceeds the stack bound. its not a exception 
but an error. it is a subclass of java.lang.error class. it is thrown when the stack size exceeds its limit.
*/

/*
class throwable has two classes exception and error
then we have partially checked,unchecked  and checked 


 Checked Exceptions are enforced by the compiler at compile-time, forcing you to handle them using try-catch or declare them with throws 
 because they represent predictable, recoverable external errors (like IOException).
 
 Unchecked Exceptions (subclasses of RuntimeException or Error) are ignored by the compiler, meaning you don't have to catch or declare them, 
 as they usually signify programming flaws or critical system failures (like NullPointerException).
 
 Partially Checked Exceptions are checked classes (like Throwable or Exception) that contain a mix of both checked and unchecked child classes
  within their hierarchy, meaning the parent requires handling but some children bypass it.
  
  */
/* 
class Car{
    int model;
    String name;


}
 class exception_handing {
int dosomethingmore(){

    int num=29/0;
    return num;
}
int dosomething() {
    return dosomethingmore();
}

*/

/* 
public static void main(String[] args) {
    // exception_handling obj = new exception_handling();
    // obj.dosomething();
    Car c=null;
    System.out.println(c.name);
    String str = "hello";
    try{
        if(str.equals("heo")){
            System.out.println("String is hello");
        }
        else{
            System.out.println("String is not hello");
        }
    }
    catch(Exception e){
        System.out.println("Exception occurred");
    }
    finally{
        System.out.println("Finally block executed");
}
}}
*/


// multiple runtime exceptions can be handled in a single catch block using multi-catch feature of java 7

/*
finally contains the cleanup code that is executed after the try block, regardless of whether an exception was thrown or caught.
cant be using two finally blocks with a single try block. but we can use multiple catch blocks with a single try block. 
finally block is executed even if there is a return statement in the try or catch block.


*/
import java.util.Scanner;

public class exception_handling {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        sc.close();
        // Check password
        if (!password.equals("1234")) {
            try {
                throw new PasswordIncorrectException("Password is incorrect!");
                
            } catch (PasswordIncorrectException e) {
                System.out.println("Exception caught: " + e.getMessage());
            }
        } else {
            System.out.println("Password correct! Access granted.");
        }
        
        // Division by zero example
        try {
            int a = 10 / 0;
            System.out.println("Result: " + a); // This won't execute
        } 
        catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception: " + e.getMessage());
        } 
        catch (NullPointerException e) {
            System.out.println("Null Pointer Exception: " + e.getMessage());
        }
        // OR use multi-catch (Java 7+):
        // catch (ArithmeticException | NullPointerException e) {
        //     System.out.println("Exception: " + e.getMessage());
        // }
        finally {
            System.out.println("Finally block executed - cleanup here!");
        }
        
        sc.close();
    }
}

// Custom Exception Class - following Java naming conventions
class PasswordIncorrectException extends Exception {
    // Constructor with message only
    PasswordIncorrectException(String message) {
        super(message);
        // Don't print here - let the caller handle printing
    }
    
    // Optional: Constructor with no message
    PasswordIncorrectException() {
        super("Password is incorrect!");
    }
}