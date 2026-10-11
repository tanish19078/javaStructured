import java.io.*;
import java.util.*;

public class ProjectAllocation {
    private int[][] hours;
    private int n, m;

    public ProjectAllocation(int n, int m, Scanner sc) {
        this.n = n;
        this.m = m;
        hours = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                hours[i][j] = sc.nextInt();
            }
        }
    }

    public void displayEmployeeReport() {
        System.out.println("Employee report:");
        System.out.print("\t");
        for (int j = 0; j < m; j++) {
            System.out.print("P" + (j + 1) + "\t");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print("E" + (i + 1) + "\t");
            for (int j = 0; j < m; j++) {
                System.out.print(hours[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void calculateEmployeeTotal() {
        for (int i = 0; i < n; i++) {
            long s = 0;
            for (int j = 0; j < m; j++) {
                s += hours[i][j];
            }
            System.out.println("Employee E" + (i + 1) + " total: " + s + " hours");
        }
    }

    public void generateProjectReport() {
        int[][] a = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[j][i] = hours[i][j];
            }
        }

        System.out.println("Project report:");
        System.out.print("\t");
        for (int j = 0; j < n; j++) {
            System.out.print("E" + (j + 1) + "\t");
        }
        System.out.println();

        for (int i = 0; i < m; i++) {
            System.out.print("P" + (i + 1) + "\t");
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private long total(int j) {
        long s = 0;
        for (int i = 0; i < n; i++) {
            s += hours[i][j];
        }
        return s;
    }

    public void calculateProjectTotal() {
        for (int j = 0; j < m; j++) {
            System.out.println("Project P" + (j + 1) + " total: " + total(j) + " hours");
        }
    }

    public void findMostWorkedProject() {
        int p = 0;
        long mx = total(0);

        for (int j = 1; j < m; j++) {
            long s = total(j);
            if (s > mx) {
                mx = s;
                p = j;
            }
        }

        System.out.println("Most worked project: P" + (p + 1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter employee count and project count:");
        int n = sc.nextInt(), m = sc.nextInt();

        if (n <= 0 || m <= 0) {
            System.out.println("Counts must be positive.");
            sc.close();
            return;
        }

        System.out.println("Enter working hours:");
        ProjectAllocation p = new ProjectAllocation(n, m, sc);
        p.displayEmployeeReport();
        p.calculateEmployeeTotal();
        p.generateProjectReport();
        p.calculateProjectTotal();
        p.findMostWorkedProject();
        sc.close();
    }
}