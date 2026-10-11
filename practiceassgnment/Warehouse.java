import java.io.*;
import java.util.*;

public class Warehouse {
    private int[][] inventory;
    private int n;

    public Warehouse(int n, Scanner sc) {
        this.n = n;
        inventory = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inventory[i][j] = sc.nextInt();
            }
        }
    }

    public void displayInventory() {
        System.out.println("Inventory:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(inventory[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void getRobotVisitOrder() {
        int t = 0, b = n - 1, l = 0, r = n - 1;
        System.out.println("Robot visit order:");

        while (t <= b && l <= r) {
            for (int j = l; j <= r; j++) {
                System.out.print(inventory[t][j] + " ");
            }
            t++;

            for (int i = t; i <= b; i++) {
                System.out.print(inventory[i][r] + " ");
            }
            r--;

            if (t <= b) {
                for (int j = r; j >= l; j--) {
                    System.out.print(inventory[b][j] + " ");
                }
                b--;
            }

            if (l <= r) {
                for (int i = b; i >= t; i--) {
                    System.out.print(inventory[i][l] + " ");
                }
                l++;
            }
        }
        System.out.println();
    }

    public long calculateBoundaryStock() {
        long s = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
                    s += inventory[i][j];
                }
            }
        }
        return s;
    }

    public void findMaximumSection() {
        int mx = inventory[0][0], r = 0, c = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (inventory[i][j] > mx) {
                    mx = inventory[i][j];
                    r = i;
                    c = j;
                }
            }
        }
        System.out.println("Maximum stock: " + mx);
        System.out.println("Section: row " + (r + 1) + ", column " + (c + 1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter grid size:");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Size must be positive.");
            sc.close();
            return;
        }

        System.out.println("Enter inventory:");
        Warehouse w = new Warehouse(n, sc);
        w.displayInventory();
        w.getRobotVisitOrder();
        System.out.println("Boundary stock: " + w.calculateBoundaryStock());
        w.findMaximumSection();
        sc.close();
    }
}