public class multidimensionarr{
    public static void main(String[] args){
        // int[] arr={1,3,4,2,4,5,5,3};
        //random access in arr allows us to access elements by index in any way;
        //  jvm calculates the refrence adress formula=base adr+(index)*(datatype size);
        //this helps one to achieve O(1) time access in array;
    //        System.out.println(arr[7]);
    // int[][] brr=new int[5][];
    // brr[0]=new int[2];
    // brr[0][0]=36;
    // System.out.println(brr[1][0]);

    // creation of 3d array and printing sum of elements;
int[][][] abc={{{1,2},{3,7},{6,8}},{{3,2},{4,8},{4,2}}};
int sum=0;
    for(int i=0;i<abc.length;i++){
        for(int j=0;j<abc[i].length;j++){
            for(int k=0;k<abc[i][j].length;k++){
        sum+=abc[i][j][k];

    }
     }
}
System.out.println(sum);
// absolute difference of diagonals - sum of 2 diagonals of 4x4 matrix and then return mod ld-rd
int[][] xy={{2,3,1,6},{4,-8,8,4},{6,1,8,8},{-2,0,9,11}};
int[][]ab={{1,4,3},{9,-8,6},{2,7,9}};
int ld=0;
int rd=0;
int n=xy.length;
for(int i=0;i<n;i++){
ld+=xy[i][i];
rd+=xy[i][n-i-1];
    }
System.out.println( Math.abs(ld-rd));

int ls=0;int rs=0;int x=ab.length;
for(int k=0;k<x;k++){
    ls+=ab[k][k];
    rs+=ab[k][x-k-1];
}
System.out.println( Math.abs(ls-rs));
}}

// return border elements appended into new array

public static int[] border(int[][] arr){
    int m=arr.length;
    int n=arr[0].length;
    int[] b=new int[2*(m+n)-4];
    int idx=0;
    for (int j = 0; j < n; j++) {
        b[idx++] = arr[0][j];
    }

    for (int i = 1; i < m; i++) {
        b[idx++] = arr[i][n - 1];
    }

    for (int j = n - 2; j >= 0; j--) {
        b[idx++] = arr[m - 1][j];
    }

    for (int i = m - 2; i > 0; i--) {
        b[idx++] = arr[i][0];
    }

    return b;
}