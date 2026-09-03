package assignment;
import java.util.*;

public class ArrayListProblems {
    
    static ArrayList<Integer> removeDuplicates(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer num : list) {
            if (!result.contains(num)) {
                result.add(num);
            }
        }
        return result;
    }
    
    static int findSecondLargest(ArrayList<Integer> list) {
        ArrayList<Integer> distinctList = removeDuplicates(list);
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        for (int num : distinctList) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }
        return secondLargest;
    }
    
    static void findFrequency(ArrayList<Integer> list) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (Integer num : list) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        ArrayList<Integer> printed = new ArrayList<>();
        for (Integer num : list) {
            if (!printed.contains(num)) {
                System.out.println(num + " -> " + frequencyMap.get(num));
                printed.add(num);
            }
        }
    }
    
    static ArrayList<Integer> findCommonElements(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> result = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>(list2);
        for (Integer num : list1) {
            if (set.contains(num) && !result.contains(num)) {
                result.add(num);
            }
        }
        return result;
    }
    
    static void separateEvenOdd(ArrayList<Integer> list) {
        ArrayList<Integer> evenList = new ArrayList<>();
        ArrayList<Integer> oddList = new ArrayList<>();
        for (Integer num : list) {
            if (num % 2 == 0) {
                evenList.add(num);
            } else {
                oddList.add(num);
            }
        }
        System.out.println("Even: " + evenList);
        System.out.println("Odd: " + oddList);
    }
    
    static ArrayList<Integer> reverseArrayList(ArrayList<Integer> list) {
        ArrayList<Integer> reversed = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            reversed.add(list.get(i));
        }
        return reversed;
    }
    
    static int findMissingNumber(ArrayList<Integer> list) {
        int n = list.size() + 1;
        int expectedSum = (n * (n + 1)) / 2;
        int actualSum = 0;
        for (int num : list) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }
    
    static ArrayList<Integer> mergeAndRemoveDuplicates(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> merged = new ArrayList<>(list1);
        merged.addAll(list2);
        return removeDuplicates(merged);
    }
    
    static ArrayList<Integer> rotateArrayList(ArrayList<Integer> list, int k) {
        int n = list.size();
        k = k % n;
        ArrayList<Integer> rotated = new ArrayList<>();
        for (int i = n - k; i < n; i++) {
            rotated.add(list.get(i));
        }
        for (int i = 0; i < n - k; i++) {
            rotated.add(list.get(i));
        }
        return rotated;
    }
    
    static void findLargestAndSmallest(ArrayList<Integer> list) {
        int largest = list.get(0);
        int smallest = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int current = list.get(i);
            if (current > largest) {
                largest = current;
            }
            if (current < smallest) {
                smallest = current;
            }
        }
        System.out.println("Largest = " + largest);
        System.out.println("Smallest = " + smallest);
    }
    
    public static void main(String[] args) {
        System.out.println("=== 1. Remove Duplicates ===");
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(10, 20, 10, 30, 20, 40));
        System.out.println("Input: " + list1);
        System.out.println("Output: " + removeDuplicates(list1));
        
        System.out.println("\n=== 2. Second Largest ===");
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(10, 50, 20, 50, 30, 40));
        System.out.println("Input: " + list2);
        System.out.println("Output: " + findSecondLargest(list2));
        
        System.out.println("\n=== 3. Frequency of Elements ===");
        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(10, 20, 10, 30, 20, 10));
        System.out.println("Input: " + list3);
        findFrequency(list3);
        
        System.out.println("\n=== 4. Common Elements ===");
        ArrayList<Integer> list4a = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        ArrayList<Integer> list4b = new ArrayList<>(Arrays.asList(30, 40, 60, 70));
        System.out.println("List1: " + list4a);
        System.out.println("List2: " + list4b);
        System.out.println("Output: " + findCommonElements(list4a, list4b));
        
        System.out.println("\n=== 5. Separate Even and Odd ===");
        ArrayList<Integer> list5 = new ArrayList<>(Arrays.asList(11, 20, 31, 42, 53, 64));
        System.out.println("Input: " + list5);
        separateEvenOdd(list5);
        
        System.out.println("\n=== 6. Reverse Without Collections.reverse() ===");
        ArrayList<Integer> list6 = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        System.out.println("Input: " + list6);
        System.out.println("Output: " + reverseArrayList(list6));
        
        System.out.println("\n=== 7. Find Missing Number ===");
        ArrayList<Integer> list7 = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 6, 7));
        System.out.println("Input: " + list7);
        System.out.println("Output: " + findMissingNumber(list7));
        
        System.out.println("\n=== 8. Merge and Remove Duplicates ===");
        ArrayList<Integer> list8a = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> list8b = new ArrayList<>(Arrays.asList(3, 4, 5, 6));
        System.out.println("List1: " + list8a);
        System.out.println("List2: " + list8b);
        System.out.println("Output: " + mergeAndRemoveDuplicates(list8a, list8b));
        
        System.out.println("\n=== 9. Rotate ArrayList ===");
        ArrayList<Integer> list9 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int k = 2;
        System.out.println("Input: " + list9 + ", k = " + k);
        System.out.println("Output: " + rotateArrayList(list9, k));
        
        System.out.println("\n=== 10. Find Largest and Smallest ===");
        ArrayList<Integer> list10 = new ArrayList<>(Arrays.asList(45, 12, 78, 23, 9, 56));
        System.out.println("Input: " + list10);
        findLargestAndSmallest(list10);
    }
}