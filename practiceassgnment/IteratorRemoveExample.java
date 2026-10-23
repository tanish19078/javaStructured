import java.util.*;
import java.util.ArrayList;

class Student{
    int id;

    public Student(int id) {
        this.id = id;
    }
}

public class IteratorRemoveExample {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(10, 5, 4, 3, 2, 1));

        ListIterator<Integer> listIterator = list.listIterator();
        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            int i = iterator.next();
            if (i == 5) {
                iterator.remove();  
            }
        }

        System.out.println(list);
ArrayList<Student> listx = new ArrayList<>(Arrays.asList(new Student(1), new Student(2), new Student(3)));


Comparator<Student> byId = new Comparator<Student>() {
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.id, s2.id);   
    }
};

listx.sort(byId);

    // COncurrent Modufication Exception
/* 
        for (int i : list) {
            if (i == 5) {
                list.remove(Integer.valueOf(i));  
            }
        }

        System.out.println(list); */
    }
}