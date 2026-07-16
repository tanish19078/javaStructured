
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

class Book{
int bookId;
String title;
String author;
int price;
    static int bookCount=0;
    static String libraryName;

Book(int bookId, String title,String author,int price){
    this.bookId=bookId;
    this.price=price;
    this.title=title;
    this.author=author;
bookCount++;
}
void displayBook(){
        System.out.println("Book ID : " + bookId);
        System.out.println("Title   : " + title);
        System.out.println("Author  : " + author);
        System.out.println("Price   : " + price);
        System.out.println("----------------");
}
static void displayLibraryName(){
System.out.println(libraryName);
}
 static void displayBookCount(){
System.out.println(bookCount);
 }

 

}

public class bookms {
    public static void main(String[] args){
        Book.libraryName="AB";
        Book.displayLibraryName();
        java.util.Scanner sc=new Scanner(System.in);
        int s=sc.nextInt();
        sc.nextLine();
        String s1=sc.nextLine();

        Book book1 = new Book(101, "Java Programming", "James Gosling", 650);
        Book book2 = new Book(102, "Clean Code", "Robert C. Martin", 799);
        Book book3 = new Book(103, "Effective Java", "Joshua Bloch", 899);
        Book x[]=new Book[11];
    x[2]=new Book(103, "Effective Java", "Joshua Bloch", 899);
    x[1]=new Book(102, "Clean Code", "Robert C. Martin", 799);
    x[0]=new Book(101, "Java Programming", "James Gosling", 650);
boolean found=false;
for(int i=0;i<x.length;i++){
    if(x[i]!=null &&( x[i].bookId==s || x[i].author.equals(s1))){
        x[i].displayBook();
        found=true;
    }
}
if(!found){System.out.println("Not found");}

        // book1.displayBook();
        // book2.displayBook();
        // book3.displayBook();

        Book.displayBookCount();
        sc.close();
    }
}
