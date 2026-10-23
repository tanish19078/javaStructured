# OOPS-2 Java Practice Revision

48 questions from the five sections of 25CSE0202-OOPS-2, checked on 23 October 2026. Each description keeps the details needed to practise, followed immediately by a short solution.

## How to use

Map iteration uses `for (var e : freq.entrySet())`. `var` requires Java 10 or newer; for older Java, use `Map.Entry<Character, Integer> e` (or `Map.Entry<Integer, Integer> e` for ages). Counting loops still scan the input, and first-unique solutions scan the original string to preserve order.

Several questions also include a **basic alternative** or **built-in alternative**. These show loops, ASCII logic, string methods, or sorting alongside the original solution. Choose one version when practising; do not paste both methods with the same signature into one class.

- Practise one question at a time. Repeated class names in different questions are independent; do not combine all snippets into one file.
- For method-only snippets, put the method inside `class Solution { ... }` unless your provided template specifies another class. Keep the template's exact method name, return type and `static` modifier when submitting. Some revision helper names are descriptive where the statement does not prescribe a name.
- Add `import java.util.*;` above a snippet when needed. Methods return answers unless the question explicitly asks them to print. OOP snippets define the requested classes; no `main` is needed.
- Spiral/boundary solutions use `List<Integer>` as a convenient revision return type. If your supplied template requires `int[]`, convert the result with `ans.stream().mapToInt(Integer::intValue).toArray()` and change the return type.
- Matrix inputs are nonempty and rectangular; in-place rotations require a square matrix. Use zero-based indices in Java.
- Source examples with contradictory outputs are flagged beside the affected solution. Follow the written rules for those questions.

## Sections

| Section | Questions |
| --- | ---: |
| 1. 2D Array | 8 |
| 2. Strings | 14 |
| 3. Practice Test | 3 |
| 4. Abstract Class and Interface | 11 |
| 5. Inheritance | 12 |


## 1. 2D Array

### 1. Row-Wise and Column-Wise Sum

**Question:** Given an m × n matrix, return int[][] with exactly two rows: row sums in the first, column sums in the second. The two result rows may have different lengths.

**Solution idea:** Add each cell to its row total and column total. O(mn) time; O(m+n) result space.

```java
public int[][] rowColumnSum(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    int[][] ans = {new int[m], new int[n]};
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++) {
            ans[0][i] += matrix[i][j];
            ans[1][j] += matrix[i][j];
        }
    return ans;
}
```

### 2. Transpose of a Matrix

**Question:** Return the transpose of an m × n matrix: cell (i,j) becomes (j,i).

**Solution idea:** Create an n × m result and swap the indices. O(mn) time and result space.

```java
public int[][] transpose(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    int[][] ans = new int[n][m];
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            ans[j][i] = matrix[i][j];
    return ans;
}
```

### 3. Rotate Matrix 90 Degrees Clockwise

**Question:** Rotate an n × n matrix clockwise in place.

**Solution idea:** Transpose, then reverse every row. O(n²) time, O(1) extra space.

```java
public void rotate(int[][] matrix) {
    int n = matrix.length;
    for (int i = 0; i < n; i++)
        for (int j = i + 1; j < n; j++) {
            int t = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = t;
        }
    for (int[] row : matrix)
        for (int l = 0, r = n - 1; l < r; l++, r--) {
            int t = row[l]; row[l] = row[r]; row[r] = t;
        }
}
```

### 4. Spiral Matrix Traversal

**Question:** Return every element clockwise in spiral order, starting at the top-left.

**Solution idea:** Maintain top, bottom, left and right boundaries. Shrink after traversing each edge. O(mn) time.

```java
public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> ans = new ArrayList<>();
    int top = 0, bottom = matrix.length - 1;
    int left = 0, right = matrix[0].length - 1;
    while (top <= bottom && left <= right) {
        for (int j = left; j <= right; j++) ans.add(matrix[top][j]);
        top++;
        for (int i = top; i <= bottom; i++) ans.add(matrix[i][right]);
        right--;
        if (top <= bottom) {
            for (int j = right; j >= left; j--) ans.add(matrix[bottom][j]);
            bottom--;
        }
        if (left <= right) {
            for (int i = bottom; i >= top; i--) ans.add(matrix[i][left]);
            left++;
        }
    }
    return ans;
}
```

**Remember:** The boundary checks prevent duplicates for a single remaining row or column. Example: [[1,2,3],[4,5,6],[7,8,9]] → [1,2,3,6,9,8,7,4,5].

### 5. Search in a Sorted Matrix

**Question:** Each row and column is sorted in increasing order. Return zero-based [row,col] of target, or [-1,-1]. Required time: O(m+n).

**Solution idea:** Start at the top-right. Too large → left; too small → down.

```java
public int[] searchMatrix(int[][] matrix, int target) {
    int i = 0, j = matrix[0].length - 1;
    while (i < matrix.length && j >= 0) {
        if (matrix[i][j] == target) return new int[]{i, j};
        if (matrix[i][j] > target) j--;
        else i++;
    }
    return new int[]{-1, -1};
}
```

### 6. Rotate Matrix Counter-Clockwise In-Place

**Question:** Rotate an n × n matrix 90 degrees counter-clockwise in place.

**Solution idea:** Transpose, then reverse every column. O(n²) time, O(1) extra space.

```java
public void rotateCounterClockwise(int[][] matrix) {
    int n = matrix.length;
    for (int i = 0; i < n; i++)
        for (int j = i + 1; j < n; j++) {
            int t = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = t;
        }
    for (int j = 0; j < n; j++)
        for (int top = 0, bottom = n - 1; top < bottom; top++, bottom--) {
            int t = matrix[top][j];
            matrix[top][j] = matrix[bottom][j];
            matrix[bottom][j] = t;
        }
}
```

### 7. Set Matrix Zeroes

**Question:** If an original cell is zero, make its entire row and column zero. Modify and return the same matrix. Auxiliary memory may be O(m+n).

**Solution idea:** First mark rows/columns containing original zeroes; then modify cells. O(mn) time.

```java
public int[][] setZeroes(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    boolean[] rows = new boolean[m], cols = new boolean[n];
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            if (matrix[i][j] == 0) { rows[i] = true; cols[j] = true; }
    for (int i = 0; i < m; i++)
        for (int j = 0; j < n; j++)
            if (rows[i] || cols[j]) matrix[i][j] = 0;
    return matrix;
}
```

**Remember:** Do not zero cells during the first scan: newly created zeroes would incorrectly spread.

### 8. Boundary Traversal of a Matrix

**Question:** Return only the outer boundary clockwise from the top-left, without repeating corners.

**Solution idea:** Top row → right column → bottom row backwards → left column upwards. O(m+n) time.

```java
public List<Integer> boundaryTraversal(int[][] matrix) {
    List<Integer> ans = new ArrayList<>();
    int m = matrix.length, n = matrix[0].length;
    for (int j = 0; j < n; j++) ans.add(matrix[0][j]);
    for (int i = 1; i < m; i++) ans.add(matrix[i][n - 1]);
    if (m > 1)
        for (int j = n - 2; j >= 0; j--) ans.add(matrix[m - 1][j]);
    if (n > 1)
        for (int i = m - 2; i >= 1; i--) ans.add(matrix[i][0]);
    return ans;
}
```

## 2. Strings

### 1. Reverse a String

**Question:** Return the string with all characters reversed, including spaces and punctuation.

**Solution idea:** Use an empty result string and append characters from right to left. Repeated concatenation can take O(n²) time.

```java
public String reverseString(String s) {
    String ans = "";
    for (int i = s.length() - 1; i >= 0; i--)
        ans += s.charAt(i);
    return ans;
}
```

### 2. Check Palindrome

**Question:** Return whether the string reads the same backwards, ignoring case. Spaces and punctuation still participate. The empty string is a palindrome.

**Solution idea:** Compare characters from both ends after converting to lowercase. O(n) time.

```java
public boolean isPalindrome(String s) {
    int l = 0, r = s.length() - 1;
    while (l < r) {
        if (Character.toLowerCase(s.charAt(l++)) !=
            Character.toLowerCase(s.charAt(r--))) return false;
    }
    return true;
}
```

### 3. Count Vowels and Consonants

**Question:** Return [vowelCount, consonantCount]. Count English letters in either case; ignore digits, spaces and symbols.

**Solution idea:** Lowercase each character, check a–z, then test membership in aeiou. O(n) time.

```java
public int[] countVowelsConsonants(String s) {
    int vowels = 0, consonants = 0;
    for (char c : s.toCharArray()) {
        c = Character.toLowerCase(c);
        if (c >= 'a' && c <= 'z') {
            if ("aeiou".indexOf(c) >= 0) vowels++;
            else consonants++;
        }
    }
    return new int[]{vowels, consonants};
}
```

### 4. Toggle Case

**Question:** Change uppercase letters to lowercase and lowercase to uppercase. Keep other characters unchanged.

**Solution idea:** Start with an empty string. Change each letter’s case and append it using +=. Repeated concatenation can take O(n²) time.

```java
public String toggleCase(String s) {
    String ans = "";
    for (char c : s.toCharArray()) {
        if (Character.isUpperCase(c)) ans += Character.toLowerCase(c);
        else if (Character.isLowerCase(c)) ans += Character.toUpperCase(c);
        else ans += c;
    }
    return ans;
}
```

**Basic alternative — ASCII comparisons**

For English letters, lowercase and uppercase differ by 32. Check the range before changing the character; leave other characters alone. The simple result-string concatenation can take O(n²) time.

```java
public String toggleCase(String s) {
    String ans = "";
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c >= 'A' && c <= 'Z') c = (char)(c + 32);
        else if (c >= 'a' && c <= 'z') c = (char)(c - 32);
        ans += c;
    }
    return ans;
}
```

### 5. Character Frequency Counter

**Question:** Count only lowercase a–z. Ignore uppercase and other characters. Return alphabetical letterCount groups separated by one space; skip absent letters.

**Solution idea:** Count in a TreeMap, then iterate with entrySet(). TreeMap keeps letters alphabetical. Start with an empty string and append each letter/count pair. O(n log k) time for k distinct lowercase letters (at most 26).

```java
public String charFrequency(String s) {
    TreeMap<Character, Integer> freq = new TreeMap<>();
    for (char c : s.toCharArray()) {
        if (c >= 'a' && c <= 'z')
            freq.put(c, freq.getOrDefault(c, 0) + 1);
    }
    String res = "";
    for (var e : freq.entrySet()) {
        res += "" + e.getKey() + e.getValue() + " ";
    }
    return res.trim();
}
```

**Basic alternative — count each letter with loops**

For each letter a–z, scan the string and count matches. This automatically gives alphabetical output and ignores uppercase/symbols. O(26n) scanning time; no map or frequency array.

```java
public String charFrequency(String s) {
    String ans = "";
    for (char letter = 'a'; letter <= 'z'; letter++) {
        int count = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == letter) count++;
        if (count > 0) {
            if (!ans.isEmpty()) ans += " ";
            ans += "" + letter + count;
        }
    }
    return ans;
}
```

### 6. Check if Two Strings are Anagrams

**Question:** For two lowercase strings, return true if every letter has the same frequency in both.

**Solution idea:** Sort both character arrays and compare them with Arrays.equals(), exactly as in your approach. O(n log n + m log m) time; O(n+m) array space. Add `import java.util.Arrays;` above the class.

```java
class Solution {
    public boolean areAnagrams(String s, String t) {
        char[] x = s.toCharArray();
        char[] y = t.toCharArray();
        Arrays.sort(x);
        Arrays.sort(y);
        return Arrays.equals(x, y);
    }
}
```

**HashMap alternative**

Use a HashMap: increment for s, decrement for t, then check that all counts are zero. Expected O(n+m) time.

```java
public boolean areAnagrams(String s, String t) {
    if (s.length() != t.length()) return false;
    HashMap<Character, Integer> freq = new HashMap<>();
    for (char c : s.toCharArray())
        freq.put(c, freq.getOrDefault(c, 0) + 1);
    for (char c : t.toCharArray())
        freq.put(c, freq.getOrDefault(c, 0) - 1);
    for (var e : freq.entrySet()) {
        if (e.getValue() != 0) return false;
    }
    return true;
}
```

### 7. First Non-Repeating Character

**Question:** Return the first lowercase character occurring exactly once; return # if none exists or the string is empty.

**Solution idea:** Count in a HashMap, then scan the string in its original order. Expected O(n) time.

```java
public char firstNonRepeatingChar(String s) {
    HashMap<Character, Integer> freq = new HashMap<>();
    for (char c : s.toCharArray())
        freq.put(c, freq.getOrDefault(c, 0) + 1);
    for (char c : s.toCharArray())
        if (freq.get(c) == 1) return c;
    return '#';
}
```

**Built-in alternative — indexOf and lastIndexOf**

A character occurs exactly once when its first and last positions are equal. Scan left to right to find the first such character. Easy for small practice inputs; worst-case O(n²), so use the HashMap solution for larger inputs.

```java
public char firstNonRepeatingChar(String s) {
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (s.indexOf(c) == s.lastIndexOf(c)) return c;
    }
    return '#';
}
```

### 8. Reverse Words in a Sentence

**Question:** Reverse word order without reversing characters inside words. Collapse repeated spaces and remove leading/trailing spaces.

**Solution idea:** Trim, split on one or more whitespace characters, and append words backwards to an empty string. Repeated concatenation can take O(n²) time.

```java
public String reverseWords(String s) {
    s = s.trim();
    if (s.isEmpty()) return "";
    String[] words = s.split("\\s+");
    String ans = "";
    for (int i = words.length - 1; i >= 0; i--) {
        if (!ans.isEmpty()) ans += " ";
        ans += words[i];
    }
    return ans;
}
```

**Basic alternative — split and a backward loop**

trim() removes outer spaces; split(" +") handles repeated spaces. Append the words from right to left. Repeated concatenation can take O(n²) time.

```java
public String reverseWords(String s) {
    s = s.trim();
    if (s.isEmpty()) return "";
    String[] words = s.split(" +");
    String ans = "";
    for (int i = words.length - 1; i >= 0; i--) {
        if (!ans.isEmpty()) ans += " ";
        ans += words[i];
    }
    return ans;
}
```

### 9. Longest Substring Without Repeating Characters

**Question:** Return the length of the longest contiguous substring with no repeated character. Input contains printable ASCII.

**Solution idea:** Sliding window: store the index after each character’s last occurrence. Move left forward on repeats. O(n) time.

```java
public int lengthOfLongestSubstring(String s) {
    int[] next = new int[128];
    int left = 0, best = 0;
    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        left = Math.max(left, next[c]);
        best = Math.max(best, right - left + 1);
        next[c] = right + 1;
    }
    return best;
}
```

**Remember:** Example: abba → 2. Never move left backwards.

### 10. String Compression

**Question:** Compress consecutive groups in char[] in place. Write the character, followed by its count only when count > 1. Return the new length. Example: aabbccc → a2b2c3; twelve b characters → b12.

**Solution idea:** Use a read index to find groups and a write index for output. O(n) time; O(1) auxiliary space.

```java
public int compress(char[] chars) {
    int read = 0, write = 0;
    while (read < chars.length) {
        char c = chars[read];
        int start = read;
        while (read < chars.length && chars[read] == c) read++;
        chars[write++] = c;
        int count = read - start;
        if (count > 1)
            for (char digit : Integer.toString(count).toCharArray())
                chars[write++] = digit;
    }
    return write;
}
```

**Remember:** Only the first returned-length cells are meaningful. The count conversion uses bounded space for the given maximum length 2000.

### 11. First Unique Character

**Question:** Return the zero-based INDEX of the first lowercase letter occurring once, or -1. Length can be 100000.

**Solution idea:** Count with a HashMap, then scan indices from left to right and return the first with count 1. Expected O(n) time.

```java
public int firstUniqChar(String str) {
    HashMap<Character, Integer> freq = new HashMap<>();
    for (char c : str.toCharArray())
        freq.put(c, freq.getOrDefault(c, 0) + 1);
    for (int i = 0; i < str.length(); i++)
        if (freq.get(str.charAt(i)) == 1) return i;
    return -1;
}
```

**Built-in alternative — check a–z**

For each lowercase letter, find its first and last positions. If they match and are not -1, it is unique. Keep the smallest index among all unique letters. O(26n) time and O(1) extra space; suitable for the given lowercase constraint.

```java
public int firstUniqChar(String str) {
    int answer = -1;
    for (char c = 'a'; c <= 'z'; c++) {
        int first = str.indexOf(c);
        int last = str.lastIndexOf(c);
        if (first != -1 && first == last) {
            if (answer == -1 || first < answer) answer = first;
        }
    }
    return answer;
}
```

### 12. Strings are anagram or not

**Question:** Strings may contain any ASCII character. Return 1 if their character frequencies match, otherwise 0. Comparison is case-sensitive.

**Solution idea:** Use a HashMap for ASCII character counts, preserving case. Increment for a, decrement for b. Expected O(n+m) time.

```java
static int isAnagram(String a, String b) {
    if (a.length() != b.length()) return 0;
    HashMap<Character, Integer> freq = new HashMap<>();
    for (char c : a.toCharArray())
        freq.put(c, freq.getOrDefault(c, 0) + 1);
    for (char c : b.toCharArray())
        freq.put(c, freq.getOrDefault(c, 0) - 1);
    for (var e : freq.entrySet()) {
        if (e.getValue() != 0) return 0;
    }
    return 1;
}
```

**Built-in alternative — sort ASCII characters**

Sorting works for spaces, digits and uppercase letters too, and preserves case sensitivity. O(n log n) time; use the HashMap version when linear time is required.

```java
static int isAnagram(String a, String b) {
    if (a.length() != b.length()) return 0;
    char[] x = a.toCharArray();
    char[] y = b.toCharArray();
    Arrays.sort(x);
    Arrays.sort(y);
    return Arrays.equals(x, y) ? 1 : 0;
}
```

**Remember:** The supplied driver prints Yes/No; the method returns 1/0.

### 13. Upper to lower

**Question:** Convert uppercase English letters to lowercase; preserve digits, symbols and existing lowercase letters.

**Solution idea:** Use the standard lowercase method. O(n) time.

```java
static String upperToLower(String str) {
    return str.toLowerCase(Locale.ROOT);
}
```

**Basic alternative — ASCII conversion**

Check each character. Add 32 only for A–Z. This uses a loop, comparisons and a cast. Repeated concatenation can take O(n²) time.

```java
static String upperToLower(String str) {
    String ans = "";
    for (int i = 0; i < str.length(); i++) {
        char c = str.charAt(i);
        if (c >= 'A' && c <= 'Z') c = (char)(c + 32);
        ans += c;
    }
    return ans;
}
```

### 14. College Seats Are Reserved

**Question:** Two arrays contain reserved seat ages and applicant ages. Every applicant must match one seat of exactly the same age. Return YES only when all frequencies match. Ages are 1–100; N can be 100000.

**Solution idea:** Use a HashMap: add seat-age frequencies and subtract applicant-age frequencies. All counts must be zero. Expected O(n) time; O(k) space for k distinct ages (at most 100).

```java
static String seatsFilled(int[] a, int[] b) {
    if (a.length != b.length) return "NO";
    HashMap<Integer, Integer> freq = new HashMap<>();
    for (int age : a)
        freq.put(age, freq.getOrDefault(age, 0) + 1);
    for (int age : b)
        freq.put(age, freq.getOrDefault(age, 0) - 1);
    for (var e : freq.entrySet()) {
        if (e.getValue() != 0) return "NO";
    }
    return "YES";
}
```

**Built-in alternative — sort both age lists**

Sort copies and compare them. Matching sorted arrays have the same ages with the same counts. clone() preserves the original arrays. O(n log n) time and O(n) extra space.

```java
static String seatsFilled(int[] a, int[] b) {
    if (a.length != b.length) return "NO";
    int[] x = a.clone();
    int[] y = b.clone();
    Arrays.sort(x);
    Arrays.sort(y);
    return Arrays.equals(x, y) ? "YES" : "NO";
}
```

## 3. Practice Test

### 1. Emergency Light Grid

**Question:** For 1-based odd rows, first > last; for even rows, first < last. The last column must also satisfy row1 < row2 > row3 < row4 … . Return YES only if every strict comparison passes.

**Solution idea:** Use zero-based row parity to check row endpoints and the previous last-column value. O(N) checks after reading the matrix.

```java
static String emergencyLight(int[][] a) {
    int last = a[0].length - 1;
    for (int i = 0; i < a.length; i++) {
        if (i % 2 == 0) {
            if (a[i][0] <= a[i][last]) return "NO";
            if (i > 0 && a[i - 1][last] <= a[i][last]) return "NO";
        } else {
            if (a[i][0] >= a[i][last]) return "NO";
            if (a[i - 1][last] >= a[i][last]) return "NO";
        }
    }
    return "YES";
}
```

**Remember:** Source correction: its second sample is labelled NO, but satisfies both written rules and must be YES. This solution follows the rules.

### 2. The Security Scanner Matrix

**Question:** For 1-based odd rows, first < last; for even rows, first > last. The first column must satisfy row1 < row2 > row3 < row4 … . Return SECURE or UNSECURE.

**Solution idea:** Check endpoints and consecutive first-column values using row parity. Interior values do not matter. O(N) checks.

```java
static String securityScanner(int[][] a) {
    int last = a[0].length - 1;
    for (int i = 0; i < a.length; i++) {
        if (i % 2 == 0) {
            if (a[i][0] >= a[i][last]) return "UNSECURE";
            if (i > 0 && a[i - 1][0] <= a[i][0]) return "UNSECURE";
        } else {
            if (a[i][0] <= a[i][last]) return "UNSECURE";
            if (a[i - 1][0] >= a[i][0]) return "UNSECURE";
        }
    }
    return "SECURE";
}
```

### 3. The Power Consumption Audit

**Question:** Each 1-based odd row must have an even sum AND an even count of values strictly greater than 5. Each even row must have an odd sum AND an odd count. Return AUDIT PASS only if both checks pass for every row.

**Solution idea:** Compute sum and high-value count per row. Both parities must equal the zero-based row parity. O(NM) time.

```java
static String powerAudit(int[][] a) {
    for (int i = 0; i < a.length; i++) {
        int sum = 0, high = 0;
        for (int value : a[i]) {
            sum += value;
            if (value > 5) high++;
        }
        if (sum % 2 != i % 2 || high % 2 != i % 2) return "AUDIT FAIL";
    }
    return "AUDIT PASS";
}
```

**Remember:** Source correction: the first and third sample matrices fail the written rules despite being labelled AUDIT PASS. A valid example is [[2,4,6,8],[7,1,3,4]].

## 4. Abstract Class and Interface

### 1. Abstract Class - Shape

**Question:** Create abstract Shape(name) with abstract area() and display() printing name and area to 2 decimals. Circle uses pi=3.14159; Square uses side².

**Solution idea:** Keep common state/display in the parent; implement formulas in subclasses.

```java
abstract class Shape {
    protected String name;
    Shape(String name) { this.name = name; }
    public abstract double area();
    public void display() {
        System.out.printf(Locale.US, "%s %.2f%n", name, area());
    }
}
class Circle extends Shape {
    private double radius;
    Circle(double radius) { super("Circle"); this.radius = radius; }
    public double area() { return 3.14159 * radius * radius; }
}
class Square extends Shape {
    private double side;
    Square(double side) { super("Square"); this.side = side; }
    public double area() { return side * side; }
}
```

### 2. Interface - Animal

**Question:** Animal declares sound() and display(). Dog(name) prints "name barks"; Cat(name) prints "name meows". Each display() calls sound().

**Solution idea:** Implement every interface method as public.

```java
interface Animal {
    void sound();
    void display();
}
class Dog implements Animal {
    private String name;
    Dog(String name) { this.name = name; }
    public void sound() { System.out.println(name + " barks"); }
    public void display() { sound(); }
}
class Cat implements Animal {
    private String name;
    Cat(String name) { this.name = name; }
    public void sound() { System.out.println(name + " meows"); }
    public void display() { sound(); }
}
```

### 3. Abstract Class with Default and Abstract Method - Vehicle

**Question:** Abstract Vehicle stores brand and declares wheels(). start() prints "brand started"; info() prints "brand has N wheels". Car has 4 wheels; Bike has 2.

**Solution idea:** Concrete parent methods can call an overridden abstract method.

```java
abstract class Vehicle {
    protected String brand;
    Vehicle(String brand) { this.brand = brand; }
    public abstract int wheels();
    public void start() { System.out.println(brand + " started"); }
    public void info() { System.out.println(brand + " has " + wheels() + " wheels"); }
}
class Car extends Vehicle {
    Car(String brand) { super(brand); }
    public int wheels() { return 4; }
}
class Bike extends Vehicle {
    Bike(String brand) { super(brand); }
    public int wheels() { return 2; }
}
```

**Remember:** Here start()/info() are ordinary concrete class methods. The default keyword is used for interface methods, not these class methods.

### 4. Interface with Default Method - Logger

**Question:** Logger declares log(msg). Default logInfo(msg) calls log("[INFO] " + msg), and logError(msg) uses "[ERROR] ". ConsoleLogger prints the message.

**Solution idea:** Implement log() once; inherit both default helpers.

```java
interface Logger {
    void log(String msg);
    default void logInfo(String msg) { log("[INFO] " + msg); }
    default void logError(String msg) { log("[ERROR] " + msg); }
}
class ConsoleLogger implements Logger {
    public void log(String msg) { System.out.println(msg); }
}
```

### 5. Interface with Multiple Methods and Constants - Vehicle

**Question:** Vehicle declares FUEL_PRICE=100.0, maxSpeed(), fuelEfficiency(), and default costPerKm() = price/efficiency. Car: 180 and 15.0. Truck: 100 and 5.0.

**Solution idea:** Interface constants are implicitly public static final; abstract methods must be implemented.

```java
interface Vehicle {
    double FUEL_PRICE = 100.0;
    int maxSpeed();
    double fuelEfficiency();
    default double costPerKm() { return FUEL_PRICE / fuelEfficiency(); }
}
class Car implements Vehicle {
    public int maxSpeed() { return 180; }
    public double fuelEfficiency() { return 15.0; }
}
class Truck implements Vehicle {
    public int maxSpeed() { return 100; }
    public double fuelEfficiency() { return 5.0; }
}
```

**Remember:** Print speed as an integer, efficiency with 1 decimal, and cost with 2 decimals. Car cost → 6.67; truck cost → 20.00.

### 6. Implementing Multiple Interfaces - Smartphone

**Question:** Camera declares String capture(); MusicPlayer declares String play(song). Smartphone(model) implements both. Return "model captured photo", "model playing song", and "model ready" from capture(), play(), info().

**Solution idea:** A class may implement multiple interfaces.

```java
interface Camera { String capture(); }
interface MusicPlayer { String play(String song); }
class Smartphone implements Camera, MusicPlayer {
    private String model;
    Smartphone(String model) { this.model = model; }
    public String capture() { return model + " captured photo"; }
    public String play(String song) { return model + " playing " + song; }
    public String info() { return model + " ready"; }
}
```

### 7. Abstract Class vs Interface Choice - Report Generator

**Question:** Formatter declares format(String). Abstract Report stores title and double[] values, declares summary(), and displays "title | summary". SalesReport: sum with 2 decimals prefixed by $. InventoryReport: sum with 0 decimals suffixed by " units".

**Solution idea:** Use the abstract class for shared state and the interface for formatting behavior.

```java
interface Formatter { String format(String value); }
abstract class Report {
    protected String title;
    protected double[] values;
    Report(String title, double[] values) { this.title = title; this.values = values; }
    public abstract String summary();
    protected double sum() {
        double total = 0;
        for (double value : values) total += value;
        return total;
    }
    public void display() { System.out.println(title + " | " + summary()); }
}
class SalesReport extends Report implements Formatter {
    SalesReport(String title, double[] values) { super(title, values); }
    public String format(String value) { return "$" + value; }
    public String summary() { return format(String.format(Locale.US, "%.2f", sum())); }
}
class InventoryReport extends Report implements Formatter {
    InventoryReport(String title, double[] values) { super(title, values); }
    public String format(String value) { return value + " units"; }
    public String summary() { return format(String.format(Locale.US, "%.0f", sum())); }
}
```

### 8. Sensor Network Abstract Class with Arrays and Static Counters

**Question:** Calibratable declares calibrate(raw). Abstract Sensor(id,readings) stores data, increments a static total, provides average()/display(), and declares type()/calibratedAverage(). Temperature calibrates raw×1.05; Pressure calibrates raw+2. Print id type rawAverage calibratedAverage with 2 decimals. Counter accumulates across test cases.

**Solution idea:** Increment the counter in the root constructor once. For these linear calibration formulas, calibrating the average equals averaging calibrated readings.

```java
interface Calibratable { double calibrate(double raw); }
abstract class Sensor implements Calibratable {
    protected String id;
    protected double[] readings;
    private static int totalSensors;
    Sensor(String id, double[] readings) {
        this.id = id; this.readings = readings; totalSensors++;
    }
    public abstract String type();
    public abstract double calibratedAverage();
    public double average() {
        double sum = 0;
        for (double r : readings) sum += r;
        return sum / readings.length;
    }
    public void display() {
        System.out.printf(Locale.US, "%s %s %.2f %.2f%n",
                          id, type(), average(), calibratedAverage());
    }
    public static int getTotalSensors() { return totalSensors; }
}
class TemperatureSensor extends Sensor {
    TemperatureSensor(String id, double[] r) { super(id, r); }
    public String type() { return "Temperature"; }
    public double calibrate(double raw) { return raw * 1.05; }
    public double calibratedAverage() { return calibrate(average()); }
}
class PressureSensor extends Sensor {
    PressureSensor(String id, double[] r) { super(id, r); }
    public String type() { return "Pressure"; }
    public double calibrate(double raw) { return raw + 2.0; }
    public double calibratedAverage() { return calibrate(average()); }
}
```

**Remember:** A Sensor[] can hold both subclasses. After displaying a batch, print "Total Sensors: " + Sensor.getTotalSensors(). Readings are nonempty.

### 9. Interface Diamond Problem Simulation

**Question:** A has default hello() → "A". B and C extend A and override it to "B"/"C". D implements B,C and returns "D" from hello(); callB(), callC(), callA() return the corresponding parent behavior.

**Solution idea:** Override the conflicting default method. Use B.super and C.super; use a separate direct A implementer for the original A default.

```java
interface A { default String hello() { return "A"; } }
interface B extends A { default String hello() { return "B"; } }
interface C extends A { default String hello() { return "C"; } }
class D implements B, C {
    public String hello() { return "D"; }
    public String callB() { return B.super.hello(); }
    public String callC() { return C.super.hello(); }
    public String callA() { return new A() {}.hello(); }
}
```

**Remember:** D cannot call A.super.hello(): A is an indirect superinterface, and B/C override its default. The helper object delegates to A without an illegal super call.

### 10. Deep Abstract Hierarchy with Interface - Logistics

**Question:** Trackable declares trackingInfo(). Abstract Shipment(id,weight) tracks total shipments; declares baseCost()/category(); totalCost = baseCost + weight×10. ExpressShipment adds priority and category "Express". Overnight: base 100+priority×20. SameDay: base 200+priority×30. Tracking is "ID:id Type:Express" plus " Overnight" or " SameDay".

**Solution idea:** Share total cost/display in Shipment; override pricing and extend tracking with super.

```java
interface Trackable { String trackingInfo(); }
abstract class Shipment implements Trackable {
    protected String id;
    protected double weight;
    private static int totalShipments;
    Shipment(String id, double weight) {
        this.id = id; this.weight = weight; totalShipments++;
    }
    public abstract double baseCost();
    public abstract String category();
    public double totalCost() { return baseCost() + weight * 10; }
    public String trackingInfo() { return "ID:" + id + " Type:" + category(); }
    public void display() {
        System.out.printf(Locale.US, "%s %s %.2f %s%n",
                          id, category(), totalCost(), trackingInfo());
    }
    public static int getTotalShipments() { return totalShipments; }
}
abstract class ExpressShipment extends Shipment {
    protected int priority;
    ExpressShipment(String id, double weight, int priority) {
        super(id, weight); this.priority = priority;
    }
    public String category() { return "Express"; }
}
class OvernightShipment extends ExpressShipment {
    OvernightShipment(String id, double weight, int priority) { super(id, weight, priority); }
    public double baseCost() { return 100 + priority * 20; }
    public String trackingInfo() { return super.trackingInfo() + " Overnight"; }
}
class SameDayShipment extends ExpressShipment {
    SameDayShipment(String id, double weight, int priority) { super(id, weight, priority); }
    public double baseCost() { return 200 + priority * 30; }
    public String trackingInfo() { return super.trackingInfo() + " SameDay"; }
}
```

**Remember:** After each batch print "Total Shipments: " + Shipment.getTotalShipments(). Do not reset the static counter between test cases.

### 11. E-Learning Platform Abstract Class and Interface

**Question:** Gradable declares gradeFor(score). Abstract Course(code,title,enrollments) tracks total courses/enrollments and declares fees()/category(). Video fee=hours×500; Quiz=questions×20+200; Live=sessions×1000+500. Grades: 90+ A, 75+ B, 60+ C, 40+ D, otherwise F; outside [0,100] → INVALID. Display code title category fee grade, fee to 2 decimals.

**Solution idea:** Put grading and cumulative counters in Course; subclasses only provide category and fees.

```java
interface Gradable { String gradeFor(double score); }
abstract class Course implements Gradable {
    protected String code, title;
    protected int enrollments;
    private static int totalCourses, totalEnrollments;
    Course(String code, String title, int enrollments) {
        this.code = code; this.title = title; this.enrollments = enrollments;
        totalCourses++; totalEnrollments += enrollments;
    }
    public abstract double fees();
    public abstract String category();
    public String gradeFor(double score) {
        if (score < 0 || score > 100) return "INVALID";
        if (score >= 90) return "A";
        if (score >= 75) return "B";
        if (score >= 60) return "C";
        if (score >= 40) return "D";
        return "F";
    }
    public void display(double score) {
        System.out.printf(Locale.US, "%s %s %s %.2f %s%n",
                          code, title, category(), fees(), gradeFor(score));
    }
    public static int getTotalCourses() { return totalCourses; }
    public static int getTotalEnrollments() { return totalEnrollments; }
}
class VideoCourse extends Course {
    private int hours;
    VideoCourse(String c, String t, int e, int h) { super(c, t, e); hours = h; }
    public double fees() { return hours * 500.0; }
    public String category() { return "Video"; }
}
class QuizCourse extends Course {
    private int questions;
    QuizCourse(String c, String t, int e, int q) { super(c, t, e); questions = q; }
    public double fees() { return questions * 20.0 + 200; }
    public String category() { return "Quiz"; }
}
class LiveCourse extends Course {
    private int sessions;
    LiveCourse(String c, String t, int e, int s) { super(c, t, e); sessions = s; }
    public double fees() { return sessions * 1000.0 + 500; }
    public String category() { return "Live"; }
}
```

**Remember:** Store mixed types in Course[]. The same batch score is passed to every display(score). After each batch print Total Courses and Total Enrollments using the static getters; both accumulate.

## 5. Inheritance

### 1. Single Inheritance - Student and Graduate

**Question:** Student(name,roll) has protected fields and display() printing name roll. Graduate extends Student, stores private degree, and showDegree() prints name roll degree.

**Solution idea:** Initialize inherited fields with super(name,roll).

```java
class Student {
    protected String name;
    protected int roll;
    Student(String name, int roll) { this.name = name; this.roll = roll; }
    public void display() { System.out.println(name + " " + roll); }
}
class Graduate extends Student {
    private String degree;
    Graduate(String name, int roll, String degree) {
        super(name, roll); this.degree = degree;
    }
    public void showDegree() { System.out.println(name + " " + roll + " " + degree); }
}
```

### 2. Multilevel Inheritance - Vehicle, Car, Sports Car

**Question:** Vehicle stores protected brand and displays "Vehicle: brand". Car adds protected seats and displayCar(). SportsCar adds private topSpeed and displaySportsCar(). Constructors accept the fields in that order.

**Solution idea:** Each constructor calls the constructor one level above it.

```java
class Vehicle {
    protected String brand;
    Vehicle(String brand) { this.brand = brand; }
    public void display() { System.out.println("Vehicle: " + brand); }
}
class Car extends Vehicle {
    protected int seats;
    Car(String brand, int seats) { super(brand); this.seats = seats; }
    public void displayCar() { System.out.println("Car: " + brand + " Seats: " + seats); }
}
class SportsCar extends Car {
    private int topSpeed;
    SportsCar(String brand, int seats, int topSpeed) {
        super(brand, seats); this.topSpeed = topSpeed;
    }
    public void displaySportsCar() {
        System.out.println("SportsCar: " + brand + " Seats: " + seats + " TopSpeed: " + topSpeed);
    }
}
```

### 3. Hierarchical Inheritance - Animal, Dog, Cat

**Question:** Animal(name) has sound() printing "name makes a sound". Dog and Cat both extend Animal; bark() prints "name barks", meow() prints "name meows".

**Solution idea:** Two child classes reuse the same parent field. These added methods do not override sound().

```java
class Animal {
    protected String name;
    Animal(String name) { this.name = name; }
    public void sound() { System.out.println(name + " makes a sound"); }
}
class Dog extends Animal {
    Dog(String name) { super(name); }
    public void bark() { System.out.println(name + " barks"); }
}
class Cat extends Animal {
    Cat(String name) { super(name); }
    public void meow() { System.out.println(name + " meows"); }
}
```

### 4. Method Overriding - Shape, Circle, Square

**Question:** Concrete Shape.area() returns 0. Circle(radius) overrides area() as 3.14159×radius²; Square(side) overrides it as side². The caller prints the result with 2 decimals.

**Solution idea:** Keep exactly the same method signature when overriding. A Shape reference uses the actual object’s area().

```java
class Shape {
    public double area() { return 0; }
}
class Circle extends Shape {
    private double radius;
    Circle(double radius) { this.radius = radius; }
    @Override public double area() { return 3.14159 * radius * radius; }
}
class Square extends Shape {
    private double side;
    Square(double side) { this.side = side; }
    @Override public double area() { return side * side; }
}
```

### 5. Using super Keyword - Employee and Manager

**Question:** Employee(name,salary).display() prints name salary with 1 decimal. Manager adds bonus; its display() first prints the parent line, then "Bonus: bonus Total: salary+bonus", both numbers with 1 decimal.

**Solution idea:** Use super(...) for construction and super.display() to preserve parent output.

```java
class Employee {
    protected String name;
    protected double salary;
    Employee(String name, double salary) { this.name = name; this.salary = salary; }
    public void display() { System.out.printf(Locale.US, "%s %.1f%n", name, salary); }
}
class Manager extends Employee {
    private double bonus;
    Manager(String name, double salary, double bonus) {
        super(name, salary); this.bonus = bonus;
    }
    @Override public void display() {
        super.display();
        System.out.printf(Locale.US, "Bonus: %.1f Total: %.1f%n", bonus, salary + bonus);
    }
}
```

### 6. Constructor Chaining in Inheritance

**Question:** Person(name) constructor prints "Person constructor: name". Student(name,roll) then prints "Student constructor: name roll". Employee(name,salary) then prints "Employee constructor: name intSalary". Finally display() prints name roll or name salary with 1 decimal.

**Solution idea:** The parent constructor executes before the child constructor body. Cast salary to int only for the constructor message.

```java
class Person {
    protected String name;
    Person(String name) {
        this.name = name;
        System.out.println("Person constructor: " + name);
    }
}
class Student extends Person {
    private int roll;
    Student(String name, int roll) {
        super(name); this.roll = roll;
        System.out.println("Student constructor: " + name + " " + roll);
    }
    public void display() { System.out.println(name + " " + roll); }
}
class Employee extends Person {
    private double salary;
    Employee(String name, double salary) {
        super(name); this.salary = salary;
        System.out.println("Employee constructor: " + name + " " + (int)salary);
    }
    public void display() { System.out.printf(Locale.US, "%s %.1f%n", name, salary); }
}
```

### 7. Protected Access Modifier - Bank, SavingsAccount

**Question:** Bank(bankName,interestRate) has protected fields and display() printing bankName rate to 2 decimals. SavingsAccount adds holder and balance; computeInterest() prints bankName holder balance interest, where interest=balance×rate/100, both amounts to 2 decimals.

**Solution idea:** Access inherited protected fields directly inside the subclass.

```java
class Bank {
    protected String bankName;
    protected double interestRate;
    Bank(String bankName, double interestRate) {
        this.bankName = bankName; this.interestRate = interestRate;
    }
    public void display() {
        System.out.printf(Locale.US, "%s %.2f%n", bankName, interestRate);
    }
}
class SavingsAccount extends Bank {
    private String holder;
    private double balance;
    SavingsAccount(String bankName, double rate, String holder, double balance) {
        super(bankName, rate); this.holder = holder; this.balance = balance;
    }
    public void computeInterest() {
        System.out.printf(Locale.US, "%s %s %.2f %.2f%n",
                          bankName, holder, balance, balance * interestRate / 100);
    }
}
```

### 8. Employee Hierarchy - Manager and Developer

**Question:** Employee(name,baseSalary).computeSalary() returns base. Manager adds teamSize×5000. Developer multiplies base by 1.20 for Java, 1.15 for Python, 1.10 otherwise. Inherited display() prints name computedSalary with 2 decimals.

**Solution idea:** Override the salary calculation; let the parent display() call it polymorphically.

```java
class Employee {
    protected String name;
    protected double baseSalary;
    Employee(String name, double baseSalary) { this.name = name; this.baseSalary = baseSalary; }
    public double computeSalary() { return baseSalary; }
    public void display() {
        System.out.printf(Locale.US, "%s %.2f%n", name, computeSalary());
    }
}
class Manager extends Employee {
    private int teamSize;
    Manager(String name, double baseSalary, int teamSize) {
        super(name, baseSalary); this.teamSize = teamSize;
    }
    public double computeSalary() { return baseSalary + teamSize * 5000; }
}
class Developer extends Employee {
    private String language;
    Developer(String name, double baseSalary, String language) {
        super(name, baseSalary); this.language = language;
    }
    public double computeSalary() {
        double multiplier = language.equals("Java") ? 1.20 :
                            language.equals("Python") ? 1.15 : 1.10;
        return baseSalary * multiplier;
    }
}
```

**Remember:** Use .equals() to compare String contents, not ==.

### 9. Four-Level Hierarchy with Salary Deductions

**Question:** Person → Employee → SeniorEmployee → Director. Employee: gross=base, deduction=10% of gross. Senior: gross=base+bonus, deduction=15%. Director: gross=base+bonus+stock, deduction=20%. net=gross−deductions. Print name gross deductions net with 2 decimals.

**Solution idea:** Override grossSalary()/deductions(); reuse inherited netSalary()/display().

```java
class Person {
    protected String name;
    Person(String name) { this.name = name; }
}
class Employee extends Person {
    protected double baseSalary;
    Employee(String name, double baseSalary) { super(name); this.baseSalary = baseSalary; }
    public double grossSalary() { return baseSalary; }
    public double deductions() { return grossSalary() * 0.10; }
    public double netSalary() { return grossSalary() - deductions(); }
    public void display() {
        System.out.printf(Locale.US, "%s %.2f %.2f %.2f%n",
                          name, grossSalary(), deductions(), netSalary());
    }
}
class SeniorEmployee extends Employee {
    protected double performanceBonus;
    SeniorEmployee(String name, double base, double bonus) {
        super(name, base); performanceBonus = bonus;
    }
    public double grossSalary() { return super.grossSalary() + performanceBonus; }
    public double deductions() { return grossSalary() * 0.15; }
}
class Director extends SeniorEmployee {
    private double stockOptions;
    Director(String name, double base, double bonus, double stock) {
        super(name, base, bonus); stockOptions = stock;
    }
    public double grossSalary() { return super.grossSalary() + stockOptions; }
    public double deductions() { return grossSalary() * 0.20; }
}
```

### 10. Overloading vs Overriding Side by Side

**Question:** Calculator overloads compute(int,int), compute(int,int,int), compute(double,double) to add arguments; describe() returns "Basic Calculator". ScientificCalculator overrides describe() to "Scientific Calculator" and adds compute(int) for square and compute(double) for square root.

**Solution idea:** Overloading changes parameters; overriding keeps the signature and changes the implementation.

```java
class Calculator {
    public int compute(int a, int b) { return a + b; }
    public int compute(int a, int b, int c) { return a + b + c; }
    public double compute(double a, double b) { return a + b; }
    public String describe() { return "Basic Calculator"; }
}
class ScientificCalculator extends Calculator {
    @Override public String describe() { return "Scientific Calculator"; }
    public int compute(int a) { return a * a; }
    public double compute(double a) { return Math.sqrt(a); }
}
```

**Remember:** compute(9) → 81, but compute(9.0) → 3.0. Overload selection depends on argument types at compile time. A Calculator reference cannot directly call the new one-argument overloads.

### 11. Deep Hierarchy with Arrays

**Question:** Person → Employee, with Intern/Manager extending Employee. Employee pay=salary; Intern pay=hours×100; Manager pay=salary+teamSize×2000. display() prints name pay to 2 decimals. A static employee counter counts all three types and accumulates across batches.

**Solution idea:** Increment the counter only in Employee’s constructor; use Employee[] for mixed types.

```java
class Person {
    protected String name;
    Person(String name) { this.name = name; }
}
class Employee extends Person {
    protected double salary;
    private static int totalEmployees;
    Employee(String name, double salary) {
        super(name); this.salary = salary; totalEmployees++;
    }
    public double computePay() { return salary; }
    public void display() { System.out.printf(Locale.US, "%s %.2f%n", name, computePay()); }
    public static int getTotalEmployees() { return totalEmployees; }
}
class Intern extends Employee {
    private int hours;
    Intern(String name, int hours) { super(name, 0); this.hours = hours; }
    public double computePay() { return hours * 100.0; }
}
class Manager extends Employee {
    private int teamSize;
    Manager(String name, double salary, int teamSize) {
        super(name, salary); this.teamSize = teamSize;
    }
    public double computePay() { return salary + teamSize * 2000; }
}
```

**Remember:** After each array is displayed, print "Total Employees: " + Employee.getTotalEmployees().

### 12. E-commerce Order System with Inheritance

**Question:** Order(orderId,baseAmount) has computeFinalAmount()/display() and cumulative totalOrders/totalRevenue getters. PhysicalOrder adds shippingFee: (base+shipping)×1.18. DigitalOrder adds licenseType: Single×1.05, Team×1.10, Enterprise×1.15. Print orderId finalAmount to 2 decimals, then cumulative totals after each batch.

**Solution idea:** Count objects in the base constructor. Register base revenue there and add the subclass difference only after subclass fields are initialized.

```java
class Order {
    protected String orderId;
    protected double baseAmount;
    private static int totalOrders;
    private static double totalRevenue;
    Order(String orderId, double baseAmount) {
        this.orderId = orderId; this.baseAmount = baseAmount;
        totalOrders++;
        totalRevenue += baseAmount;
    }
    protected final void adjustRevenue(double difference) { totalRevenue += difference; }
    public double computeFinalAmount() { return baseAmount; }
    public void display() {
        System.out.printf(Locale.US, "%s %.2f%n", orderId, computeFinalAmount());
    }
    public static int getTotalOrders() { return totalOrders; }
    public static double getTotalRevenue() { return totalRevenue; }
}
class PhysicalOrder extends Order {
    private double shippingFee;
    PhysicalOrder(String id, double base, double shippingFee) {
        super(id, base); this.shippingFee = shippingFee;
        adjustRevenue(computeFinalAmount() - baseAmount);
    }
    public double computeFinalAmount() { return (baseAmount + shippingFee) * 1.18; }
}
class DigitalOrder extends Order {
    private String licenseType;
    DigitalOrder(String id, double base, String licenseType) {
        super(id, base); this.licenseType = licenseType;
        adjustRevenue(computeFinalAmount() - baseAmount);
    }
    public double computeFinalAmount() {
        double rate = "Single".equals(licenseType) ? 1.05 :
                      "Team".equals(licenseType) ? 1.10 : 1.15;
        return baseAmount * rate;
    }
}
```

**Remember:** Source correction: Java can dispatch to an override during a parent constructor, but subclass fields still have default values then (0/null). Calling computeFinalAmount() there gives incorrect revenue or can throw. This corrected version preserves the intended totals. O1=(100+20)×1.18=141.60; O2=200×1.05=210.00; cumulative revenue=351.60. Print Total Orders and Total Revenue using the getters; format revenue to 2 decimals.

## Quick revision reminders

- Clockwise rotation: transpose + reverse rows. Counter-clockwise: transpose + reverse columns.
- Frequency questions: `freq.put(key, freq.getOrDefault(key, 0) + 1)` counts occurrences. Iterate map entries with `for (var e : freq.entrySet())`; `e.getKey()` is the key and `e.getValue()` is the frequency. Use TreeMap for alphabetical output. For the first unique character/index, scan the original string after counting because HashMap does not preserve order.
- Zero-based even row indices correspond to 1-based odd row numbers.
- `extends` inherits a class; `implements` adopts interface contracts. A Java class has one direct superclass and may implement multiple interfaces.
- Interface methods implemented by a class must be `public`. Abstract classes can hold instance fields and constructors; interfaces have constants and may have default/static methods.
- `super(...)` initializes parent state. `super.method()` calls the parent implementation.
- Overriding: same signature, runtime dispatch. Overloading: different parameter lists, compile-time selection.
- A static counter is shared by all instances. Increment it once in the common constructor and do not reset it between test cases when cumulative totals are required.
- Avoid calling overridable methods from constructors when they depend on subclass fields.
- `System.out.printf(Locale.US, "%.2f%n", value)` prints 2 decimal places; `%.1f` prints 1.
