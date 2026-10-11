import java.util.*;

class batch {
    int id;
    int m;
    int n;
    int[][] marks;

    batch(int id, int m, int n, int[][] marks) {
        this.id = id;
        this.m = m;
        this.n = n;
        this.marks = marks;
    }

    public void displayMarks() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(marks[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int totalMarks() {
        int total = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += marks[i][j];
            }
        }
        return total;
    }

    public int highestMark() {
        int highest = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                highest = Math.max(highest, marks[i][j]);
            }
        }
        return highest;
    }
    /* 
    public void sortMarks() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 1; j++) {
                for (int k = 0; k < n - j - 1; k++) {
                    if (marks[i][k] > marks[i][k + 1]) {
                        int t = marks[i][k];
                        marks[i][k] = marks[i][k + 1];
                        marks[i][k + 1] = t;
                    }
                }
            }
        }
    }



*/ }


class batchtwo extends batch {
    batchtwo(int id, int m, int n, int[][] marks) {
        super(id, m, n, marks);
        HashMap<batch,Integer> map = new HashMap<>();
        map.put(this, totalMarks());
    }
public void displayMarks() {
        System.out.println("Marks for Batch ID: " + id);
        super.displayMarks();

    }}




public class batchone {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] marks = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                marks[i][j] = sc.nextInt();
            }
        }

        batch b1 = new batch(1, m, n, marks);
        System.out.println("Marks for Batch ID: " + b1.id);
        System.out.println("Total Marks: " + b1.totalMarks());
        System.out.println("Highest Mark: " + b1.highestMark());

        // b1.sortMarks();
        b1.displayMarks();

        sc.close();
    }
}