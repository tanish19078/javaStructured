/*
creation of three classes person , student and teacher with array of attendance belonging to student; 
teacher would be able to view attendance of all(3 student objects here);
individual student can view only his / her own attendance;attendance array of size 7
markattendance() used by teacher and show attendance () by student for himself and teacher for all objects;
*/

abstract class Person{
    String name;

    Person(String name){
        this.name=name;
    }
    
    public abstract void showattendance();
}

class Student extends Person{
    boolean[] attendance=new boolean[7];

    public Student(String name){
        super(name);
    }

    @Override
    public void showattendance(){
        System.out.println("Attendance for " + name + ":");
        for(int i=0;i<7;i++){
            System.out.println("Day " + (i+1) + ": " + (attendance[i] ? "Present" : "Absent"));
        }
        System.out.println();
    }

    public boolean[] getattendance(){
        return attendance;
    }
}

class Teacher extends Person{
    Student[] studentlist;
    int studentcount;

    public Teacher(String name){
        super(name);
        studentcount=0;
        studentlist=new Student[80];
    }

    public void assignstudent(Student s){
        if(studentcount >= studentlist.length){
            System.err.println("Cannot add more students.");
            return;
        }


        for(int i=0; i<studentcount; i++){
            if(studentlist[i]==s){
                System.out.println(s.name + " is already present");
                return;
            }
        }

        studentlist[studentcount]=s;
        studentcount++;
        System.out.println(s.name + " has been assigned to " + name);
    }

    public void assignStudents(Student[] students){
        for(Student s : students){
            assignstudent(s);
        }
    }


    public void markattendance(Student s, boolean x, int day){
        if(day>=1 && day<=7){
            s.attendance[day-1]=x;
            System.out.println("Marked " + s.name + " as " + (x ? "Present" : "Absent") + " on Day " + day);
        } else {
            System.out.println("Invalid day! Must be between 1 and 7.");
        }
    }

    @Override
    public void showattendance(){
        System.out.println("\n=== All Students Attendance (Teacher: " + name + ") ===");
        if(studentcount == 0){
            System.out.println("No students assigned yet.");
            return;
        }
        for(int i=0; i<studentcount; i++){
            System.out.println("\nStudent: " + studentlist[i].name);
            for(int j=0; j<7; j++){
                System.out.println("Day " + (j+1) + ": " + (studentlist[i].attendance[j] ? "Present" : "Absent"));
            }
        }
    }
}

final class test{
    int tesid;}

// class s extends test{}; //"The type s cannot subclass the final class test"
public class attendancesys {
    public static void main(String[] args) {
        Student s1=new Student("A");
        Student s2=new Student("B");
        Student s3=new Student("C");

        Teacher t1=new Teacher("T");

        t1.assignstudent(s1);
        t1.assignstudent(s2);
        t1.assignstudent(s3);

        t1.markattendance(s1, true, 1);
        t1.markattendance(s1, false, 2);
        t1.markattendance(s1, true, 3);
        
        t1.markattendance(s2, false, 1);
        t1.markattendance(s2, true, 2);
        t1.markattendance(s2, true, 3);
        
        t1.markattendance(s3, true, 1);
        t1.markattendance(s3, true, 2);
        t1.markattendance(s3, false, 3);


        Person[] people = {s1, s2, s3, t1};
        for(Person p : people){
            p.showattendance();  
        }
    }
}