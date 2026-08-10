// book is abstract as It requires every child class to provide its own book type and full-detail display

// Method overriding
// Both PrintedBook and EBook provide their own versions of getType() and displayDetails().
// For example, PrintedBook.displayDetails() shows shelf number and edition, 
// while EBook.displayDetails() shows file format, size, and download link.

public abstract class Book {
    // All book data is private inside the Book class - encapsulation
    // The rest of the program accesses this data through getters and setters
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
