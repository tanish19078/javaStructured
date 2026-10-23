import java.io.File;
import java.io.IOException;

public class file_exception{

// triggering the file handling and exceptions possible together in single func aka main def

    public static void main(String[] args) {
        File f=new File("dem.txt");
        try{
if(f.createNewFile()){
    System.out.println("created"+f.getName());
}
else{
    System.err.println("unavailable");
}

if(f.exists()){

    System.err.println("xyasz");
}



        }

        catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
}