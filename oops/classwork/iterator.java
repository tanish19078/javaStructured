package classwork;
import java.util.*;

public class iterator {
    
    public static void arrayListIterator() {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(27);
        list.add(35);

        java.util.Iterator<Integer> iterator = list.iterator();
        
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            System.out.println(value);
        }
        System.out.println(iterator.hasNext());
    }
    
    static void linkedListIterator() {
        java.util.LinkedList<String> list = new java.util.LinkedList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        
        java.util.Iterator<String> iterator = list.iterator();
        
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println(iterator.hasNext());
    }
    
    
    
    
    public static void main(String[] args) {
        arrayListIterator();
        linkedListIterator();
    }}
