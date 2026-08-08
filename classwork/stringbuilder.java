public class stringbuilder {
    public static void main(String[] args) {
        
        String st="GM"; // string literal
        String st1="GM";
        String x=new String("GM");
        System.out.println(st==st1);
        System.out.println(st.equals(st1));
        System.out.println(x.equals(st1));
String xy="TREEEE";
int cnt=0;
for(int i=0;i<xy.length();i++){
    if(xy.toLowerCase().charAt(i)=='e'){cnt++;}
}
System.out.println(cnt);

/*  strings immutability and use of + operand for concatenation-
doesnt modifies original strings, just reads value and creates a brand new String object in memory
*/

xy.split("T");
String sx="10 20 40 5 60 21 25 656";
String[] arr=sx.split(" ");
int sum=0;
for(int i=0;i<arr.length;i++){
    sum+=Integer.parseInt(arr[i]);
}
System.out.println(sum);

// capitalize string and reverse string
String t1="this is a fuckin beautiful friday man";
String trev="";
for(int i=t1.length()-1;i>0;i--){
    trev+=t1.charAt(i);
}
System.err.println(trev);
// xy.replace();
xy.replaceAll(x, trev);
xy.substring(0,3);

}}
