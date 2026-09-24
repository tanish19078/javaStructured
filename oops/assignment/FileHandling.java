package assignment;
import java.io.*;

public class FileHandling {
    public static void main(String[] args) throws IOException {
        readFileCharByChar("input.txt");
        countCharacters("input.txt");
        copyFile("source.txt", "destination.txt");
        convertToUppercase("input.txt", "output.txt");
        readFileLineByLine("input.txt");
        countLines("input.txt");
        writeStudentInfo("student.txt", "Rahul", 20, "B.Tech");
        copyFileLineByLine("source.txt", "destination.txt");
        countWords("input.txt");
        searchWord("input.txt", "Java");
    }

    static void readFileCharByChar(String file) throws IOException {
        FileReader fr = new FileReader(file);
        int ch;
        while ((ch = fr.read()) != -1) System.out.print((char) ch);
        fr.close();
        System.out.println();
    }

    static void countCharacters(String file) throws IOException {
        FileReader fr = new FileReader(file);
        int count = 0, ch;
        while ((ch = fr.read()) != -1) count++;
        fr.close();
        System.out.println("Total Characters = " + count);
    }

    static void copyFile(String src, String dest) throws IOException {
        FileReader fr = new FileReader(src);
        FileWriter fw = new FileWriter(dest);
        int ch;
        while ((ch = fr.read()) != -1) fw.write(ch);
        fr.close();
        fw.close();
    }

    static void convertToUppercase(String src, String dest) throws IOException {
        FileReader fr = new FileReader(src);
        FileWriter fw = new FileWriter(dest);
        int ch;
        while ((ch = fr.read()) != -1) fw.write(Character.toUpperCase(ch));
        fr.close();
        fw.close();
    }

    static void readFileLineByLine(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        int num = 1;
        while ((line = br.readLine()) != null) System.out.println(num++ + ": " + line);
        br.close();
    }

    static void countLines(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        int count = 0;
        while (br.readLine() != null) count++;
        br.close();
        System.out.println("Total Lines = " + count);
    }

    static void writeStudentInfo(String file, String name, int age, String course) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("Name: " + name + " Age: " + age + " Course: " + course);
        bw.close();
    }

    static void copyFileLineByLine(String src, String dest) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(src));
        BufferedWriter bw = new BufferedWriter(new FileWriter(dest));
        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
        }
        br.close();
        bw.close();
    }

    static void countWords(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            String[] words = line.split(" ");
            count += words.length;
        }
        br.close();
        System.out.println("Total Words = " + count);
    }

    static void searchWord(String file, String word) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        int num = 1;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            if (line.contains(word)) {
                System.out.println(word + " found at line " + num);
                found = true;
            }
            num++;
        }
        if (!found) System.out.println("Word not found");
        br.close();
    }
}