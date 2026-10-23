class Student {
    String name;
    int age;
    String grade;
    
    public Student(String name, int age, String grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

}

class Employee{

    String name;
    int String; // identifiers name can be same as class name but not recommended , reusability 
    Employee(String name,int String){
        this.name = name;
        this.String = String;
    }
    void employee(String n){
        name = n;
    }
}

class Account{

    double bal=10000.00;
void display(){
    System.out.println("Balance is: "+bal);

}}



public class debugrun {
   public static void main(String[] args) {


      try{
Account acc1 = new Account(); 
acc1.display(); // Throws NullPointerException
}
        catch(NullPointerException e){
            System.out.println(e.getMessage());}
        finally{
            System.out.println("pass");}
    }}


    