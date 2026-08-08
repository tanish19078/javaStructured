import java.util.Scanner;

public class SmartLibrarySystem {
    private final Scanner sc = new Scanner(System.in);
    private final Library lib = new Library();

    public static void main(String[] args) {
        new SmartLibrarySystem().runMenu();
    }

    private void runMenu() {
        boolean run = true;

        while (run) {
            showMenu();
            int op = readInt("Choose an option: ", 1, 11);

            switch (op) {
                case 1:
                    addBook();
                    break;
                case 2:
                    lib.displayAllBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    deleteBook();
                    break;
                case 6:
                    issueBook();
                    break;
                case 7:
                    returnBook();
                    break;
                case 8:
                    lib.displayAvailableBooks();
                    break;
                case 9:
                    lib.displayIssuedBooks();
                    break;
                case 10:
                    lib.displayStatistics();
                    break;
                case 11:
                    run = false;
                    System.out.println("Thank you for using the library system.");
                    break;
                default:
                    break;
            }
        }

        sc.close();
    }

    private void showMenu() {
        System.out.println("\n========== SMART LIBRARY SYSTEM ==========");
        System.out.println("1. Add Book");
        System.out.println("2. Display All Books");
        System.out.println("3. Search Book");
        System.out.println("4. Update Book");
        System.out.println("5. Delete Book");
        System.out.println("6. Issue Book");
        System.out.println("7. Return Book");
        System.out.println("8. Display Available Books");
        System.out.println("9. Display Issued Books");
        System.out.println("10. Display Library Statistics");
        System.out.println("11. Exit");
    }

    private void addBook() {
        System.out.println("\n--- Add Book ---");
        String id = readText("Book ID: ");

        if (lib.searchById(id) != null) {
            System.out.println("A book with this Book ID already exists.");
            return;
        }

        int ty = readInt("Book type (1. Printed Book, 2. E-Book): ", 1, 2);
        Book b = readBook(id, ty);

        if (lib.addBook(b)) {
            System.out.println("Book added successfully.");
        }
    }

    private void searchBook() {
        System.out.println("\n--- Search Book ---");
        System.out.println("1. Search by Book ID");
        System.out.println("2. Search by Title");
        System.out.println("3. Search by Author");
        System.out.println("4. Search by Category");

        int op = readInt("Choose search option: ", 1, 4);

        switch (op) {
            case 1:
                String id = readText("Book ID: ");
                Book b = lib.searchById(id);
                if (b == null) {
                    System.out.println("Book not found.");
                } else {
                    b.displayDetails();
                }
                break;
            case 2:
                lib.searchByTitle(readText("Title: "));
                break;
            case 3:
                lib.searchByAuthor(readText("Author: "));
                break;
            case 4:
                lib.searchByCategory(readText("Category: "));
                break;
            default:
                break;
        }
    }

    private void updateBook() {
        System.out.println("\n--- Update Book ---");
        String id = readText("Book ID: ");
        Book old = lib.searchById(id);

        if (old == null) {
            System.out.println("Book not found.");
            return;
        }

        System.out.println("Enter the new details. Book ID will remain " + id + ".");
        int ty = old instanceof PrintedBook ? 1 : 2;
        Book b = readBook(id, ty);

        if (lib.updateBook(id, b)) {
            System.out.println("Book updated successfully.");
        }
    }

    private void deleteBook() {
        System.out.println("\n--- Delete Book ---");
        String id = readText("Book ID: ");

        if (lib.deleteBook(id)) {
            System.out.println("Book deleted successfully.");
        }
    }

    private void issueBook() {
        System.out.println("\n--- Issue Book ---");
        String id = readText("Book ID: ");

        if (lib.issueBook(id)) {
            System.out.println("Book issued successfully.");
        }
    }

    private void returnBook() {
        System.out.println("\n--- Return Book ---");
        String id = readText("Book ID: ");

        if (lib.returnBook(id)) {
            System.out.println("Book returned successfully.");
        }
    }

    private Book readBook(String id, int ty) {
        String ti = readText("Title: ");
        String au = readText("Author: ");
        String pu = readText("Publisher: ");
        String is = readText("ISBN: ");
        String ca = readText("Category: ");
        double pr = readPrice("Price: ");
        int pg = readPositiveInt("Number of pages: ");
        int yr = readPositiveInt("Publication year: ");

        if (ty == 1) {
            String sh = readText("Shelf number: ");
            String ed = readText("Edition: ");
            return new PrintedBook(id, ti, au, pu, is, ca, pr, pg, yr, sh, ed);
        }

        String fo = readText("File format: ");
        double fs = readFileSize("File size in MB: ");
        String dl = readText("Download link: ");
        return new EBook(id, ti, au, pu, is, ca, pr, pg, yr, fo, fs, dl);
    }

    private String readText(String p) {
        while (true) {
            System.out.print(p);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) {
                return s;
            }
            System.out.println("This value cannot be empty.");
        }
    }

    private int readInt(String p, int lo, int hi) {
        while (true) {
            System.out.print(p);
            if (sc.hasNextInt()) {
                int n = sc.nextInt();
                sc.nextLine();
                if (n >= lo && n <= hi) {
                    return n;
                }
            } else {
                sc.nextLine();
            }
            System.out.println("Enter a number from " + lo + " to " + hi + ".");
        }
    }

    private int readPositiveInt(String p) {
        while (true) {
            System.out.print(p);
            if (sc.hasNextInt()) {
                int n = sc.nextInt();
                sc.nextLine();
                if (Book.isValidPages(n)) {
                    return n;
                }
            } else {
                sc.nextLine();
            }
            System.out.println("Enter a whole number greater than zero.");
        }
    }

    private double readPrice(String p) {
        while (true) {
            System.out.print(p);
            if (sc.hasNextDouble()) {
                double n = sc.nextDouble();
                sc.nextLine();
                if (Book.isValidPrice(n)) {
                    return n;
                }
            } else {
                sc.nextLine();
            }
            System.out.println("Enter a price that is zero or greater.");
        }
    }

    private double readFileSize(String p) {
        return readPrice(p);
    }

    interface LibraryOperations {
        boolean addBook(Book b);

        Book searchById(String id);

        void searchByTitle(String ti);

        void searchByAuthor(String au);

        void searchByCategory(String ca);

        boolean updateBook(String id, Book b);

        boolean deleteBook(String id);

        boolean issueBook(String id);

        boolean returnBook(String id);

        void displayAllBooks();

        void displayAvailableBooks();

        void displayIssuedBooks();

        void displayStatistics();
    }

    static class Library implements LibraryOperations {
        private static final int MAX_BOOKS = 100;
        private final Book[] bk = new Book[MAX_BOOKS];
        private int n;

        public boolean addBook(Book b) {
            if (!hasSpace()) {
                System.out.println("Library capacity is full.");
                return false;
            }
            if (findBookIndexById(b.getBookId()) >= 0) {
                System.out.println("A book with this Book ID already exists.");
                return false;
            }
            if (isIsbnUsed(b.getIsbn(), -1)) {
                System.out.println("A book with this ISBN already exists.");
                return false;
            }

            bk[n] = b;
            n++;
            return true;
        }

        public Book searchById(String id) {
            int i = findBookIndexById(id);
            return i >= 0 ? bk[i] : null;
        }

        public void searchByTitle(String ti) {
            searchText(ti, 1, "title");
        }

        public void searchByAuthor(String au) {
            searchText(au, 2, "author");
        }

        public void searchByCategory(String ca) {
            searchText(ca, 3, "category");
        }

        public boolean updateBook(String id, Book b) {
            int i = findBookIndexById(id);
            if (i < 0) {
                System.out.println("Book not found.");
                return false;
            }
            if (!bk[i].getBookId().equalsIgnoreCase(b.getBookId())) {
                System.out.println("Book ID cannot be changed.");
                return false;
            }
            if (!bk[i].getClass().equals(b.getClass())) {
                System.out.println("Book type cannot be changed during update.");
                return false;
            }
            if (!bk[i].getIsbn().equalsIgnoreCase(b.getIsbn()) && isIsbnUsed(b.getIsbn(), i)) {
                System.out.println("A book with this ISBN already exists.");
                return false;
            }

            b.setAvailable(bk[i].isAvailable());
            bk[i] = b;
            return true;
        }

        public boolean deleteBook(String id) {
            int i = findBookIndexById(id);
            if (i < 0) {
                System.out.println("Book not found.");
                return false;
            }

            shiftBooksLeft(i);
            return true;
        }

        public boolean issueBook(String id) {
            Book b = searchById(id);
            if (b == null) {
                System.out.println("Book not found.");
                return false;
            }
            if (!b.issueBook()) {
                System.out.println("This book is already issued.");
                return false;
            }
            return true;
        }

        public boolean returnBook(String id) {
            Book b = searchById(id);
            if (b == null) {
                System.out.println("Book not found.");
                return false;
            }
            if (!b.returnBook()) {
                System.out.println("This book is already available.");
                return false;
            }
            return true;
        }

        public void displayAllBooks() {
            displayBooks("All Books", 0);
        }

        public void displayAvailableBooks() {
            displayBooks("Available Books", 1);
        }

        public void displayIssuedBooks() {
            displayBooks("Issued Books", 2);
        }

        public void displayStatistics() {
            int av = 0;
            for (int i = 0; i < n; i++) {
                if (bk[i].isAvailable()) {
                    av++;
                }
            }

            System.out.println("\n--- Library Statistics ---");
            System.out.println("Total books: " + n);
            System.out.println("Available books: " + av);
            System.out.println("Issued books: " + (n - av));
            System.out.println("Maximum capacity: " + MAX_BOOKS);
        }

        private boolean hasSpace() {
            return n < MAX_BOOKS;
        }

        private int findBookIndexById(String id) {
            for (int i = 0; i < n; i++) {
                if (bk[i].getBookId().equalsIgnoreCase(id)) {
                    return i;
                }
            }
            return -1;
        }

        private boolean isIsbnUsed(String is, int sk) {
            for (int i = 0; i < n; i++) {
                if (i != sk && bk[i].getIsbn().equalsIgnoreCase(is)) {
                    return true;
                }
            }
            return false;
        }

        private void shiftBooksLeft(int ix) {
            for (int i = ix; i < n - 1; i++) {
                bk[i] = bk[i + 1];
            }
            bk[n - 1] = null;
            n--;
        }

        private void searchText(String q, int ty, String lb) {
            boolean ok = false;
            for (int i = 0; i < n; i++) {
                if (matches(bk[i], q, ty)) {
                    bk[i].displayDetails();
                    ok = true;
                }
            }
            if (!ok) {
                System.out.println("No book found for this " + lb + ".");
            }
        }

        private boolean matches(Book b, String q, int ty) {
            String v;
            if (ty == 1) {
                v = b.getTitle();
            } else if (ty == 2) {
                v = b.getAuthor();
            } else {
                v = b.getCategory();
            }
            return v.toLowerCase().contains(q.toLowerCase());
        }

        private void displayBooks(String hd, int ty) {
            boolean ok = false;
            System.out.println("\n--- " + hd + " ---");

            for (int i = 0; i < n; i++) {
                if (ty == 0 || (ty == 1 && bk[i].isAvailable()) || (ty == 2 && !bk[i].isAvailable())) {
                    bk[i].displayDetails(false);
                    ok = true;
                }
            }

            if (!ok) {
                System.out.println("No books to display.");
            }
        }
    }

    static abstract class Book {
        private String id;
        private String ti;
        private String au;
        private String pu;
        private String is;
        private String ca;
        private double pr;
        private int pg;
        private int yr;
        private boolean av;

        public Book() {
            this("", "", "", "", "", "", 0, 1, 1, true);
        }

        public Book(String id, String ti, String au, String pu, String is, String ca, double pr, int pg, int yr, boolean av) {
            this.id = id;
            this.ti = ti;
            this.au = au;
            this.pu = pu;
            this.is = is;
            this.ca = ca;
            this.pr = pr;
            this.pg = pg;
            this.yr = yr;
            this.av = av;
        }

        public static boolean isValidPrice(double pr) {
            return pr >= 0;
        }

        public static boolean isValidPages(int pg) {
            return pg > 0;
        }

        public String getBookId() {
            return id;
        }

        public String getTitle() {
            return ti;
        }

        public String getAuthor() {
            return au;
        }

        public String getPublisher() {
            return pu;
        }

        public String getIsbn() {
            return is;
        }

        public String getCategory() {
            return ca;
        }

        public double getPrice() {
            return pr;
        }

        public int getPages() {
            return pg;
        }

        public int getYear() {
            return yr;
        }

        public boolean isAvailable() {
            return av;
        }

        public void setTitle(String ti) {
            this.ti = ti;
        }

        public void setAuthor(String au) {
            this.au = au;
        }

        public void setPublisher(String pu) {
            this.pu = pu;
        }

        public void setIsbn(String is) {
            this.is = is;
        }

        public void setCategory(String ca) {
            this.ca = ca;
        }

        public void setPrice(double pr) {
            if (isValidPrice(pr)) {
                this.pr = pr;
            }
        }

        public void setPages(int pg) {
            if (isValidPages(pg)) {
                this.pg = pg;
            }
        }

        public void setYear(int yr) {
            if (yr > 0) {
                this.yr = yr;
            }
        }

        public void setAvailable(boolean av) {
            this.av = av;
        }

        public boolean issueBook() {
            if (!av) {
                return false;
            }
            av = false;
            return true;
        }

        public boolean returnBook() {
            if (av) {
                return false;
            }
            av = true;
            return true;
        }

        public void displayDetails(boolean full) {
            if (full) {
                displayDetails();
            } else {
                System.out.println(id + " | " + ti + " | " + au + " | " + getType() + " | " + getStatus());
            }
        }

        protected void displayCommonDetails() {
            System.out.println("Book ID: " + id);
            System.out.println("Title: " + ti);
            System.out.println("Author: " + au);
            System.out.println("Publisher: " + pu);
            System.out.println("ISBN: " + is);
            System.out.println("Category: " + ca);
            System.out.printf("Price: %.2f%n", pr);
            System.out.println("Pages: " + pg);
            System.out.println("Publication Year: " + yr);
            System.out.println("Availability: " + getStatus());
        }

        protected String getStatus() {
            return av ? "Available" : "Issued";
        }

        public abstract String getType();

        public abstract void displayDetails();
    }

    static class PrintedBook extends Book {
        private String sh;
        private String ed;

        public PrintedBook() {
            super();
            sh = "";
            ed = "";
        }

        public PrintedBook(String id, String ti, String au, String pu, String is, String ca, double pr, int pg, int yr, String sh, String ed) {
            super(id, ti, au, pu, is, ca, pr, pg, yr, true);
            this.sh = sh;
            this.ed = ed;
        }

        public String getShelfNumber() {
            return sh;
        }

        public String getEdition() {
            return ed;
        }

        public void setShelfNumber(String sh) {
            this.sh = sh;
        }

        public void setEdition(String ed) {
            this.ed = ed;
        }

        public String getType() {
            return "Printed Book";
        }

        public void displayDetails() {
            System.out.println("\n--- Printed Book Details ---");
            displayCommonDetails();
            System.out.println("Shelf Number: " + sh);
            System.out.println("Edition: " + ed);
        }
    }

    static class EBook extends Book {
        private String fo;
        private double fs;
        private String dl;

        public EBook() {
            super();
            fo = "";
            fs = 0;
            dl = "";
        }

        public EBook(String id, String ti, String au, String pu, String is, String ca, double pr, int pg, int yr, String fo, double fs, String dl) {
            super(id, ti, au, pu, is, ca, pr, pg, yr, true);
            this.fo = fo;
            this.fs = fs;
            this.dl = dl;
        }

        public String getFileFormat() {
            return fo;
        }

        public double getFileSize() {
            return fs;
        }

        public String getDownloadLink() {
            return dl;
        }

        public void setFileFormat(String fo) {
            this.fo = fo;
        }

        public void setFileSize(double fs) {
            if (isValidPrice(fs)) {
                this.fs = fs;
            }
        }

        public void setDownloadLink(String dl) {
            this.dl = dl;
        }

        public String getType() {
            return "E-Book";
        }

        public void displayDetails() {
            System.out.println("\n--- E-Book Details ---");
            displayCommonDetails();
            System.out.println("File Format: " + fo);
            System.out.printf("File Size: %.2f MB%n", fs);
            System.out.println("Download Link: " + dl);
        }
    }
}
