public class Tutor extends Teacher {
    // Additional attributes
    private double salary;
    private String specialization;
    private String academicQualifications;
    private int performanceIndex;
    private boolean isCertified;

    // Constructor
    public Tutor(int teacherId, String teacherName, String address, String workingType, String employmentStatus,
                 int workingHours, double salary, String specialization, String academicQualifications, int performanceIndex) {
        super(teacherId, teacherName, address, workingType, employmentStatus);
        setWorkingHours(workingHours);
        this.salary = salary;
        this.specialization = specialization;
        this.academicQualifications = academicQualifications;
        this.performanceIndex = performanceIndex;
        this.isCertified = false;
    }

    // Accessor methods
    public double getSalary() {
        return salary;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getAcademicQualifications() {
        return academicQualifications;
    }

    public int getPerformanceIndex() {
        return performanceIndex;
    }

    public boolean isCertified() {
        return isCertified;
    }

    // Mutator methods
 public void setSalary(double newSalary, int newPerformanceIndex) {
        if (performanceIndex >= 3 && getWorkingHours() > 20) {
            double appraisal = 0.05;
            if (performanceIndex >= 8) {
                appraisal = 0.1;
            } else if (performanceIndex == 10) {
                appraisal = 0.2;
            }
            this.salary = newSalary + (newSalary * appraisal);
            this.isCertified = true;
            System.out.println("Salary has been approved, and the appraisal has been applied!");
        } else {
            System.out.println("Tutor cannot be certified yet. Salary cannot be approved.");
        }
    }

    public void removeTutor() {
        if (!isCertified) {
            salary = 0.0;
            specialization = "";
            academicQualifications = "";
            performanceIndex = 0;
            isCertified = false;
            System.out.println("Tutor is removed successfully.");
        } else {
            System.out.println("The tutor is certified. Cannot remove certified tutor.");
        }
}
    // Method to display Tutor details
 public void display() {
        super.display(); // Calling here display method of Teacher class to display teacher details
        System.out.println("Salary: " + salary);
        System.out.println("Specialization: " + specialization);
        System.out.println("Academic Qualifications: " + academicQualifications);
        System.out.println("Performance Index: " + performanceIndex);
        System.out.println("Certified: " + isCertified);
    }
}
