// int a;a=Null;System.out.println(a);
// String y;y=Null;System.out.println(y);

// null is itself a object which doesnt points to anything.......

class ucampusclient{
    String name;
    String num;
    int id;
    String message;
    int sendotp(){
        double otp=Math.random();
        int otpf=(int)(otp*10000);
        return (otpf >= 1000 && otpf<10000) ? otpf : sendotp();

        }
    }

public class day7_sort {
    int sort(){
        int[] a={0,1,2,2,1,0,2,2,1,1};
        int l=0;
        int m=0;
        int h=a.length-1;
        while(m<=h){
            if(a[m]==0){
                // swap 
                int temp=a[m];
                a[m]=a[l];
                a[l]=temp;
                m++;
                l++;
            }
            else if(a[m]==1){
                m++;
            }
            else if(a[m]==2){
                int temp=a[m];
                a[m]=a[h];
                a[h]=temp;
                h--;
            }
        }
        return 0;
    }
    public static void main(String[] args){
        int[] arr={0,1,0,0,1};
        int cnt=0;
        //push 1s to back and 0s to front
        // double pass using cnt variable

        // now for array with 0,1,2 and in single pass
        for(int i=0;i<arr.length;i++){
            if (arr[i]==0){
                cnt++;}}
        for (int t = 0; t < arr.length; t++) {
    if (t < cnt) {
        arr[t] = 0;}
         else {
        arr[t] = 1;}}
        for(int j=0;j<arr.length;j++){
    System.out.println(arr[j]);}
}}

    


