/*
Singleton classes
mainly used when a single shared object is required
ensures that a class has only one instance throughout the application and provides a global access point to it.

 Private Constructor: Prevents other classes from instantiating the class using the new keyword.
Private Static Field: Holds the single, unique instance of the class.
Public Static Factory Method: Serves as the global entry point (usually named getInstance()) to return the single instance

*/

/*
library class will have book type arr of size 10
book has subclass fiction horror biographys etc
user input name of book
*/

import java.util.*;

class Library{

static Library lib=null;
Book[] books;
int bookcnt;
    private Library(){
        books = new Book[10];
        bookcnt = 0;
System.out.println("library constructor");
    }

  static  Library getInstance(){
    if(lib==null){
        lib=new Library();}
        return lib;
    }

    
    void addBook(Book b) {
        if (bookcnt < 10) {
            books[bookcnt] = b;
            bookcnt++;
            System.out.println("Book added successfully");
        } else {
            System.out.println("Library is full");
        }
    }

    void searchBook(String name) {
        boolean found = false;
        for (int i = 0; i < bookcnt; i++) {
            if (books[i].title.equalsIgnoreCase(name)) {
                books[i].display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found");
        }
    }

    void displayAllBooks() {
        if (bookcnt == 0) {
            System.out.println("No books in library");
            return;
        }
        for (int i = 0; i < bookcnt; i++) {
            books[i].display();
            System.out.println();
        }
    }
}


class Book {
    String title;
    String author;
    double price;

    Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: " + price);
        System.out.println("Type: " + this.getClass().getSimpleName());
    }
}

class Horror extends Book {
    String sclvl;

    Horror(String title, String author, double price, String sclvl) {
        super(title, author, price);
        this.sclvl = sclvl;
    }

    @Override
    void display() {
        super.display();
        System.out.println("Scare Level: " + sclvl);
    }
}

class Biography extends Book {
    String personName;

    Biography(String title, String author, double price, String personName) {
        super(title, author, price);
        this.personName = personName;
    }

    @Override
    void display() {
        super.display();
        System.out.println("About: " + personName);
    }
}

class Fiction extends Book {
    String genre;

    Fiction(String title, String author, double price, String genre) {
        super(title, author, price);
        this.genre = genre;
    }

    @Override
    void display() {
        super.display();
        System.out.println("Genre: " + genre);
    }
}

public class day16_singleton{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Library library = Library.getInstance();

        library.addBook(new Horror("Dracula", "Bram Stoker", 299.99, "Extreme"));
        library.addBook(new Biography("Steve Jobs", "Walter Isaacson", 499.99, "Steve Jobs"));
        library.addBook(new Fiction("Harry Potter", "J.K. Rowling", 399.99, "Fantasy"));
        library.addBook(new Horror("It", "Stephen King", 349.99, "Very High"));
        library.addBook(new Biography("Wings of Fire", "A.P.J. Abdul Kalam", 299.99, "A.P.J. Abdul Kalam"));

        while (true) {
            System.out.println("\n1: Display All Books");
            System.out.println("2: Search Book by Name");
            System.out.println("3: Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    library.displayAllBooks();
                    break;
                case 2:
                    System.out.print("Enter book name: ");
                    String name = sc.nextLine();
                    library.searchBook(name);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}