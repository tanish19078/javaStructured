import java.util.Scanner;

public class str2 {

    static int lenoflastword(String x){
        String[] lw=x.split(" ");
        // return lw[lw.length-1].length();

        //track white space and length
        int i=x.length()-1;
        int cnt=0;
        while(i>=0 && x.charAt(i)==' '){
            i--;
        }
        while(i>=0 && x.charAt(i)!=' '){
            cnt++;
            i--;
        }
        return cnt;
    }

    static void rev(char[] a){
    int l=0;
    int r=a.length-1;
    while(l<=r){
        char temp=a[l];
        a[l]=a[r];
        a[r]=temp;
        l++;
        r--;
    }}

    static boolean ispalindrome(String x){
x=x.toLowerCase().trim();
StringBuilder sb=new StringBuilder(x);
sb.reverse();
return x.equals(sb.toString());
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String t=sc.nextLine();
        System.out.println(ispalindrome(t));
        System.out.println(lenoflastword(t));

        sc.close();
    }
}
