public class day4_stack {
    // call stack understanding

    // stack is lifo (last in first out)
    // stack is used to store local variables and function calls
    // on a func call, a new stack frame is created and pushed onto the stack
    // when the function returns, the stack frame is popped off the stack
    // stack overflow occurs when the stack is full and a new stack frame cannot be created
    // stack underflow occurs when the stack is empty and a pop operation is attempted
// frames are loaded on top pf each and the top one gets executed first and when it is done it is popped off the stack and the next frame is executed
static void getINFO(String s1) {
        String s2="tree";
        String s3=new String("tree");
        System.out.println(s1==s2);
        System.out.println(s1==s3);
    }
    public static void main(String[] args) {
        String a="tree";
        getINFO(a);
              dosmth(5);
    }
// recursion is a function that calls itself


static void dosmth(int n) {
        if(n>=1) {
            System.out.println(n);
            dosmth(n-1);
        }
        // System.out.println(s1);
        
    }
}