interface Interfacedemo {
    String collegeName = "Chitkara";
    
    default void getCollegeName() {
        System.out.println("The college name is " + collegeName + ".");
        System.out.println("Your OTP is: " + getOtp());
    }

    private int getOtp() {
        return 6767;
    }

    static void clgName() {
        System.out.println("College from static method: " + collegeName);
    }
}


class BTechStudent implements Interfacedemo {

}

class CSEtudent implements Interfacedemo {

}

class InterfaceDemo {
    public static void main(String[] args) {
        Interfacedemo.clgName();
        
        CSEtudent student1 = new CSEtudent();
        student1.getCollegeName();
    }
}