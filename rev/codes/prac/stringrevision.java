import java.util.Scanner;

public class stringrevision{
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String x=sc.nextLine();
sc.close();
        // track length
        int cnt=0;
        for(char c:x.toCharArray()){
            cnt++;}
                System.out.println(cnt);
        //reverse a string
        String rev="";
        for(int i=x.length()-1;i>=0;i--){
rev+=x.charAt(i);
        }        System.out.println(rev);


// cnt vowels and consonants
int vw=0;
for(int i=0;i<x.length();i++){
    char ch=x.charAt(i);
    if(ch>='a' && ch<='z'){
        if(ch=='a'|| ch=='e'||ch=='i'||ch=='o'||ch=='u'){
            vw++;
        }
    }

}
System.out.println(vw);



        }
}