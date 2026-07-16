
/*Create a Java class named Book with the following requirements:
 1. Instance Variables- bookId- title- author- price 
 2. Static Variables- libraryName (same for all books)- bookCount (stores the total number of books created) 
 3. Parameterized Constructor Initialize all instance variables and increment bookCount whenever a new Book object is created. 
 4. Non-Static Method Create a method displayBook() to display the details of a book.
  5. Static Methods- displayLibraryName() – prints the library name.- displayBookCount() – prints the total number of books created.
   6. Main Method- Set the library name.- Create three Book objects using the constructor.
   - Display details of all books.- Display the library name.- Display the total number of books created. 
   Sample Output Library Name: City Central Library Book ID : 101 Title : Java Programming Author : James Gosling Price : 650.0 
Book ID : 102 Title : Clean Code Author : Robert C. Martin Price : 799.0 
Book ID : 103 Title : Effective Java Author : Joshua Bloch Price : 899.0Total Books Created : 3 
Search book by id and authorname and add details of book in array with user input;
start with a input 1/2 - 1 for adding a book and 2 for searching book by id+author name
*/

import java.util.Scanner;

class Book {
    int bookId;
    String title;
    String author;
    int price;
    static int bookCount = 0;
    static String libraryName;

    Book(int bookId, String title, String author, int price) {
        this.bookId = bookId;
        this.price = price;
        this.title = title;
        this.author = author;
        bookCount++;
    }

    void displayBook() {
        System.out.println("Book ID : " + bookId);
        System.out.println("Title   : " + title);
        System.out.println("Author  : " + author);
        System.out.println("Price   : " + price);
        System.out.println("----------------");
    }

    static void displayLibraryName() {
        System.out.println("Library Name: " + libraryName);
    }

    static void displayBookCount() {
        System.out.println("Total Books Created : " + bookCount);
    }
}

public class libraryms {
    public static void main(String[] args) {
        // Set and display library name
        Book.libraryName = "CityLibrary";
        Book.displayLibraryName();
        System.out.println("----------------");

        Book x[] = new Book[11];
        x[0] = new Book(101, "Java Programming", "James Gosling", 650);
        x[1] = new Book(102, "Clean Code", "Robert C. Martin", 799);
        x[2] = new Book(103, "Effective Java", "Joshua Bloch", 899);

        for (int i = 0; i < 3; i++) {
            x[i].displayBook();
        }

        java.util.Scanner sc = new Scanner(System.in);
        System.out.print("Enter choice (1 to Add, 2 to Search): ");
        int choice = sc.nextInt();
        sc.nextLine(); 


        if (choice == 1) {
            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();
            sc.nextLine(); 

            System.out.print("Enter Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Author: ");
            String author = sc.nextLine();

            System.out.print("Enter Price: ");
            int price = sc.nextInt();
            sc.nextLine(); 

            x[3] = new Book(id, title, author, price);
            System.out.println("\nBook Added Successfully!");
            x[3].displayBook();

        } else if (choice == 2) {
            // Choice 2: Search book by id AND author name
            System.out.print("Enter Book ID to search: ");
            int s = sc.nextInt();
            sc.nextLine(); // Clear buffer

            System.out.print("Enter Author Name to search: ");
            String s1 = sc.nextLine();

            boolean found = false;
            for (int i = 0; i < x.length; i++) {
                // Changed || to && to strictly match both parameters
                if (x[i] != null && (x[i].bookId == s && x[i].author.equalsIgnoreCase(s1))) {
                    x[i].displayBook();
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Not found");
            }
        } else {
            System.out.println("Invalid choice!");
        }

        Book.displayBookCount();
        sc.close();
    }
}
