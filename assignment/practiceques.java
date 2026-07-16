import java.util.Scanner;

public class practiceques {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========== Assignment 1: Even or Odd ==========");
        System.out.print("Enter a number N: ");
        int n = scanner.nextInt();
        printEvenOdd(n);

        System.out.println("\n========== Assignment 2: Multiplication Tables ==========");
        System.out.print("Enter a number N: ");
        int tableN = scanner.nextInt();
        printMultiplicationTables(tableN);

        System.out.println("\n========== Assignment 3: Prime Numbers ==========");
        System.out.print("Enter start: ");
        int start = scanner.nextInt();
        System.out.print("Enter end: ");
        int end = scanner.nextInt();
        printPrimesInRange(start, end);

        System.out.println("\n========== Assignment 4: Number Guessing Game ==========");
        System.out.println("You have 5 attempts to guess the secret number (27)");
        guessNumber(scanner);

        System.out.println("\n========== Assignment 5: Strong Numbers ==========");
        System.out.print("Enter start: ");
        int strongStart = scanner.nextInt();
        System.out.print("Enter end: ");
        int strongEnd = scanner.nextInt();
        printStrongNumbers(strongStart, strongEnd);

        scanner.close();
    }

    public static void printEvenOdd(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                System.out.println(i + " -> Even");
            } else {
                System.out.println(i + " -> Odd");
            }
        }
    }

    public static void printMultiplicationTables(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println("\nTable of " + i);
            for (int j = 1; j <= 10; j++) {
                System.out.println(i + " x " + j + " = " + (i * j));
            }
        }
    }

    public static void printPrimesInRange(int start, int end) {
        for (int num = start; num <= end; num++) {
            if (isPrime(num)) {
                System.out.println(num);
            }
        }
    }

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void guessNumber(Scanner scanner) {
        int secretNumber = 27;
        int maxAttempts = 5;
        boolean guessed = false;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            System.out.print("Guess " + attempt + ": ");
            int guess = scanner.nextInt();

            if (guess == secretNumber) {
                System.out.println("Congratulations! You guessed it.");
                guessed = true;
                break;
            } else if (guess < secretNumber) {
                System.out.println("Too Low");
            } else {
                System.out.println("Too High");
            }
        }

        if (!guessed) {
            System.out.println("Better Luck Next Time!");
        }
    }

    public static void printStrongNumbers(int start, int end) {
        for (int num = start; num <= end; num++) {
            if (isStrongNumber(num)) {
                System.out.println(num);
            }
        }
    }

    public static boolean isStrongNumber(int num) {
        int original = num;
        int sum = 0;
        
        while (num > 0) {
            int digit = num % 10;
            sum += factorial(digit);
            num /= 10;
        }
        
        return sum == original;
    }

    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}