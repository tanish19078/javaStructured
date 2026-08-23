import java.util.*;

public class comparatorele {
    public static void main(String[] args) {
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if (a > b) {
                    return -1;
                } else if (a < b) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(comp);
        
        pq.add(5);
        pq.add(2);
        pq.add(8);
        pq.add(1);
        pq.add(9);
        
        System.out.println("Priority Queue (max heap): " + pq);
        
        System.out.println("Elements in priority order:");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
    }
}