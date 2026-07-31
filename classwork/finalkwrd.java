/*
final keyword 
a class wth final keyword cant be inherited
a method with final cant be over ridden
a variable with final cant be reassigned
*/


public class finalkwrd {
public static void main(String[] args) {
    car cx=new car("ford");
    cx.display();
}}


 final class car{
    final String Model;
    int cost;

    car(String ml){
        this.Model=ml;
    }

    public final void display(){
        System.out.println("x");
    }
public static final String s1="xyz";
public  final static String s2="xyz";

//  abstract static dx(){
    // System.err.println("test");}
}
 /*class Mk{
private int id;

Mk(int id){
    this.id=id;
}

private int getId() {
    return id;
}}

Mk extends Mv{

}

package mypack;

public class Parent {
    public int publicVar = 1;
    protected int protectedVar = 2;
    int defaultVar = 3;  
    private int privateVar = 4;
    
    public void publicMethod() {
        System.out.println("Public method");
    }
    
    protected void protectedMethod() {
        System.out.println("Protected method");
    }
    
    void defaultMethod() {  // default access - no keyword
        System.out.println("Default method");
    }
    
    private void privateMethod() {
        System.out.println("Private method");
    }
    
    // Test within same class
    public void testWithinClass() {
        System.out.println("=== Within Same Class ===");
        System.out.println("Public: " + publicVar);
        System.out.println("Protected: " + protectedVar);
        System.out.println("Default: " + defaultVar);
        System.out.println("Private: " + privateVar);
        
        publicMethod();
        protectedMethod();
        defaultMethod();
        privateMethod();
    }
}
    */