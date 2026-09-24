
/*
class Result {
    static void printSpiral(int a[][], int r, int c) {
        int t = 0;        // top row boundary
        int b = r - 1;    // bottom row boundary
        int l = 0;        // left column boundary
        int rg = c - 1;   // right column boundary

        while (t <= b && l <= rg) {
            // 1. Move Left -> Right
            for (int i = l; i <= rg; i++) {
                System.out.println(a[t][i]);
            }
            t++;

            // 2. Move Top -> Bottom
            for (int i = t; i <= b; i++) {
                System.out.println(a[i][rg]);
            }
            rg--;

            // 3. Move Right -> Left
            if (t <= b) {
                for (int i = rg; i >= l; i--) {
                    System.out.println(a[b][i]);
                }
                b--;
            }

            // 4. Move Bottom -> Top
            if (l <= rg) {
                for (int i = b; i >= t; i--) {
                    System.out.println(a[i][l]);
                }
                l++;
            }
        }
    }
}
*/
import java.util.Scanner;
public class mat2d {
    public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int[][] mat=new int[r][c];
for(int i=0;i<r;i++){
    for(int j=0;j<c;j++){
mat[i][j]=sc.nextInt();
    }
}
int rx=sc.nextInt();
int cx=sc.nextInt();
int rs=0;int cs=0;
for(int i=0;i<r;i++){
    rs+=mat[rx][i];
}
for(int j=0;j<c;j++){
    cs+=mat[j][cx];
}
for(int i=0;i<r;i++){
    for(int j=0;j<c;j++){
        int sum=0;
        for(int k=0;k<r;k++){
            sum+=mat[i][k]*mat[k][j];
        }
        System.out.print(sum+" ");
    }
    System.out.println();
}
System.out.println(rs+cs);
sc.close();

    }}