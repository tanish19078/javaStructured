# ✏️ Practice Questions: Strings, Arrays & Patterns

> Java code solutions — basic to medium level
> Each question includes the full problem, sample I/O, and complete runnable code.

---

## Part A: String Questions

---

### 1. Find the Length of a String (Without Built-in)

**Problem:** Write a program to find the length of a given string without using any built-in length function.

**Sample Input:** `Hello`
**Sample Output:** `Length = 5`

```java
import java.util.Scanner;

public class StringLength {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        int length = 0;
        // convert to char array and count
        for (char c : str.toCharArray()) {
            length++;
        }

        System.out.println("Length = " + length);
        sc.close();
    }
}
```

**Alternative — using try-catch on charAt:**
```java
int length = 0;
try {
    while (true) {
        str.charAt(length);
        length++;
    }
} catch (IndexOutOfBoundsException e) {
    // reached the end
}
System.out.println("Length = " + length);
```

---

### 2. Count Vowels and Consonants

**Problem:** Write a program to count the number of vowels and consonants in a given string.

**Sample Input:** `Programming`
**Sample Output:**
```
Vowels = 3
Consonants = 8
```

```java
import java.util.Scanner;

public class VowelConsonant {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().toLowerCase();

        int vowels = 0, consonants = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        System.out.println("Vowels = " + vowels);
        System.out.println("Consonants = " + consonants);
        sc.close();
    }
}
```

**Alternative — using String.indexOf for vowel check:**
```java
String vowelSet = "aeiouAEIOU";
for (int i = 0; i < str.length(); i++) {
    char ch = str.charAt(i);
    if (Character.isLetter(ch)) {
        if (vowelSet.indexOf(ch) != -1) vowels++;
        else consonants++;
    }
}
```

---

### 3. Reverse a String

**Problem:** Write a program to reverse a given string.

**Sample Input:** `Java`
**Sample Output:** `avaJ`

```java
import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }

        System.out.println(reversed);
        sc.close();
    }
}
```

**Alternative — using char array swap:**
```java
char[] chars = str.toCharArray();
int left = 0, right = chars.length - 1;
while (left < right) {
    char temp = chars[left];
    chars[left] = chars[right];
    chars[right] = temp;
    left++;
    right--;
}
System.out.println(new String(chars));
```

**Alternative — using StringBuilder:**
```java
StringBuilder sb = new StringBuilder(str);
System.out.println(sb.reverse().toString());
```

---

### 4. Check Palindrome String

**Problem:** Write a program to check whether the given string is a palindrome.

**Sample Input:** `madam`
**Sample Output:** `Palindrome`

```java
import java.util.Scanner;

public class PalindromeCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        boolean isPalindrome = true;
        int n = str.length();

        for (int i = 0; i < n / 2; i++) {
            if (str.charAt(i) != str.charAt(n - 1 - i)) {
                isPalindrome = false;
                break;
            }
        }

        System.out.println(isPalindrome ? "Palindrome" : "Not Palindrome");
        sc.close();
    }
}
```

**Alternative — using reverse comparison:**
```java
String reversed = new StringBuilder(str).reverse().toString();
if (str.equals(reversed)) {
    System.out.println("Palindrome");
} else {
    System.out.println("Not Palindrome");
}
```

---

### 5. Count Words in a Sentence

**Problem:** Write a program to count the number of words in a sentence.

**Sample Input:** `Java is easy`
**Sample Output:** `Number of words = 3`

```java
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().trim();

        if (str.isEmpty()) {
            System.out.println("Number of words = 0");
            return;
        }

        int count = 1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ' && str.charAt(i - 1) != ' ') {
                count++;
            }
        }

        System.out.println("Number of words = " + count);
        sc.close();
    }
}
```

**Alternative — using split:**
```java
String[] words = str.trim().split("\\s+");  // splits on one or more spaces
System.out.println("Number of words = " + words.length);
```

---

### 6. Convert Lowercase to Uppercase

**Problem:** Write a program to convert all lowercase letters into uppercase.

**Sample Input:** `hello world`
**Sample Output:** `HELLO WORLD`

```java
import java.util.Scanner;

public class ToUpperCase {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                result += (char)(ch - 32);  // ASCII: 'a'=97, 'A'=65, diff=32
            } else {
                result += ch;
            }
        }

        System.out.println(result);
        sc.close();
    }
}
```

**Alternative — using Character class:**
```java
String result = "";
for (int i = 0; i < str.length(); i++) {
    result += Character.toUpperCase(str.charAt(i));
}
System.out.println(result);
```

**Simplest — built-in method:**
```java
System.out.println(str.toUpperCase());
```

---

### 7. Find Frequency of a Character

**Problem:** Write a program to count how many times a given character appears in a string.

**Sample Input:**
```
banana
a
```
**Sample Output:** `Frequency = 3`

```java
import java.util.Scanner;

public class CharFrequency {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char target = sc.next().charAt(0);

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == target) {
                count++;
            }
        }

        System.out.println("Frequency = " + count);
        sc.close();
    }
}
```

**Alternative — using replace trick:**
```java
// remove all occurrences and compare length difference
int count = str.length() - str.replace(String.valueOf(target), "").length();
System.out.println("Frequency = " + count);
```

---

### 8. Remove White Spaces

**Problem:** Write a program to remove all spaces from a string.

**Sample Input:** `Java Programming`
**Sample Output:** `JavaProgramming`

```java
import java.util.Scanner;

public class RemoveSpaces {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        String result = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                result += str.charAt(i);
            }
        }

        System.out.println(result);
        sc.close();
    }
}
```

**Alternative — using replaceAll:**
```java
System.out.println(str.replaceAll(" ", ""));
// or to remove ALL whitespace (tabs, newlines too):
System.out.println(str.replaceAll("\\s", ""));
```

---

### 9. Check Anagram

**Problem:** Write a program to check whether two strings are anagrams (same characters, different order).

**Sample Input:**
```
listen
silent
```
**Sample Output:** `Anagrams`

```java
import java.util.Scanner;
import java.util.Arrays;

public class AnagramCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine().toLowerCase();
        String s2 = sc.nextLine().toLowerCase();

        if (s1.length() != s2.length()) {
            System.out.println("Not Anagrams");
            return;
        }

        // sort both and compare
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        if (Arrays.equals(arr1, arr2)) {
            System.out.println("Anagrams");
        } else {
            System.out.println("Not Anagrams");
        }
        sc.close();
    }
}
```

**Alternative — using frequency count (no sorting):**
```java
int[] freq = new int[26];
for (char c : s1.toCharArray()) freq[c - 'a']++;
for (char c : s2.toCharArray()) freq[c - 'a']--;

boolean isAnagram = true;
for (int f : freq) {
    if (f != 0) {
        isAnagram = false;
        break;
    }
}
System.out.println(isAnagram ? "Anagrams" : "Not Anagrams");
```

---

### 10. Find Duplicate Characters

**Problem:** Write a program to print all duplicate characters in a string.

**Sample Input:** `programming`
**Sample Output:**
```
r
g
m
```

```java
import java.util.Scanner;

public class DuplicateChars {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().toLowerCase();

        int[] freq = new int[26];

        // count frequency of each character
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                freq[ch - 'a']++;
            }
        }

        // print characters with frequency > 1
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 1) {
                System.out.println((char)(i + 'a'));
            }
        }
        sc.close();
    }
}
```

> **Note:** This prints duplicates in alphabetical order. If you need them in order of first appearance, iterate through the string instead.

**Alternative — preserving order of appearance:**
```java
boolean[] seen = new boolean[26];
boolean[] printed = new boolean[26];

for (int i = 0; i < str.length(); i++) {
    char ch = str.charAt(i);
    if (ch >= 'a' && ch <= 'z') {
        int idx = ch - 'a';
        if (seen[idx] && !printed[idx]) {
            System.out.println(ch);
            printed[idx] = true;
        }
        seen[idx] = true;
    }
}
```

---

## Part B: Array Questions

---

### 1. Find Sum of Elements

**Problem:** Write a program to calculate the sum of all elements in an array.

**Sample Input:**
```
5
10 20 30 40 50
```
**Sample Output:** `Sum = 150`

```java
import java.util.Scanner;

public class ArraySum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        System.out.println("Sum = " + sum);
        sc.close();
    }
}
```

**Alternative — using for-each:**
```java
int sum = 0;
for (int num : arr) {
    sum += num;
}
```

---

### 2. Find Largest Element

**Problem:** Write a program to find the largest element in an array.

**Sample Input:**
```
5
8 3 15 9 6
```
**Sample Output:** `Largest = 15`

```java
import java.util.Scanner;

public class LargestElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        System.out.println("Largest = " + max);
        sc.close();
    }
}
```

**Alternative — using Math.max:**
```java
int max = arr[0];
for (int i = 1; i < n; i++) {
    max = Math.max(max, arr[i]);
}
```

---

### 3. Find Smallest Element

**Problem:** Write a program to find the smallest element.

**Sample Input:**
```
5
8 3 15 9 6
```
**Sample Output:** `Smallest = 3`

```java
import java.util.Scanner;

public class SmallestElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int min = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        System.out.println("Smallest = " + min);
        sc.close();
    }
}
```

---

### 4. Calculate Average

**Problem:** Write a program to calculate the average of all array elements.

**Sample Input:**
```
5
2 4 6 8 10
```
**Sample Output:** `Average = 6.0`

```java
import java.util.Scanner;

public class ArrayAverage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }

        double average = (double) sum / n;  // cast to double for decimal result
        System.out.println("Average = " + average);
        sc.close();
    }
}
```

---

### 5. Search an Element (Linear Search)

**Problem:** Write a program to search for an element in an array using Linear Search.

**Sample Input:**
```
5
2 5 8 1 7
8
```
**Sample Output:** `Element Found at Index 2`

```java
import java.util.Scanner;

public class LinearSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int target = sc.nextInt();
        int foundIndex = -1;

        for (int i = 0; i < n; i++) {
            if (arr[i] == target) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex != -1) {
            System.out.println("Element Found at Index " + foundIndex);
        } else {
            System.out.println("Element Not Found");
        }
        sc.close();
    }
}
```

---

### 6. Count Even and Odd Numbers

**Problem:** Write a program to count even and odd numbers in an array.

**Sample Input:**
```
6
1 2 3 4 5 6
```
**Sample Output:**
```
Even = 3
Odd = 3
```

```java
import java.util.Scanner;

public class EvenOddCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        int even = 0, odd = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if (arr[i] % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        System.out.println("Even = " + even);
        System.out.println("Odd = " + odd);
        sc.close();
    }
}
```

---

### 7. Reverse an Array

**Problem:** Write a program to reverse the elements of an array.

**Sample Input:**
```
5
1 2 3 4 5
```
**Sample Output:** `5 4 3 2 1`

```java
import java.util.Scanner;

public class ReverseArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // swap from both ends moving inward
        int left = 0, right = n - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        sc.close();
    }
}
```

**Alternative — print in reverse without modifying array:**
```java
for (int i = n - 1; i >= 0; i--) {
    System.out.print(arr[i] + " ");
}
```

---

### 8. Find Second Largest Element

**Problem:** Write a program to find the second largest element.

**Sample Input:**
```
5
12 45 9 31 40
```
**Sample Output:** `Second Largest = 40`

```java
import java.util.Scanner;

public class SecondLargest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (arr[i] > first) {
                second = first;       // old first becomes second
                first = arr[i];       // new first
            } else if (arr[i] > second && arr[i] != first) {
                second = arr[i];      // new second (must be different from first)
            }
        }

        System.out.println("Second Largest = " + second);
        sc.close();
    }
}
```

> This finds the second largest in a **single pass** (O(n)) — no sorting needed.

---

### 9. Count Frequency of Elements

**Problem:** Write a program to count the frequency of each element.

**Sample Input:**
```
6
1 2 1 3 2 1
```
**Sample Output:**
```
1 -> 3
2 -> 2
3 -> 1
```

```java
import java.util.Scanner;

public class ElementFrequency {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;  // already counted

            int count = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                    visited[j] = true;  // mark as counted
                }
            }

            System.out.println(arr[i] + " -> " + count);
        }
        sc.close();
    }
}
```

---

### 10. Remove Duplicate Elements

**Problem:** Write a program to remove duplicate elements from an array.

**Sample Input:**
```
7
1 2 3 2 4 1 5
```
**Sample Output:** `1 2 3 4 5`

```java
import java.util.Scanner;

public class RemoveDuplicates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // use a result array to store unique elements
        int[] result = new int[n];
        int size = 0;

        for (int i = 0; i < n; i++) {
            boolean isDuplicate = false;
            for (int j = 0; j < size; j++) {
                if (arr[i] == result[j]) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                result[size] = arr[i];
                size++;
            }
        }

        for (int i = 0; i < size; i++) {
            System.out.print(result[i] + " ");
        }
        sc.close();
    }
}
```

**Alternative — using LinkedHashSet (preserves order, removes dups automatically):**
```java
import java.util.LinkedHashSet;

LinkedHashSet<Integer> set = new LinkedHashSet<>();
for (int num : arr) {
    set.add(num);
}
for (int num : set) {
    System.out.print(num + " ");
}
```

---

## Part C: Pattern Questions

---

### 1. Square Pattern

**Problem:** Print a square pattern of stars.

**Input:** `4`
**Output:**
```
* * * *
* * * *
* * * *
* * * *
```

```java
import java.util.Scanner;

public class SquarePattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        sc.close();
    }
}
```

---

### 2. Right Triangle

**Problem:** Print a right-angled triangle.

**Input:** `5`
**Output:**
```
*
* *
* * *
* * * *
* * * * *
```

```java
import java.util.Scanner;

public class RightTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {        // i = current row (1 to n)
            for (int j = 1; j <= i; j++) {    // print i stars in row i
                System.out.print("* ");
            }
            System.out.println();
        }
        sc.close();
    }
}
```

---

### 3. Inverted Triangle

**Problem:** Print an inverted star triangle.

**Input:** `5`
**Output:**
```
* * * * *
* * * *
* * *
* *
*
```

```java
import java.util.Scanner;

public class InvertedTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = n; i >= 1; i--) {        // start from n, go down
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        sc.close();
    }
}
```

---

### 4. Number Triangle

**Problem:** Print a number triangle.

**Input:** `5`
**Output:**
```
1
1 2
1 2 3
1 2 3 4
1 2 3 4 5
```

```java
import java.util.Scanner;

public class NumberTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");    // print numbers 1 to i
            }
            System.out.println();
        }
        sc.close();
    }
}
```

---

### 5. Floyd's Triangle

**Problem:** Print Floyd's Triangle (consecutive numbers filling rows).

**Input:** `5`
**Output:**
```
1
2 3
4 5 6
7 8 9 10
11 12 13 14 15
```

```java
import java.util.Scanner;

public class FloydsTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int num = 1;    // a single counter that keeps incrementing
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(num + " ");
                num++;
            }
            System.out.println();
        }
        sc.close();
    }
}
```

---

### 6. Character Triangle

**Problem:** Print the alphabet triangle.

**Input:** `4`
**Output:**
```
A
A B
A B C
A B C D
```

```java
import java.util.Scanner;

public class CharTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print((char)('A' + j) + " ");  // 'A'+0='A', 'A'+1='B', etc.
            }
            System.out.println();
        }
        sc.close();
    }
}
```

---

### 7. Full Pyramid

**Problem:** Print a full (centered) pyramid of stars.

**Input:** `5`
**Output:**
```
    *
   * *
  * * *
 * * * *
* * * * *
```

```java
import java.util.Scanner;

public class FullPyramid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            // print leading spaces (n-i spaces)
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            // print stars with spaces
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        sc.close();
    }
}
```

> **How it works:** Row `i` has `(n - i)` leading spaces followed by `i` stars. The spaces shift the stars to the right, creating the pyramid shape.

---

### 8. Inverted Full Pyramid

**Problem:** Print an inverted full pyramid.

**Input:** `5`
**Output:**
```
* * * * *
 * * * *
  * * *
   * *
    *
```

```java
import java.util.Scanner;

public class InvertedPyramid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = n; i >= 1; i--) {
            // print leading spaces (n-i spaces)
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            // print stars
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        sc.close();
    }
}
```

---

### 9. Diamond Pattern

**Problem:** Print a diamond using stars.

**Input:** `5`
**Output:**
```
    *
   * *
  * * *
 * * * *
* * * * *
 * * * *
  * * *
   * *
    *
```

```java
import java.util.Scanner;

public class DiamondPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // upper half (including middle row)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        // lower half (excluding middle row)
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        sc.close();
    }
}
```

> **How it works:** The diamond is just a full pyramid on top + inverted pyramid (minus the middle row) on bottom.

---

### 10. Hollow Square Pattern

**Problem:** Print a hollow square.

**Input:** `5`
**Output:**
```
*****
*   *
*   *
*   *
*****
```

```java
import java.util.Scanner;

public class HollowSquare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // print star if: first row, last row, first col, or last col
                if (i == 1 || i == n || j == 1 || j == n) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        sc.close();
    }
}
```

> **Logic:** A position gets a `*` only if it's on the border (first/last row or first/last column). Everything else is a space.

---

## Quick Pattern Logic Cheat Sheet

| Pattern | Outer Loop | Inner Loop(s) |
|---------|-----------|---------------|
| Square | `i: 1→n` | `j: 1→n` → print `*` |
| Right Triangle | `i: 1→n` | `j: 1→i` → print `*` |
| Inverted Triangle | `i: n→1` | `j: 1→i` → print `*` |
| Full Pyramid | `i: 1→n` | spaces: `1→(n-i)` + stars: `1→i` |
| Diamond | Upper pyramid + Lower inverted pyramid |
| Hollow | Print `*` only on borders, space otherwise |

---

*All code is complete and runnable — just copy, compile, and test. Good luck! 🚀*
