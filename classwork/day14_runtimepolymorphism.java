import java.util.Scanner;

public class day14_runtimepolymorphism {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Book b = null;
        
        while (true) {
            System.out.println("1: Horror \n2: Fiction \n3: Exit");
            int d = sc.nextInt();
            
            if (d == 1) {
                b = new HorrorBook();
            } else if (d == 2) {
                b = new FictionBook();
            } else if (d == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice");
                continue;
            }
            
            b.read();
            b.getType();
            System.out.println();
        }
        sc.close();
    }
}

class Book {
    String title;
    String author;
    double price;
    
    public void read() {
        System.out.println("Reading a book");
    }
    
    public void getType() {
        System.out.println("Generic Book");
    }
    
    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: " + price);
    }
}

class HorrorBook extends Book {
    String scareLevel;
    
    public HorrorBook() {
        this.title = "Horror Story";
        this.author = "Stephen King";
        this.price = 299.99;
        this.scareLevel = "High";
    }
    
    @Override
    public void read() {
        System.out.println("Reading horror book in dark...");
        display();
        System.out.println("Scare Level: " + scareLevel);
    }
    
    @Override
    public void getType() {
        System.out.println("Type: Horror Book");
    }
}

class FictionBook extends Book {
    String genre;
    
    public FictionBook() {
        this.title = "Fiction World";
        this.author = "J.K. Rowling";
        this.price = 399.99;
        this.genre = "Fantasy";
    }
    
    @Override
    public void read() {
        System.out.println("Reading fiction book with imagination...");
        display();
        System.out.println("Genre: " + genre);
    }
    
    @Override
    public void getType() {
        System.out.println("Type: Fiction Book");
    }
}