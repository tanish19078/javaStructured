// types of variables - local,instance,static
    // local variables - declared inside a method and can only be used inside that  method 
    // - primitive data types and reference data types
    // instance variables - declared inside a class but outside any method and can be used by all methods of the class 
    // - primitive data types and reference data types
    // static variables - declared with the static keyword and belong to the class rather than any specific instance 
    // - types of static variables - primitive data types and reference data types
public class day5_vars {
    public static void main(String[] args) {
        // arrays
        int[] stud=new int[5];System.out.println(stud);
        char[] ch=new char[5];boolean[] bool=new boolean[5];
        System.out.println(ch);System.out.println(bool);
        System.out.println(stud.getClass().getName()+"@"+Integer.toHexString(stud.hashCode()));
        // overwrting changes the definition of default toString() method of the object class and gives the address of the object in memory
        int[] st1=new int[Integer.MAX_VALUE]; // OutOfMemoryError: Requested array size exceeds VM limit
        // int[] st1=new int[Integer.MAX_VALUE+1]; // throws error because the size of the array cannot be greater than Integer.MAX_VALUE
        System.out.println(st1);
        // char[] ch1=new char[Integer.MAX_VALUE];byte[] b1=new byte[Integer.MAX_VALUE];short[] s1=new short[Integer.MAX_VALUE];long[] l1=new long[Integer.MAX_VALUE];float[] f1=new float[Integer.MAX_VALUE];double[] d1=new double[Integer.MAX_VALUE];
        // System.out.println(ch1);System.out.println(b1);System.out.println(s1);System.out.println(l1);System.out.println(f1);System.out.println(d1);
    }


}
