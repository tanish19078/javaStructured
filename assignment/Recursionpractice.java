public class Recursionpractice {
    
    void p1(int n) {
        if (n == 0) return;
        p1(n - 1);
        System.out.print(n + " ");
    }
    
    void p2(int n) {
        if (n == 0) return;
        System.out.print(n + " ");
        p2(n - 1);
    }
    
    int p3(int n) {
        if (n <= 1) return 1;
        return n * p3(n - 1);
    }
    
    int p4(int n) {
        if (n == 0) return 0;
        return n + p4(n - 1);
    }
    
    int p5(int n) {
        if (n == 0) return 0;
        return n % 10 + p5(n / 10);
    }
    
    int p6(int n) {
        if (n == 0) return 0;
        return 1 + p6(n / 10);
    }
    
    int p7(int n, int r) {
        if (n == 0) return r;
        return p7(n / 10, r * 10 + n % 10);
    }
    
    void p8(int[] a, int i) {
        if (i == a.length) return;
        System.out.print(a[i] + " ");
        p8(a, i + 1);
    }
    
    void p9(int[] a, int i) {
        if (i == a.length) return;
        p9(a, i + 1);
        System.out.print(a[i] + " ");
    }
    
    int p10(int[] a, int i) {
        if (i == a.length) return 0;
        return a[i] + p10(a, i + 1);
    }
    
    int p11(int[] a, int i) {
        if (i == a.length - 1) return a[i];
        int m = p11(a, i + 1);
        return a[i] > m ? a[i] : m;
    }
    
    int p12(int[] a, int i) {
        if (i == a.length - 1) return a[i];
        int m = p12(a, i + 1);
        return a[i] < m ? a[i] : m;
    }
    
    int p13(int[] a, int i, int x) {
        if (i == a.length) return 0;
        int c = a[i] == x ? 1 : 0;
        return c + p13(a, i + 1, x);
    }
    
    boolean p14(int[] a, int i) {
        if (i == a.length - 1) return true;
        if (a[i] > a[i + 1]) return false;
        return p14(a, i + 1);
    }
    
    void p15(String s, int i) {
        if (i == s.length()) return;
        System.out.println(s.charAt(i));
        p15(s, i + 1);
    }
    
    int p16(String s, int i) {
        if (i == s.length()) return 0;
        char c = s.charAt(i);
        int v = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || 
                 c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') ? 1 : 0;
        return v + p16(s, i + 1);
    }
    
    int p17(String s, int i, char x) {
        if (i == s.length()) return 0;
        int c = s.charAt(i) == x ? 1 : 0;
        return c + p17(s, i + 1, x);
    }
    
    String p18(String s) {
        if (s.length() <= 1) return s;
        return p18(s.substring(1)) + s.charAt(0);
    }
    
    boolean p19(String s, int l, int r) {
        if (l >= r) return true;
        if (s.charAt(l) != s.charAt(r)) return false;
        return p19(s, l + 1, r - 1);
    }
    
    int p20(String s, int i) {
        if (i == s.length()) return 0;
        int c = Character.isUpperCase(s.charAt(i)) ? 1 : 0;
        return c + p20(s, i + 1);
    }
    
    public static void main(String[] args) {
        Recursion r = new Recursion();
        
        System.out.print("1: "); r.p1(5); System.out.println();
        System.out.print("2: "); r.p2(5); System.out.println();
        System.out.println("3: " + r.p3(5));
        System.out.println("4: " + r.p4(5));
        System.out.println("5: " + r.p5(5234));
        System.out.println("6: " + r.p6(987654));
        System.out.println("7: " + r.p7(12345, 0));
        
        int[] a = {3, 8, 1, 6};
        System.out.print("8: "); r.p8(a, 0); System.out.println();
        System.out.print("9: "); r.p9(a, 0); System.out.println();
        System.out.println("10: " + r.p10(a, 0));
        System.out.println("11: " + r.p11(a, 0));
        System.out.println("12: " + r.p12(a, 0));
        int[] b = {2, 5, 2, 7, 2};
        System.out.println("13: " + r.p13(b, 0, 2));
        int[] c = {1, 2, 3};
        System.out.println("14: " + (r.p14(c, 0) ? "Sorted" : "Not Sorted"));
        
        System.out.println("15:"); r.p15("Hello", 0);
        System.out.println("16: " + r.p16("Programming", 0));
        System.out.println("17: " + r.p17("banana", 0, 'a'));
        System.out.println("18: " + r.p18("Hello"));
        System.out.println("19: " + (r.p19("madam", 0, 4) ? "Palindrome" : "Not Palindrome"));
        System.out.println("20: " + r.p20("HeLLo", 0));
    }
}