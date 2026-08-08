import java.util.List;
import java.util.ArrayList;
public class binarysearch{
    public static void main(String[] args) {
        int[] arr={0,1,2,4,5,6,7,8,9};
        int target=5;
        bsr(arr, target);
        List<Integer> xi = new ArrayList<>();
        xi.add(10);

        
    }


static int bsr(int[] arr,int target){
    int l=0;
    int h=arr.length-1;
    while(l<=h){
        int mid=l+(h-l)/2;
        if(arr[mid]==target){return mid;}
        else if(arr[mid]<target){l=mid+1;}
        else{h=mid-1;}
    }
    return -1;
}}