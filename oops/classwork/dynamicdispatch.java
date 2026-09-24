public class dynamicdispatch {
    class A{
        void show(){
            System.out.println("A");
        }
    }
    class B extends A{
        void show(){
            System.out.println("B");
        }
    }
    class C extends  B{
        void show(){
            System.out.println("C");
        }
    }

    public static int sum(int ...p){
        int total = 0;
        for(int i=0;i<p.length;i++){
            total+=p[i];
        }
        return total;
    }
    public static void main(String[] args) {
        System.out.println(sum(10,20,30,40,50));
    }


    interface A1{
        void show();
    }
}
    
