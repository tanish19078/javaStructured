public class day2_ops {
    public static void main(String[] args){
        //operators
        // arithmetic operators are binary
        // +, -, *, /, %, ++, --
        
        // logical are binary
        //unary inc/dec operators
        int a=1;
        // int b=a++ + a++;
        int c=++a + ++a;
        System.out.println(c);
        // System.out.println(b);
        // associativity check
        /*
        int x=10;
        int y=20;
        int z=30;
        int result=x-y-z;int result1=x-(y-z); int result2=(x-y)-z; int result3=x-(y-z); int result4=(x-y)-z;
        
        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);    
        
        */
       int x=1;
       if(x==1){    
        System.out.println("MOnday");}
         else if(x==2){
            System.out.println("Tuesday");
            }else if(x==3){
                System.out.println("Wednesday");   } 
            else if(x==4){
                System.out.println("Thursday");
            }
            else if(x==5){
                System.out.println("Friday");
            }
            else if(x==6){
                System.out.println("Saturday");
            }
            else{
                System.out.println("Sunday");
            }


        }}
