/*
Design and implement a Functional Interface for a real-world problem.
 The interface must contain exactly one abstract method and must be implemented using a separate implementation class.
  Do NOT use Lambda Expressions or anonymous classes.
  
  Instructions
   1. Create a Functional Interface with exactly one abstract method.
    2. Implement it using a separate implementation class.
     3. Do not use Lambda Expressions.
     4. Build a console-based application.
      5. Follow proper naming conventions and OOP principles.
       6. Demonstrate your program using suitable test cases. 
       
Use Case 1: OTP Verification System Develop a console application that verifies whether the OTP entered by the user matches the generated OTP. 
Create a Functional Interface named OTPValidator and design the abstract method yourself. 

Use Case 2: Payment Verification System Develop a payment verification application. 
Create a Functional Interface named PaymentValidator and design an appropriate abstract method to validate a payment before processing. 

Use Case 3: Age Verification System Develop an application that checks whether a user is eligible for registration based on age.
 Create a Functional Interface named AgeValidator and design the abstract method yourself.
  Expected Project Structure Functional Interface → Implementation Class → Main Class 
  Example: OTPValidator.java OTPValidatorImpl.java OTPVerificationSystem.java
  
Constraints 
• Exactly one abstract method.
 • Students must design the abstract method themselves. 
 • Do not use Lambda Expressions.
  • Do not use anonymous inner classes. 
  • Implement using a separate class. 
  • Console-based application only.
*/
import java.util.*;

interface OTPValidator {
    boolean validateOTP(int generatedOTP, int enteredOTP);
}

class OTPValidatorImpl implements OTPValidator {
    @Override
    public boolean validateOTP(int generatedOTP, int enteredOTP) {
        return generatedOTP == enteredOTP;
    }
}

interface PaymentValidator {
    boolean validatePayment(double amount, String paymentMethod);
}

class PaymentValidatorImpl implements PaymentValidator {
    private static final Set<String> VALID_METHODS = Set.of("CARD", "UPI", "NETBANKING");

    @Override
    public boolean validatePayment(double amount, String paymentMethod) {
        if (amount <= 0) {
            return false;
        }
        if (paymentMethod == null || paymentMethod.isEmpty()) {
            return false;
        }
        return VALID_METHODS.contains(paymentMethod.trim().toUpperCase());
    }
}

interface AgeValidator {
    boolean validateAge(int age);
}

class AgeValidatorImpl implements AgeValidator {
    @Override
    public boolean validateAge(int age) {
        return age >= 18;
    }
}

public class Functionalinterface {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        runOTPVerification(sc);
        System.out.println();
        runPaymentVerification(sc);
        System.out.println();
        runAgeVerification(sc);

        sc.close();
    }

    private static void runOTPVerification(Scanner sc) {
        System.out.println("---- OTP Verification System ----");
        OTPValidator otpValidator = new OTPValidatorImpl();

        int generatedOTP = new Random().nextInt(900000) + 100000;
        System.out.println("[System] Generated OTP: " + generatedOTP);
        System.out.print("Enter the 6-digit OTP: ");

        int enteredOTP;
        try {
            enteredOTP = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. OTP must be numeric.");
            return;
        }

        boolean isOTPValid = otpValidator.validateOTP(generatedOTP, enteredOTP);
        System.out.println("OTP valid: " + isOTPValid);
    }

    private static void runPaymentVerification(Scanner sc) {
        System.out.println("---- Payment Verification System ----");
        PaymentValidator paymentValidator = new PaymentValidatorImpl();

        System.out.print("Enter payment amount: ");
        double amount;
        try {
            amount = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
            return;
        }

        System.out.print("Enter payment method (CARD / UPI / NETBANKING): ");
        String paymentMethod = sc.nextLine().trim();

        boolean isPaymentValid = paymentValidator.validatePayment(amount, paymentMethod);
        System.out.println("Payment valid: " + isPaymentValid);
    }

    private static void runAgeVerification(Scanner sc) {
        System.out.println("---- Age Verification System ----");
        AgeValidator ageValidator = new AgeValidatorImpl();

        System.out.print("Enter your age: ");
        int age;
        try {
            age = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age entered.");
            return;
        }

        boolean isAgeValid = ageValidator.validateAge(age);
        System.out.println("Eligible for registration (age >= 18): " + isAgeValid);
    }
}