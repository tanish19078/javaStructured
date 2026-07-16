class Student{
    int age;
    String name;
    int marks;
}


public class day6_arrays{
    public static void main(String[] args){
    // int []arr=new int[5];
    // int[] a1,a2;
    // int a3[],a4;
    // a2=new int[5];
    // a4=new int[6];
// string points to null by default
    // car[] arr =new car[5];
    String[] x=new String[11];
    x[0]=new String("A");
    x[1]=new String("B");
    // for(int i=0;i<11;i++){
    //     System.out.println(x[i]);
    // }
    Student[] a=new Student[5];
    a[0]=new Student();
    a[1]=new Student();
    a[2]=new Student();
    a[3]=new Student();
    a[4]=new Student();
    a[0].name="tanish";a[0].age=19;a[0].marks=75;
    for(int i=1;i<5;i++){
        a[i].name="stud"+i;
        a[i].age=i;
        a[i].marks=70+i;
        System.out.println(a[i].name);
    }
    int[] arrx={1,3,4,6,8,9,32,33};
    for(int ele:arrx){
        System.out.println(ele);}

    }
}