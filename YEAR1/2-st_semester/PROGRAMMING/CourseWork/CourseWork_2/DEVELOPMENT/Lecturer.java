package AT_CLASS.HW.Course_Works.CourseWork_2.DEVELOPMENT;

public class Lecturer extends Teacher {
    // Additional attributes for Lecturer
    private String department;
    private int yearsOfExperience;
    private int gradedScore;
    private boolean hasGraded;

    // Constructor for Lecturer which uses the superclass constructor
    public Lecturer(int teacherId, String teacherName, String address,
                    String workingType, String employmentStatus,
                    String department, int yearsOfExperience) {
        super(teacherId, teacherName, address, workingType, employmentStatus);
        this.department = department;
        this.yearsOfExperience = yearsOfExperience;
        this.gradedScore = 0; // Assign gradedScore as 0
        this.hasGraded = false; // Assign hasGraded as false
    }

    // Accessor methods
    public String getDepartment() {
        return department;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public int getGradedScore() {
        return gradedScore;
    }

    public boolean getHasGraded() {
        return hasGraded;
    }

    // Mutator method for gradedScore
    public void setGradedScore(int score) {
        this.gradedScore = score;
    }

    // Method to grade assignments
    public void gradeAssignment(int gradedScore, String department, int yearsOfExperience) {
        if (yearsOfExperience >= 5 && this.department.equals(department)) {
            if (gradedScore < 0 || gradedScore > 100) {
                System.out.println("Out of range grading score; it must be between 0 and 100");
            } else {
                // Grading logic
                if (gradedScore >= 70) {
                    System.out.println("Grade: A");
                } else if (gradedScore >= 60) {
                    System.out.println("Grade: B");
                } else if (gradedScore >= 50) {
                    System.out.println("Grade: C");
                } else if (gradedScore >= 40) {
                    System.out.println("Grade: D");
                } else {
                    System.out.println("Grade: E");
                }
                this.gradedScore = gradedScore;
                this.hasGraded = true;
            }
        } else {
            // Display a suitable message when assignments have already been graded.
            System.out.println("Assignments have not graded.");
        }
    }

    // Override the display method to include Lecturer details
    @Override
    public void display() {
        super.display();
        System.out.println("Department: " + department);
        System.out.println("Years of Experience: " + yearsOfExperience);
        if (hasGraded) {
            System.out.println("Graded Score: " + gradedScore);
        } else {
            System.out.println("This Lecturer has not graded any assignment yet.");
        }
    }
}
