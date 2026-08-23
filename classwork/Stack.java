public class Stack<T> {
    T arr[];
    int top;
    int capacity;

    @SuppressWarnings("unchecked")
    Stack(int size) {
        top = -1;
        capacity = size;
        arr = (T[]) new Object[size];
    }

    void push(T data) throws Exception {
        if (top != capacity - 1) {
            top++;
            this.arr[top] = data;
        } else {
            throw new Exception("Stack overflow");
        }
    }

    T pop() throws Exception {
        if (top != -1) {
            return arr[top--];
        } else {
            throw new Exception("Stack underflow");
        }
    }
}

class Student {
    String name;
    int rollno;

    Student(String name, int rollno) {
        this.name = name;
        this.rollno = rollno;
    }
}

public class Test {
    public static void main(String[] args) throws Exception {
        Stack<Object> stack1 = new Stack<>(5);
        Stack<Object> stack2 = new Stack<>(5);
        Student student = new Student("John", 101);
        stack1.push(student);
        stack1.push(new Integer(10));
        stack2.push(new Float(20));
        stack1.push("hello");
        stack1.pop();
    }
}