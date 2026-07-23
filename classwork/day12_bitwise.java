public class day12_bitwise {

            //6. print full binary number
        public static int binary(int n){
            if(n==0){return 0;}
            return n%2+10*binary(n/2);

            //binary ((n>>1)); -- equal to n/2
            // print(n&1)  -- equal to n%2

        }
    public static void main(String[] args) {
        // 1. Bitwise AND (5 & 11)
        // 0101 & 1011 = 0001
        System.out.println(5 & 11); // Prints: 1

        // 2. Bitwise OR (8 | 12)
        // 1000 | 1100 = 1100
        System.err.println(8 | 12); // Prints: 12

        // 3. Right Shift (1 >> 1)
        // 0001 shifted right by 1 becomes 0000
        System.out.println(1 >> 1); // Prints: 0

        // 4. Check Odd and Even
        int num = 13;
        int x = 1;
        if ((num & x) == 1) {
            System.out.println("ODD"); // Prints: ODD (since 13 & 1 is 1)
        } else {
            System.out.println("Even");
        }

        // 5. Check nth bit (Moved inside the main method) right shift and and with 1l
        int n = 3;
        System.out.println(((49 >> n) & 1)); // Prints: 0

        int num1=48;
        String x1="";
        while(num1!=0){
            int a=num1&1;
            if(a==1){x1=a+x1;}
            else{x1=0+x1;}
            num1=(num1>>1);
        }
        System.out.println(x1);
    
    int ax=-111;
    System.out.println(ax>>>1);
    }}

