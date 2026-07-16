import java.util.Scanner;

public class practiceques{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int num = scanner.nextInt();
        System.out.println(num + " is " + (isEvenOrOdd(num) ? "even" : "odd"));

        System.out.print("\nEnter a number to display its multiplication table: ");
        int a = scanner.nextInt();
        multiply(a);

        System.out.print("\nEnter the start of the range: ");
        int start = scanner.nextInt();

        System.out.print("Enter the end of the range: ");
        int end = scanner.nextInt();

        primeRange(start, end);

        scanner.close();
    }

    public static boolean isEvenOrOdd(int num) {
        return num % 2 == 0;
    }

    public static void multiply(int a) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(a + " x " + i + " = " + (a * i));
        }
    }

    public static void primeRange(int start, int end) {
        for (int num = start; num <= end; num++) {
            if (num > 1) {
                boolean isPrime = true;

                for (int i = 2; i <= Math.sqrt(num); i++) {
                    if (num % i == 0) {
                        isPrime = false;
                        break;
                    }
                }

                if (isPrime) {
                    System.out.println(num);
                }
            }
        }
    }
}