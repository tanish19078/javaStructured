import java.util.*;
public class ce3x{
    static void arrlist(int n){
ArrayList<Integer> x=new ArrayList<>();
int l=0;
int r=x.size()-1;
while(l<r){
int temp=x.get(l);
//set the value at index l to the value at index r
x.set(l,x.get(r));
x.set(r,temp);
l++;
r--;
}
    }

    
}