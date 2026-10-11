import java.io.*;
import java.util.*;

class Employee {
    private String employeeId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String designation;
    private String department;
    private String skills;
    private String about;

    Employee(String id, String nm, String em, String ph, String ds,
             String dp, String sk, String ab) {
        setempid(id);
        setfullname(nm);
        setemail(em);
        setphoneno(ph);
        setdesignation(ds);
        setdepartment(dp);
        setskills(sk);
        setabout(ab);
    }

    private String clean(String s) {
        String r = "";
        boolean b = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isWhitespace(c)) {
                if (!r.isEmpty()) {
                    b = true;
                }
            } else {
                if (b) {
                    r += " ";
                }
                r += c;
                b = false;
            }
        }
        return r;
    }

    private int words(String s) {
        int n = 0;
        boolean b = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isWhitespace(s.charAt(i))) {
                b = false;
            } else if (!b) {
                n++;
                b = true;
            }
        }
        return n;
    }

    public String getempid() { return employeeId; }
    public String getfullname() { return fullName; }
    public String getemail() { return email; }
    public String getphoneno() { return phoneNumber; }
    public String getdesignation() { return designation; }
    public String getdepartment() { return department; }
    public String getskills() { return skills; }
    public String getabout() { return about; }

    public void setempid(String s) { employeeId = s.trim(); }
    public void setfullname(String s) { fullName = clean(s); }
    public void setemail(String s) { email = s.trim().toLowerCase(); }
    public void setphoneno(String s) {
        phoneNumber = s.trim().replace("-", "").replace(" ", "");
    }
    public void setdesignation(String s) { designation = clean(s); }
    public void setdepartment(String s) { department = clean(s); }
    public void setskills(String s) { skills = s.trim(); }
    public void setabout(String s) { about = s.trim(); }

    public String toupper() { return fullName.toUpperCase(); }
    public String tolower() { return fullName.toLowerCase(); }
    public int getlength() { return fullName.length(); }

    public String firstchar() {
        return fullName.isEmpty() ? "" : String.valueOf(fullName.charAt(0));
    }

    public String lastchar() {
        int n = fullName.length();
        return n == 0 ? "" : String.valueOf(fullName.charAt(n - 1));
    }

    public String firstname() {
        int p = fullName.indexOf(' ');
        return p == -1 ? fullName : fullName.substring(0, p);
    }

    public String lastname() {
        int p = fullName.lastIndexOf(' ');
        return fullName.substring(p + 1);
    }

    public String middlename() {
        int p = fullName.indexOf(' '), q = fullName.lastIndexOf(' ');
        return p == q ? "" : fullName.substring(p + 1, q);
    }

    public int noofwords() { return words(fullName); }

    public String generateEmployeeInitials() {
        String s = "";
        for (int i = 0; i < fullName.length(); i++) {
            if (i == 0 || fullName.charAt(i - 1) == ' ') {
                s += fullName.charAt(i);
            }
        }
        return s.toUpperCase();
    }

    public int positionofat() { return email.indexOf('@'); }

    public String username() {
        int p = positionofat();
        return p == -1 ? "" : email.substring(0, p);
    }

    public String domainname() {
        int p = positionofat();
        return p == -1 ? "" : email.substring(p + 1);
    }

    public boolean hasat() { return email.contains("@"); }
    public boolean hasdot() { return email.endsWith(".com"); }
    public boolean belongsto() { return domainname().equals("example.com"); }
    public boolean comparedomain(String s) { return domainname().equalsIgnoreCase(s); }
    public int length() { return phoneNumber.length(); }

    public String firstthreedigits() {
        int n = length();
        return n < 3 ? phoneNumber : phoneNumber.substring(0, 3);
    }

    public String lastfourdigits() {
        int n = length();
        return n < 4 ? phoneNumber : phoneNumber.substring(n - 4);
    }

    public boolean checklength() {
        if (length() != 10) {
            return false;
        }
        for (int i = 0; i < length(); i++) {
            char c = phoneNumber.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public String toUpperCaseDesignation() { return designation.toUpperCase(); }
    public String toLowerCaseDesignation() { return designation.toLowerCase(); }
    public boolean startsWith(String s) { return designation.startsWith(s); }
    public boolean endsWith(String s) { return designation.endsWith(s); }
    public boolean contains(String s) { return designation.contains(s); }
    public String replace() { return designation.replace(" ", "-"); }

    public boolean knowsstring(String s) {
        int p = 0;
        while (p < skills.length()) {
            int q = skills.indexOf(',', p);
            if (q == -1) {
                q = skills.length();
            }
            if (skills.substring(p, q).trim().equalsIgnoreCase(s.trim())) {
                return true;
            }
            p = q + 1;
        }
        return false;
    }

    public int posfirstcomma() { return skills.indexOf(','); }

    public String firstskill() {
        int p = posfirstcomma();
        return p == -1 ? skills : skills.substring(0, p).trim();
    }

    public String lastskill() {
        int p = skills.lastIndexOf(',');
        return skills.substring(p + 1).trim();
    }

    public int skillcount() {
        int n = 0, p = 0;
        while (p < skills.length()) {
            int q = skills.indexOf(',', p);
            if (q == -1) {
                q = skills.length();
            }
            if (!skills.substring(p, q).trim().isEmpty()) {
                n++;
            }
            p = q + 1;
        }
        return n;
    }

    public String replaceskill() {
        String s = "";
        int p = 0;
        while (p < skills.length()) {
            int q = skills.indexOf(',', p);
            if (q == -1) {
                q = skills.length();
            }
            String a = skills.substring(p, q).trim();
            if (!a.isEmpty()) {
                if (!s.isEmpty()) {
                    s += " | ";
                }
                s += a;
            }
            p = q + 1;
        }
        return s;
    }

    public int lengthofabout() { return about.length(); }
    public int wordcount() { return words(about); }
    public boolean containsword(String s) {
        return about.toLowerCase().contains(s.toLowerCase());
    }
    public String replaceword(String a, String b) { return about.replace(a, b); }
    public String toUpperCaseAbout() { return about.toUpperCase(); }

    public String extractthirtycharacters() {
        return about.length() <= 30 ? about : about.substring(0, 30);
    }

    public boolean idstarts() { return employeeId.startsWith("EMP"); }

    public String idprefix() {
        int n = employeeId.length();
        return n < 3 ? employeeId : employeeId.substring(0, 3);
    }

    public String idnumber() {
        return employeeId.length() < 3 ? "" : employeeId.substring(3);
    }

    public int idlength() { return employeeId.length(); }

    public String employeecode() {
        String s = idnumber();
        if (s.length() > 4) {
            s = s.substring(s.length() - 4);
        }
        return idprefix().concat(s);
    }

    public String getEmployeeType() { return "Employee"; }

    public void displayAnalysis() {
        System.out.println("\n========== STRING ANALYSIS ==========");
        System.out.println("Name uppercase: " + toupper());
        System.out.println("Name lowercase: " + tolower());
        System.out.println("Name length: " + getlength());
        System.out.println("First character: " + firstchar());
        System.out.println("Last character: " + lastchar());
        System.out.println("First name: " + firstname());
        System.out.println("Middle name: " + middlename());
        System.out.println("Last name: " + lastname());
        System.out.println("Name word count: " + noofwords());
        System.out.println("Initials: " + generateEmployeeInitials());
        System.out.println("Cleaned email: " + getemail());
        System.out.println("Position of @: " + positionofat());
        System.out.println("Email username: " + username());
        System.out.println("Email domain: " + domainname());
        System.out.println("Contains @: " + hasat());
        System.out.println("Ends with .com: " + hasdot());
        System.out.println("Belongs to example.com: " + belongsto());
        System.out.println("Domain equals Example.COM: " + comparedomain("Example.COM"));
        System.out.println("Cleaned phone: " + getphoneno());
        System.out.println("Phone length: " + length());
        System.out.println("First three digits: " + firstthreedigits());
        System.out.println("Last four digits: " + lastfourdigits());
        System.out.println("Exactly 10 digits: " + checklength());
        System.out.println("Designation uppercase: " + toUpperCaseDesignation());
        System.out.println("Designation lowercase: " + toLowerCaseDesignation());
        System.out.println("Starts with Software: " + startsWith("Software"));
        System.out.println("Ends with Engineer: " + endsWith("Engineer"));
        System.out.println("Contains Engineer: " + contains("Engineer"));
        System.out.println("Hyphenated designation: " + replace());
        System.out.println("Knows Java: " + knowsstring("Java"));
        System.out.println("Knows React: " + knowsstring("React"));
        System.out.println("Knows Python: " + knowsstring("Python"));
        System.out.println("First comma position: " + posfirstcomma());
        System.out.println("First skill: " + firstskill());
        System.out.println("Last skill: " + lastskill());
        System.out.println("Skill count: " + skillcount());
        System.out.println("Formatted skills: " + replaceskill());
        System.out.println("About length: " + lengthofabout());
        System.out.println("About word count: " + wordcount());
        System.out.println("About contains Java: " + containsword("Java"));
        System.out.println("About contains Python: " + containsword("Python"));
        System.out.println("Updated about: " + replaceword("Java", "Full Stack Java"));
        System.out.println("About uppercase: " + toUpperCaseAbout());
        System.out.println("Short summary: " + extractthirtycharacters());
        System.out.println("ID starts with EMP: " + idstarts());
        System.out.println("ID prefix: " + idprefix());
        System.out.println("ID numeric portion: " + idnumber());
        System.out.println("ID length: " + idlength());
        System.out.println("Employee code: " + employeecode());
    }

    public void displayProfile() {
        System.out.println("\n========== EMPLOYEE PROFILE ==========");
        System.out.println("Employee ID    : " + getempid());
        System.out.println("Employee Type  : " + getEmployeeType());
        System.out.println("Name           : " + getfullname());
        System.out.println("Initials       : " + generateEmployeeInitials());
        System.out.println("Email          : " + getemail());
        System.out.println("Email Username : " + username());
        System.out.println("Email Domain   : " + domainname());
        System.out.println("Phone          : " + getphoneno());
        System.out.println("Designation    : " + getdesignation());
        System.out.println("Department     : " + getdepartment().toUpperCase());
        System.out.println("\nSkills:\n" + replaceskill());
        System.out.println("\nAbout:\n" + getabout());
    }
}

class Developer extends Employee {
    private String programmingLanguage;

    Developer(String id, String nm, String em, String ph, String ds,
              String dp, String sk, String ab, String pl) {
        super(id, nm, em, ph, ds, dp, sk, ab);
        setProgrammingLanguage(pl);
    }

    public String getProgrammingLanguage() { return programmingLanguage; }
    public void setProgrammingLanguage(String s) { programmingLanguage = s.trim(); }

    @Override
    public String getEmployeeType() { return "Developer"; }

    @Override
    public void displayProfile() {
        super.displayProfile();
        System.out.println("Programming Language: " + getProgrammingLanguage());
    }
}

class Manager extends Employee {
    private int teamSize;

    Manager(String id, String nm, String em, String ph, String ds,
            String dp, String sk, String ab, int n) {
        super(id, nm, em, ph, ds, dp, sk, ab);
        setTeamSize(n);
    }

    public int getTeamSize() { return teamSize; }
    public void setTeamSize(int n) { teamSize = n; }

    @Override
    public String getEmployeeType() { return "Manager"; }

    @Override
    public void displayProfile() {
        super.displayProfile();
        System.out.println("Team Size: " + getTeamSize());
    }
}

public class empanalyse {
    private static String read(Scanner sc, String s) {
        System.out.print(s + ": ");
        return sc.nextLine();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String id = read(sc, "Employee ID");
        String nm = read(sc, "Full name");
        String em = read(sc, "Email");
        String ph = read(sc, "Phone number");
        String ds = read(sc, "Designation");
        String dp = read(sc, "Department");
        String sk = read(sc, "Skills");
        String ab = read(sc, "About");

        System.out.print("Employee type (1 Employee, 2 Developer, 3 Manager): ");
        int k = sc.nextInt();
        sc.nextLine();
        Employee e;

        if (k == 1) {
            e = new Employee(id, nm, em, ph, ds, dp, sk, ab);
        } else if (k == 2) {
            String pl = read(sc, "Programming language");
            e = new Developer(id, nm, em, ph, ds, dp, sk, ab, pl);
        } else if (k == 3) {
            System.out.print("Team size: ");
            int n = sc.nextInt();
            e = new Manager(id, nm, em, ph, ds, dp, sk, ab, n);
        } else {
            System.out.println("Invalid employee type.");
            sc.close();
            return;
        }

        e.displayAnalysis();
        e.displayProfile();
        System.out.println("======================================");
        sc.close();
    }
}
