class Person{
    String name;
    int id;
    char gender;
    Person(){
        System.out.println("I am default constructor");
    }

// function signature - func name + type of parameters/arguments;
Person(int age,int marks){
    System.out.println("parameterised constructor");
}}
public class day10_constructor{
    public static void main(String[] args){
        Person p=new Person();
        Person px=new Person(20, 75);
        System.out.println(p+""+px);
    }
}