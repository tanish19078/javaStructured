import java.util.*;

public class slidingwndw {

/*
function maxSumSubarray(arr, k):
    windowSum = sum of arr[0..k-1]
    maxSum = windowSum
    for i from k to n-1:
        windowSum = windowSum + arr[i] - arr[i-k]
        maxSum = max(maxSum, windowSum)
    return maxSum

*/

    static void maxsubarrsum(int arr[], int n, int k) {
        int max_sum = 0;
        for (int i = 0; i < k; i++)
            max_sum += arr[i];
        int window_sum = max_sum;
        for (int i = k; i < n; i++) {
            window_sum += arr[i] - arr[i - k];
            max_sum = Math.max(max_sum, window_sum);
        }
        System.out.println("Maximum subarray sum: " + max_sum);
    }

static void avgsubarrsum(int arr[], int n, int k) {
    int sum = 0;
    for (int i = 0; i < k; i++)
        sum += arr[i];
    
    double avg = (double) sum / k;
    System.out.println("Average of subarray [0, " + (k - 1) + "]: " + avg);
    
    int window_sum = sum;
    for (int i = k; i < n; i++) {
        window_sum += arr[i] - arr[i - k];
        avg = (double) window_sum / k;
        System.out.println("Average of subarray [" + (i - k + 1) + ", " + i + "]: " + avg);
    }
}

static void dssubarr(int arr[],int n, int k){

HashMap<Integer,Integer> map = new HashMap<>();
for(int i=0;i<k;i++){
    map.put(arr[i],map.getOrDefault(arr[i],0)+1);
    if(map.size()==k){
        System.out.print("[");
        for(int j=i-k+1;j<=i;j++){
            System.out.print(arr[j]);
            if(j<i) System.out.print(" ");
        }
        System.out.print("] ");
    }
}}

static void distinctsubarr(int arr[], int n, int k) {
    for (int i = 0; i <= n - k; i++) {
        boolean distinct = true;
        for (int j = i; j < i + k; j++) {
            for (int l = j + 1; l < i + k; l++) {
                if (arr[j] == arr[l]) {
                    distinct = false;
                    break;
                }
            }
            if (!distinct) break;
        }
        if (distinct) {
            System.out.print("[");
            for (int j = i; j < i + k; j++) {
                System.out.print(arr[j]);
                if (j < i + k - 1) System.out.print(" ");
            }
            System.out.print("] ");
        }
    }}



    static void printsubarr(int arr[], int n, int k) {
        int l = 0;
        int r = k - 1;
        while (r < arr.length) {
            System.out.print("[");
            for (int i = l; i <= r; i++) {
                System.out.print(arr[i]);
                if (i < r) System.out.print(" ");
            }
            System.out.print("] ");
            l++;
            r++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8,7,8};
        int k = 3;
        int n = arr.length;
        dssubarr(arr, n, k);
        distinctsubarr(arr, n, k);
        maxsubarrsum(arr, n, k);
        avgsubarrsum(arr, n, k);
        printsubarr(arr, n, k);
    }
}