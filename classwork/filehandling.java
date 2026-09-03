import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class filehandling {
    public static void main(String[] args) {
        File f = new File("ab1.txt");
        printFolderContents("C:\\Users\\Tanish Singla\\Desktop\\java\\classwork");
        
        try {
            if (!f.exists()) {
                f.createNewFile();
                System.out.println("createdup");
            } else {
                System.err.println("Existential crisis being served");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
        
        System.out.println(f.isDirectory());
        System.out.println(f.isFile());
        System.out.println("File name: " + f.getName());
        System.out.println("Path: " + f.getPath());
        System.out.println("Absolute path: " + f.getAbsolutePath());
        System.out.println("Parent: " + f.getParent());
        System.out.println("Length: " + f.length() + " bytes");
        System.out.println("Can read: " + f.canRead());
        System.out.println("Can write: " + f.canWrite());
        System.out.println("Can execute: " + f.canExecute());
        System.out.println("Exists: " + f.exists());
        System.out.println("Hidden: " + f.isHidden());
        System.out.println("Last modified: " + f.lastModified());

        writeToFile(f, "Hello123 World456\n");
        writeToFile(f, "File789 Handling10 in Java\n");
        writeToFile(f, "79865456");
        
        System.out.println("\n=== Sum of Digits in File ===");
        int sum = sumDigitsFromFile(f);
        System.out.println("Total sum of digits: " + sum);
        
        System.out.println("\n=== Copy File Contents ===");
        File f2 = new File("ab2.txt");
        copyFile(f, f2);
        System.out.println("File copied successfully from " + f.getName() + " to " + f2.getName());
        
        System.out.println("\n=== Reading Copied File ===");
        readFile(f2);
    }

    static void writeToFile(File file, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(content);
            System.out.println("Successfully wrote to file");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
    
    static int sumDigitsFromFile(File file) {
        int sum = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    if (Character.isDigit(c)) {
                        sum += Character.getNumericValue(c);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        
        return sum;
    }
    
    static void copyFile(File source, File destination) {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             BufferedWriter bw = new BufferedWriter(new FileWriter(destination))) {
            
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
            System.out.println("File copied successfully");
            
        } catch (IOException e) {
            System.out.println("An error occurred while copying the file.");
            e.printStackTrace();
        }
    }
    
    static void readFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
    
    static void printFolderContents(String path) {
        File folder = new File(path);
        File[] files = folder.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("[DIR] " + file.getName());
                } else {
                    System.out.println("[FILE] " + file.getName() + " (" + file.length() + " bytes)");
                }
            }
        } else {
            System.out.println("Unable to read folder contents");
        }
    }
}