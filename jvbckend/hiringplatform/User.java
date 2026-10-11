public abstract class User implements Authenticatable {
    private int userId;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private boolean active;

    public User() {
    }

    public User(int userId, String name, String email, String password, UserRole role, boolean active) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public abstract void updateProfile();

    public String getProfile() {
        return "User{id=" + userId + ", name='" + name + "', email='" + email
                + "', role=" + role + ", active=" + active + "}";
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    @Override
    public boolean login(String email, String password) {
        return this.active
                && this.email != null
                && this.email.equalsIgnoreCase(email)
                && this.password != null
                && this.password.equals(password);
    }

    @Override
    public void logout() {
        // default no-op
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

interface Authenticatable {
    boolean login(String email, String password);
    void logout();
}

enum UserRole {
    STUDENT, RECRUITER, ADMIN
}

class Student extends User {
    private String major;
    private int graduationYear;

    Student(int userId, String name, String email, String password, UserRole role,
            boolean active, String major, int graduationYear) {
        super(userId, name, email, password, role, active);
        this.major = major;
        this.graduationYear = graduationYear;
    }

    @Override
    public void updateProfile() {
        // Placeholder for stude
    }

    @Override
    public String getProfile() {
        return "Student Profile: " + super.getProfile()
                + ", Major: " + major
                + ", Graduation Year: " + graduationYear;
    }
}

class Admin extends User {
    private String department;

    Admin(int userId, String name, String email, String password, UserRole role,
          boolean active, String department) {
        super(userId, name, email, password, role, active);
        this.department = department;
    }

    @Override
    public void updateProfile() {
    }

    @Override
    public String getProfile() {
        return "Admin Profile: " + super.getProfile()
                + ", Department: " + department;
    }
}