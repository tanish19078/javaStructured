import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private int studentId;
    private String registrationNo;
    private String branch;
    private double cgpa;
    private int graduationYear;
    private String resumePath;
    
    private List<Skill> skills;
    private List<Project> projects;
    private List<Application> applications;
    private List<Notification> notifications;

    public Student(int userId, String name, String email, String password, UserRole role, boolean active,
                   int studentId, String registrationNo, String branch, double cgpa, 
                   int graduationYear, String resumePath) {
        super(userId, name, email, password, role, active);
        this.studentId = studentId;
        this.registrationNo = registrationNo;
        this.branch = branch;
        this.cgpa = cgpa;
        this.graduationYear = graduationYear;
        this.resumePath = resumePath;
        
        this.skills = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.applications = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }


    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }

    public void removeSkill(int skillId) {
        this.skills.removeIf(skill -> skill.getSkillId() == skillId);
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public void removeProject(int projectId) {
        this.projects.removeIf(project -> project.getProjectId() == projectId);
    }

    public List<Job> searchJobs(String keyword) {
        System.out.println("Searching for jobs with keyword: " + keyword);
        return new ArrayList<>(); 
    }

    public Application applyForJob(Job job) {
        Application app = new Application();
        this.applications.add(app);
        return app;
    }

    public List<Application> viewApplications() {
        return this.applications;
    }

    public List<Interview> viewInterviews() {
        return new ArrayList<>();
    }

    public List<Offer> viewOffers() {
        return new ArrayList<>();
    }

    public List<Notification> viewNotifications() {
        return this.notifications;
    }

    @Override
    public void updateProfile() {
        System.out.println("Updating profile for student: " + getName());
    }

    @Override
    public String getProfile() {
        return "Student Profile: " + super.getProfile() + 
               ", RegNo: " + registrationNo + 
               ", Branch: " + branch + 
               ", CGPA: " + cgpa + 
               ", Graduation Year: " + graduationYear;
    }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getRegistrationNo() { return registrationNo; }
    public void setRegistrationNo(String registrationNo) { this.registrationNo = registrationNo; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    public int getGraduationYear() { return graduationYear; }
    public void setGraduationYear(int graduationYear) { this.graduationYear = graduationYear; }

    public String getResumePath() { return resumePath; }
    public void setResumePath(String resumePath) { this.resumePath = resumePath; }

    public List<Skill> getSkills() { return skills; }
    public List<Project> getProjects() { return projects; }
}