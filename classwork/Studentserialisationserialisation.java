import java.io.*;

// Studentserialisation class implementing Serializable
public class Studentserialisationserialisation implements Serializable {
    private int rollNo;
    private String name;
    private transient String password; // transient fields won't be serialized
    private static String schoolName = "ABC School"; // static fields won't be serialized
    
    public Studentserialisationserialisation(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }
    
    public Studentserialisationserialisation(int rollNo, String name, String password) {
        this.rollNo = rollNo;
        this.name = name;
        this.password = password;
    }
    
    public int getRollNo() {
        return rollNo;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "Studentserialisation{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}

// Serialization Demo Class
class SerializationDemo {
    
    public static void main(String[] args) {
        serializeStudentserialisation();
        deserializeStudentserialisation();
        serializeMultipleStudentserialisations();
        deserializeMultipleStudentserialisations();
        customSerialization();
    }
    
    // Method to serialize a single student
    public static void serializeStudentserialisation() {
        System.out.println("=== SINGLE STUDENT SERIALIZATION ===\n");
        
        Studentserialisation student = new Studentserialisation(101, "Rahul", "secret123");
        
        try (FileOutputStream fileOut = new FileOutputStream("student.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            
            out.writeObject(student);
            System.out.println("Studentserialisation object serialized successfully!");
            System.out.println("Original object: " + student);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Method to deserialize a single student
    public static void deserializeStudentserialisation() {
        System.out.println("\n=== SINGLE STUDENT DESERIALIZATION ===\n");
        
        try (FileInputStream fileIn = new FileInputStream("student.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            
            Studentserialisation student = (Studentserialisation) in.readObject();
            System.out.println("Studentserialisation object deserialized successfully!");
            System.out.println("Deserialized object: " + student);
            System.out.println("Note: password is null (transient) and schoolName is null (static)");
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // Method to serialize multiple students
    public static void serializeMultipleStudentserialisations() {
        System.out.println("\n=== MULTIPLE STUDENTS SERIALIZATION ===\n");
        
        Studentserialisation[] students = {
            new Studentserialisation(101, "Rahul", "rahul123"),
            new Studentserialisation(102, "Aman", "aman123"),
            new Studentserialisation(103, "Priya", "priya123"),
            new Studentserialisation(104, "Neha", "neha123")
        };
        
        try (FileOutputStream fileOut = new FileOutputStream("students.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            
            out.writeObject(students);
            System.out.println("Studentserialisation array serialized successfully!");
            System.out.println("Original students:");
            for (Studentserialisation s : students) {
                System.out.println(s);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Method to deserialize multiple students
    public static void deserializeMultipleStudentserialisations() {
        System.out.println("\n=== MULTIPLE STUDENTS DESERIALIZATION ===\n");
        
        try (FileInputStream fileIn = new FileInputStream("students.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            
            Studentserialisation[] students = (Studentserialisation[]) in.readObject();
            System.out.println("Studentserialisations deserialized successfully!");
            System.out.println("Deserialized students:");
            for (Studentserialisation s : students) {
                System.out.println(s);
            }
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // Method demonstrating custom serialization
    public static void customSerialization() {
        System.out.println("\n=== CUSTOM SERIALIZATION ===\n");
        
        // Using ArrayList for dynamic list of students
        java.util.List<Studentserialisation> studentList = new java.util.ArrayList<>();
        studentList.add(new Studentserialisation(201, "Karan", "karan123"));
        studentList.add(new Studentserialisation(202, "Riya", "riya123"));
        studentList.add(new Studentserialisation(203, "Amit", "amit123"));
        
        try (FileOutputStream fileOut = new FileOutputStream("studentList.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            
            out.writeObject(studentList);
            System.out.println("Studentserialisation list serialized successfully!");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (FileInputStream fileIn = new FileInputStream("studentList.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            
            @SuppressWarnings("unchecked")
            java.util.List<Studentserialisation> deserializedList = (java.util.List<Studentserialisation>) in.readObject();
            System.out.println("Studentserialisation list deserialized successfully!");
            System.out.println("Deserialized list:");
            deserializedList.forEach(System.out::println);
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}