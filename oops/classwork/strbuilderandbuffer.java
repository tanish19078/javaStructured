/* StringBuilder and StringBuffer 
String buffer is thread safe and slower than string builder.
strings are not mutable but buffer and reader and mutable tho; 
no string pool forboth of these classes ;only available for String class.


thread safety actually means that only one thread can access the object at a time. 
Thread Safety means when multiple threads access the same object or piece of code at the same time, 
the program still behaves correctly, without data corruption or unexpected results.

A class or method is thread-safe if it works fine even when accessed by many threads at once.
Even if threads run in any order, the shared data will always remain correct.
*/

public class strbuilderandbuffer {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        StringBuffer sbf = new StringBuffer("World");
        System.out.println(sb.toString()+sbf.toString());
        sbf.append(" Java");
        sb.insert(5, " Java");
        System.out.println(sbf.toString());
        System.out.println(sb.toString());
    }
}
