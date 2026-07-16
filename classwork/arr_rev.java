public class arr_rev {
    public static void main(String[] args){
        int[] arr={1,2,3,4,5};
        // array reversal- 2 pointer and extra space rev array creation methods
        int l=0;
        int r=arr.length-1;
        while(l<r){
            int temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;
            l++;
            r--;
        }
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
        
            int[] rev=new int[5];
for(int i=0;i<5;i++){
    rev[i]=arr[5-i-1];
    System.out.println(rev[i]);
}
    }
}
