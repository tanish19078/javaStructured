import java.util.*;

public class twodimmat {

static int[][] transpose(int[][] x){
    int r=x.length;
    int c=x[0].length;
    int[][] tr=new int[c][r];
    for(int i=0;i<r;i++){
        for(int j=0;j<c;j++){
            tr[j][i]=x[i][j];
        }
    }
    return tr;
}

static int rscs(int[][] x){
    int r=x.length;
    int rs1=0;int cs1=0;
    int row=2;
    int col=2;
    for(int i=0;i<r;i++){
        cs1+=x[i][col];
    }
    for(int i=0;i<x[0].length;i++){
        rs1+=x[row][i];
    }
    return rs1;
}

static int maxrowsum(int[][] x){
    int r=x.length;
    int c=x[0].length;
    int max=0;
    for(int i=0;i<r;i++){
        int rowsum=0;
        for(int j=0;j<c;j++){
            rowsum+=x[i][j];
        }
        if(rowsum>max){
            max=rowsum;
        }
    }
    return max;
}

static int[][] reshapemat(int[][]x){
    int r=x.length;
    int c=x[0].length;
    int k=0;
    int newr=1;
    int newc=4;
    int[][] res=new int[newr][newc];
    for(int i=0;i<r;i++){
        for(int j=0;j<c;j++){
res[k/newc][k%newc]=x[i][j];
k++;
        }
    }
    return res;
}
static int diagonalsum(int[][] x){
    int n=x.length;
    int sum=0;
    for(int i=0;i<n;i++){
        sum+=x[i][i];
        sum+=x[i][n-i-1];
    }
    // if matrix is odd we count centre twice
    if(n%2==1){
        sum-=x[n/2][n/2];
    }
    return sum;

}

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int r=sc.nextInt();
        int c=sc.nextInt();
        int[][] mat=new int[r][c];
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                mat[i][j]=sc.nextInt();
            }
        }
        int[][] tr = transpose(mat);
        System.out.println(diagonalsum(mat));

for (int i = 0; i < tr.length; i++) {
    for (int j = 0; j < tr[0].length; j++) {
        System.out.print(tr[i][j] + " ");
    }
    System.out.println();
}
    }
}
