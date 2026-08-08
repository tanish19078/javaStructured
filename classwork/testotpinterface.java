public class testotpinterface {
    public static void main(String[] args) {
        otpinterface otpGenerator = () -> {
            return (int)(Math.random() * 9000) + 1000;
        };

        int otp = otpGenerator.generateOtp();
        System.out.println("Generated OTP: " + otp);
    }
}
