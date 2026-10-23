import java.util.HashMap;

public class Hashmapprac {
    
    int[] arr={1,2,3,4,5,6,7,8,9,10};

    HashMap<Integer,Integer> map=new HashMap<>();
    // freq count of each element in the array
    for(int i=0;i<arr.length;i++){
        map.put(arr[i],map.getOrDefault(arr[i],0)+1);
    }

    map.forEach((key,value)->{
        System.out.println(key+" "+value);
    });


    // First Non-Repeating Character: Given a string, use a HashMap to find the first character that occurs

    // only once in the string. If all characters repeat, return a message indicating that there are no non-repeating characters.

    HashMap<Character,Integer> charMap=new HashMap<>();

    for(var e:"hello world".toCharArray()){
        charMap.put(e,charMap.getOrDefault(e,0)+1);
    }


    // check for duplicate Integers

    ArrayList<Integer> list=new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,1,2,3));
    HashMap<Integer,Integer> duplicateMap=new HashMap<>();
    for(int i=0;i<list.size();i++){
        duplicateMap.put(list.get(i),duplicateMap.getOrDefault(list.get(i),0)+1);
    }
    for(var e:duplicateMap.entrySet()){
        if(e.getValue()>1){
            System.out.println(e.getKey()+" is duplicate");
        }
    }
    


}
