import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
public class hashx {
    public static void main(String[] args) {
      HashSet<Integer> set = new HashSet<>();  
Scanner sc = new Scanner(System.in);
int n = sc.nextInt();
for(int i = 0; i < n; i++) {
    set.add(sc.nextInt());
}
for(int num : set) {
    System.out.println(num);
}
System.out.println(set.size());
if(n==set.size())
    System.out.println("NO DUPLICATES");
else
    System.out.println("DUPLICATES FOUND");

// common elements in arr1 and 2 using hashset
int m = sc.nextInt();
int[] arr1 = new int[n];
for(int i = 0; i < n; i++) {
    arr1[i] = sc.nextInt();
}
int[] arr2 = new int[m];
for(int i = 0; i < m; i++) {
    arr2[i] = sc.nextInt();
}
HashSet<Integer> set2 = new HashSet<>();
for(int i = 0; i < n; i++) {
    set2.add(arr1[i]);
}
HashSet<Integer> common = new HashSet<>();
for(int i = 0; i < m; i++) {
    if(set2.contains(arr2[i])) {
        common.add(arr2[i]);
    }
}
System.out.println("Common elements:");
for(int num : common) {
    System.out.println(num);
}


// union of arr1 and arr2 using hashset
HashSet<Integer> union = new HashSet<>();
for(int i = 0; i < n; i++) {
    union.add(arr1[i]);
}
for(int i = 0; i < m; i++) {
    union.add(arr2[i]);
}
System.out.println("Union of arrays:");
for(int num : union) {
    System.out.println(num);
}

HashSet<String> y=new LinkedHashSet<>();
String y1=sc.nextLine();
String[] arr=y1.split(" ");
for(int i=0;i<arr.length;i++)
{
    y.add(arr[i]);
}

    }}