        
        // code area aka method area store the code (class information and static variables) which when excuted in the program reaches stack area firstly for sequential execution 
        // and then heap area store the objects which are created in the program
        // s1 and s2 are two different objects of the same class day3_student and they are stored in heap area 
        // and they have their own copies of the instance variables marks, name and phone
        // variables are created in stack area and they are stored in stack area and they are destroyed when the method ends
        // objects are created in heap area and they are destroyed when the garbage collector runs

        // s1 and s2 are references to the objects of class day3_student and they are stored in stack area

public class day3_objects {
    public static void main(String[] args) {
        // int a=010; // System.out.println(a);
        // // This is an octal literal, which is equal to 8 in decimal
        // long a1=43843098943208L;System.out.println(a1);
        // float a2=23.823464498F;System.out.println(a2);
        // String a4="Hello";String a5=new String("Hello");System.out.println(a4+" "+a5);System.out.println(a4.length());System.out.println(a5.length());
        day3_student s1=new day3_student();day3_student s2=new day3_student();
        s1.name="Tanish";s1.marks=90;s1.phone="1234567890";
        s2.name="John";s2.marks=80;s2.phone="098765";
        s1.getname();
        s2.getname();
        day3_teacher t1=new day3_teacher();day3_teacher t2=new day3_teacher();
        t1.name="Tanish";t1.email="acsd3@gmail.com";t1.phone="1234567890";
        t2.name="John";t2.email="asjd039@gmail.com";t2.phone="098765";
        t1.getname();t1.getemail();t1.getphone();
        t2.getname();t2.getemail();t2.getphone();
        // make 10 objects of class t1 and print name;
        for(int i=0;i<10;i++){
            day3_teacher t=new day3_teacher();
            t.name="Teacher "+i;
            t.getname();
        }
    }
}
