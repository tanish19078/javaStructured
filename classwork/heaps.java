/*\
A heap is a specialized tree-based data structure that satisfies the heap property:
- In a min heap, for any given node I, the value of I is less than or equal to the values of its children.
- In a max heap, for any given node I, the value of I is greater than or equal to the values of its children.

Heaps are commonly implemented as arrays, where for a node at index i:
- Its l child is at index 2*i + 1
- Its r child is at index 2*i + 2
- Its parent is at index (i - 1) / 2

If parent node is at index i:
- Left child index = 2*i + 1
- Right child index = 2*i + 2

If given child index c:
- Parent index = (c - 1) / 2   [for l child where c = 2*i + 1]
- Parent index = (c - 2) / 2   [for r child where c = 2*i + 2]
- Both formulas give the same result using integer division: (c - 1) / 2 works for both cases

Common operations:
- Insert: Add a new element while maintaining heap property
- Extract min/max: Remove and return the root element
- Heapify: Convert an array into a heap
- Peek: View the root element without removing it

Time complexities:
- Insert: O(log n)
- Extract min/max: O(log n)
- Heapify: O(n)
- Peek: O(1)

Heaps are used in:
- Priority queues
- Heap sort algorithm
- Graph algorithms (e.g., Dijkstra's shortest path)
- Finding kth largest/smallest elements
*/

public class heaps {

static void heapify(int[] a){
int n=a.length;
for(int i=n/2-1;i>=0;i--){
heapifyd(a,n,i);
}}

static void heapifyd(int[] a,int n,int i){
    int largest=i;
    int l=2*i+1;
    int r=2*i+2;

     if (l < n && a[l] > a[largest])
            largest = l;
        
        if (r < n && a[r] > a[largest])
            largest = r;
        
        if (largest != i) {
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;
            heapifyd(a, n, largest);
        }
    }

    static void  heapsort(int[] a){
        int n=a.length;
        heapify(a);
        for(int i=n-1;i>0;i--){
            int temp=a[0];
            a[0]=a[i];
            a[i]=temp;
        heapifyd(a,i,0);

        }
    }

    public static void main(String[] args) {
        int[] a={9,7,1,8,3,6,4};
heapify(a);
heapsort(a);
for(int i=0;i<a.length;i++){
    System.err.println(a[i]);
}
    }
    
}
