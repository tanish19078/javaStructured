/*
Problem Statement Develop a Library Management System in Java 
that demonstrates constructors, static members, inheritance, method overloading, and method overriding. 
Part 1 – Create the Book Class Create a class named Book with the following attributes: Book ID Title Author Price Quantity 
Implement the following constructors: Default Constructor Parameterized Constructor Copy Constructor 
Implement methods: displayDetails() updatePrice(double price) purchaseBook(int quantity) restockBook(int quantity) 
Create static variables libraryName and totalBooksCreated. Create static methods displayLibraryInfo() and displayTotalBooks(). 

Part 2 – Method Overloading Overload the following methods: updatePrice(double price) updatePrice(double price, double discountPercentage) 
purchaseBook(int quantity) purchaseBook(int quantity, boolean applyDiscount) Demonstrate each overloaded method in your program.

Part 3 – Inheritance Create the following hierarchy: Book PrintedBook EBook PrintedBook should include Number of Pages and Publisher. 
EBook should include File Size and File Format. 
Override displayDetails() in both derived classes.

Part 4 – Method Overriding Demonstrate runtime polymorphism using: Book b; b = new PrintedBook(...); b.displayDetails(); b = new EBook(...); b.displayDetails();

Part 5 – Menu-Driven Program Store all book objects in an array and 
implement the following menu: Add Printed Book Add EBook Display All Books Search Book by ID Search Book by Title Purchase a Book Restock a Book
 Update Book Price Display Total Books Created Display Library Information ExitGuidelines 
 Use constructors appropriately. Use static variables and methods wherever applicable. 
 Demonstrate inheritance, method overloading, and method overriding. Store objects in arrays only. 
 Do not use the Java Collection Framework. Follow proper object-oriented programming practices. 
 
 Bonus Challenge (Optional) Implement any of the following: Search books by author. 
 Display the most expensive and least expensive book. Display books within a price range. Calculate total inventory value.
  Sort books by title or price. Remove a book by ID. Display out-of-stock books.
*/

import java.util.Scanner;

class Book {
    int id, qty;
    String title, author;
    double price;

    static String libraryName = "City Library";
    static int totalBooks = 0;

    Book() {
        id = 0; title = "Unknown"; author = "Unknown"; price = 0; qty = 0;
        totalBooks++;
    }

    Book(int id, String title, String author, double price, int qty) {
        this.id = id; this.title = title; this.author = author;
        this.price = price; this.qty = qty;
        totalBooks++;
    }

    Book(Book b) {
        id = b.id; title = b.title; author = b.author; price = b.price; qty = b.qty;
        totalBooks++;
    }

    void displayDetails() {
        System.out.println("------------------------------");
        System.out.println("ID: " + id + " | " + title + " by " + author);
        System.out.println("Price: Rs. " + price + " | Qty: " + qty);
    }

    void updatePrice(double p) {
        if (p < 0) { System.out.println("Invalid price"); return; }
        price = p;
        System.out.println(title + " price = Rs. " + price);
    }

    void updatePrice(double p, double disc) {
        if (p < 0 || disc < 0 || disc > 100) { System.out.println("Invalid input"); return; }
        price = p - p * disc / 100;
        System.out.println(title + " price = Rs. " + price + " after " + disc + "% off");
    }

    void purchaseBook(int q) {
        if (q <= 0 || q > qty) { System.out.println("Invalid or not enough stock"); return; }
        qty -= q;
        System.out.println("Bought " + q + " of " + title + ", bill = Rs. " + q * price + ", left = " + qty);
    }

    void purchaseBook(int q, boolean disc) {
        if (q <= 0 || q > qty) { System.out.println("Invalid or not enough stock"); return; }
        qty -= q;
        double bill = q * price;
        if (disc) bill *= 0.9;
        System.out.println("Bought " + q + " of " + title + ", bill = Rs. " + bill + ", left = " + qty);
    }

    void restockBook(int q) {
        if (q <= 0) { System.out.println("Invalid quantity"); return; }
        qty += q;
        System.out.println(title + " restocked, stock = " + qty);
    }

    static void displayLibraryInfo() {
        System.out.println("Library: " + libraryName + " | Books created: " + totalBooks);
    }

    static void displayTotalBooks() {
        System.out.println("Total books created = " + totalBooks);
    }
}

class PrintedBook extends Book {
    int pages;
    String publisher;

    PrintedBook(int id, String t, String a, double p, int q, int pages, String pub) {
        super(id, t, a, p, q);
        this.pages = pages;
        this.publisher = pub;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Printed | Pages: " + pages + " | Publisher: " + publisher);
    }
}

class EBook extends Book {
    double size;
    String format;

    EBook(int id, String t, String a, double p, int q, double size, String format) {
        super(id, t, a, p, q);
        this.size = size;
        this.format = format;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("EBook | " + size + " MB | " + format);
    }
}

public class LibraryMSOOPS {
    static Book[] books = new Book[100];
    static int n = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        demo();

        while (true) {
            System.out.println("\n1.Add Printed 2.Add EBook 3.Show All 4.Search ID 5.Search Title");
            System.out.println("6.Purchase 7.Restock 8.Update Price 9.Total Books 10.Library Info 0.Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            if (ch == 0) break;
            else if (ch == 1) add(true);
            else if (ch == 2) add(false);
            else if (ch == 3) showAll();
            else if (ch == 4) searchId();
            else if (ch == 5) searchTitle();
            else if (ch == 6) buy();
            else if (ch == 7) restock();
            else if (ch == 8) price();
            else if (ch == 9) Book.displayTotalBooks();
            else if (ch == 10) Book.displayLibraryInfo();
            else System.out.println("Wrong choice");
        }
        System.out.println("Bye!");
    }

    static void demo() {
        Book b1 = new Book();
        Book b2 = new Book(1, "Java", "Gosling", 500, 10);
        Book b3 = new Book(b2);
        b3.displayDetails();

        b2.updatePrice(600);
        b2.updatePrice(600, 20);
        b2.purchaseBook(2);
        b2.purchaseBook(2, true);

        Book b;
        b = new PrintedBook(2, "OOP", "Booch", 750, 5, 320, "TechPress");
        b.displayDetails();
        b = new EBook(3, "DSA", "Schildt", 300, 50, 15.5, "PDF");
        b.displayDetails();
    }

    static void add(boolean printed) {
        if (n == 100) { System.out.println("Full"); return; }
        System.out.print("ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Title: ");
        String t = sc.nextLine();
        System.out.print("Author: ");
        String a = sc.nextLine();
        System.out.print("Price: ");
        double p = sc.nextDouble();
        System.out.print("Qty: ");
        int q = sc.nextInt(); sc.nextLine();

        if (printed) {
            System.out.print("Pages: ");
            int pg = sc.nextInt(); sc.nextLine();
            System.out.print("Publisher: ");
            books[n++] = new PrintedBook(id, t, a, p, q, pg, sc.nextLine());
        } else {
            System.out.print("Size(MB): ");
            double s = sc.nextDouble(); sc.nextLine();
            System.out.print("Format: ");
            books[n++] = new EBook(id, t, a, p, q, s, sc.nextLine());
        }
        System.out.println("Added");
    }

    static void showAll() {
        if (n == 0) System.out.println("No books");
        for (int i = 0; i < n; i++) books[i].displayDetails();
    }

    static Book find(int id) {
        for (int i = 0; i < n; i++)
            if (books[i].id == id) return books[i];
        return null;
    }

    static void searchId() {
        System.out.print("ID: ");
        Book b = find(sc.nextInt());
        if (b == null) System.out.println("Not found");
        else b.displayDetails();
    }

    static void searchTitle() {
        System.out.print("Title: ");
        String t = sc.nextLine();
        boolean f = false;
        for (int i = 0; i < n; i++)
            if (books[i].title.equalsIgnoreCase(t)) { books[i].displayDetails(); f = true; }
        if (!f) System.out.println("Not found");
    }

    static void buy() {
        System.out.print("ID: ");
        Book b = find(sc.nextInt());
        if (b == null) { System.out.println("Not found"); return; }
        System.out.print("Qty: ");
        int q = sc.nextInt();
        System.out.print("Discount? (1/0): ");
        if (sc.nextInt() == 1) b.purchaseBook(q, true);
        else b.purchaseBook(q);
    }

    static void restock() {
        System.out.print("ID: ");
        Book b = find(sc.nextInt());
        if (b == null) { System.out.println("Not found"); return; }
        System.out.print("Qty: ");
        b.restockBook(sc.nextInt());
    }

    static void price() {
        System.out.print("ID: ");
        Book b = find(sc.nextInt());
        if (b == null) { System.out.println("Not found"); return; }
        System.out.print("New price: ");
        double p = sc.nextDouble();
        System.out.print("Discount? (1/0): ");
        if (sc.nextInt() == 1) {
            System.out.print("Percent: ");
            b.updatePrice(p, sc.nextDouble());
        } else b.updatePrice(p);
    }
}