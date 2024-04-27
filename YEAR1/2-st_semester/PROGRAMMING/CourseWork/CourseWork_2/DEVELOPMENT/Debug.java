package AT_CLASS.HW.Course_Works.CourseWork_2.DEVELOPMENT;

public class Debug {
// To get or set text into input Field
/*
    teacherGui.lecturerIdTextField.setText("Hello");
    String lecturerId = teacherGui.lecturerIdTextField.getText();
    System.out.println(lecturerId);
*/
}


// Lecturer adding part Display Code into own terminal
/*
public void displayInfo() {
    try {
        StringBuilder info = new StringBuilder();
        for (Lecturer lecturer : addedLecturers) {
            // Append information about each lecturer to the StringBuilder
            info.append("Teacher ID: ").append(lecturer.getTeacherId()).append("\n");
            info.append("Teacher Name: ").append(lecturer.getTeacherName()).append("\n");
            info.append("Address: ").append(lecturer.getAddress()).append("\n");
            info.append("Working Type: ").append(lecturer.getWorkingType()).append("\n");
            info.append("Employment Status: ").append(lecturer.getEmploymentStatus()).append("\n");
            info.append("Department: ").append(lecturer.getDepartment()).append("\n");
            info.append("Graded Score: ").append(lecturer.getGradedScore()).append("\n");
            info.append("Years of Experience: ").append(lecturer.getYearsOfExperience()).append("\n");
            info.append("\n"); // Add a newline between each lecturer's information
        }
        // Set the complete information in the JTextArea
        message.setText(info.toString());
        // Display success message after setting all information
        message.append("\nInformation Displayed Successfully");
    } catch (Exception e) {
        // Handle specific exceptions if necessary
        message.setText("Something went wrong: " + e.getMessage());
    }
}

*/

// For Printing the arry list When Casted Teacher Class arrylist to subClass
/* // Assuming addedLecturers contains instances of Lecturer
for (Teacher teacher : addedLecturers) {
    if (teacher instanceof Lecturer) {
        Lecturer lecturer = (Lecturer) teacher;
        // Now you can access methods specific to Lecturer class
        lecturer.setGradedScore(gradedScore);
        lecturer.setDepartment(department);
        lecturer.setYearsOfExperience(yearsOfExperience);
    }
}
*/

// Actual Way to Access the Lectured class When We casted Teacher class to Lecturer
/*
public void displayInfo(){
    try {
        StringBuilder info = new StringBuilder();
        for (Teacher teacher : addedLecturers) {
            if (teacher instanceof Lecturer) {
                Lecturer lecturer = (Lecturer) teacher;
                // Append information about each lecturer to the StringBuilder
                info.append("Teacher ID: ").append(lecturer.getTeacherId()).append("\n");
                info.append("Teacher Name: ").append(lecturer.getTeacherName()).append("\n");
                info.append("Address: ").append(lecturer.getAddress()).append("\n");
                info.append("Working Type: ").append(lecturer.getWorkingType()).append("\n");
                info.append("Employment Status: ").append(lecturer.getEmploymentStatus()).append("\n");
                info.append("Department: ").append(lecturer.getDepartment()).append("\n");
                info.append("Graded Score: ").append(lecturer.getGradedScore()).append("\n");
                info.append("Years of Experience: ").append(lecturer.getYearsOfExperience()).append("\n");
                info.append("\n"); // Add a newline between each lecturer's information
            }
        }
        // Set the complete information in the JTextArea
        message.setText(info.toString());
        // Display success message after setting all information
        message.append("\nInformation Displayed Successfully");
    } catch (Exception e) {
        // Handle specific exceptions if necessary
        message.setText("Something went wrong: " + e.getMessage());
    }

}
 */

/*
// Hierarchical representation of the TeacherGui JFrame along with its contained panels, mentioning the layout used by each panel
TeacherGui (JFrame)
│
└── mainPanel (JPanel) - CardLayout
    │
    ├── Add Lecturer Section Panel (JPanel) - GridBagLayout
    │
    ├── Add Tutor Section Panel (JPanel) - GridBagLayout
    │
    ├── Grade Assignments Section Panel (JPanel) - GridBagLayout
    │
    └── Set Salary of Tutor Section Panel (JPanel) - GridBagLayout
        │
        └── Remove Tutor Panel (JPanel) - GridBagLayout



// In Depth Hierarchy of my Teacher GUi :-

TeacherGui (JFrame)
│
└── mainPanel (JPanel) - CardLayout
    │
    ├── Add Lecturer Section Panel (JPanel) - GridBagLayout
    │   ├── Lecturer ID (JLabel) & lecturerIdTextField (JTextField)
    │   ├── Lecturer Name (JLabel) & lecturerNameTextField (JTextField)
    │   ├── Address (JLabel) & lecturerAddressTextField (JTextField)
    │   ├── Working Type (JLabel) & lecturerWorkingTypeTextField (JTextField)
    │   ├── Employment Status (JLabel) & lecturerEmploymentStatusTextField (JTextField)
    │   ├── Department (JLabel) & lecturerDepartmentTextField (JTextField)
    │   ├── Graded Score (JLabel) & lecturerGradedScoreTextField (JTextField)
    │   ├── Years of Experience (JLabel) & lecturerYearsOfExperienceTextField (JTextField)
    │   ├── Add Lecturer Button (JButton)
    │   ├── Message Box (JScrollPane) - JTextArea
    │   └── Clear Button (JButton)
    │
    ├── Add Tutor Section Panel (JPanel) - GridBagLayout
    │   ├── Tutor ID (JLabel) & tutorIdTextField (JTextField)
    │   ├── Tutor Name (JLabel) & tutorNameTextField (JTextField)
    │   ├── Address (JLabel) & tutorAddressTextField (JTextField)
    │   ├── Working Type (JLabel) & tutorWorkingTypeTextField (JTextField)
    │   ├── Employment Status (JLabel) & tutorEmploymentStatusTextField (JTextField)
    │   ├── Working Hours (JLabel) & tutorWorkingHoursTextField (JTextField)
    │   ├── Salary (JLabel) & tutorSalaryTextField (JTextField)
    │   ├── Specialization (JLabel) & tutorSpecializationTextField (JTextField)
    │   ├── Academic Qualifications (JLabel) & tutorAcademicQualificationsTextField (JTextField)
    │   ├── Performance Index (JLabel) & tutorPerformanceIndexTextField (JTextField)
    │   ├── Add Tutor Button (JButton)
    │   ├── Message Box (JScrollPane) - JTextArea
    │   └── Clear Button (JButton)
    │
    ├── Grade Assignments Section Panel (JPanel) - GridBagLayout
    │   ├── Teacher ID (JLabel) & assignmentTeacherIdTextField (JTextField)
    │   ├── Graded Score (JLabel) & assignmentGradedScoreTextField (JTextField)
    │   ├── Department (JLabel) & assignmentDepartmentTextField (JTextField)
    │   ├── Years of Experience (JLabel) & assignmentYearsOfExperienceTextField (JTextField)
    │   ├── Grade Assignments Button (JButton)
    │   ├── Message Box (JScrollPane) - JTextArea
    │   └── Clear Button (JButton)
    │
    └── Set Salary of Tutor Section Panel (JPanel) - GridBagLayout
        ├── Tutor ID (JLabel) & salaryTutorIdTextField (JTextField)
        ├── New Salary (JLabel) & salaryTutorNewSalaryTextField (JTextField)
        ├── New Performance Index (JLabel) & salaryTutorNewPerformanceIndexTextField (JTextField)
        ├── Set Salary of Tutor Button (JButton)
        ├── Remove Tutor Panel (JPanel) - GridBagLayout
        │   ├── Teacher ID to Remove (JLabel) & removeTutorIdTextField (JTextField)
        │   ├── Remove Tutor Button (JButton)
        │   └── Message Box (JScrollPane) - JTextArea
        ├── Message Box (JScrollPane) - JTextArea
        ├── Clear Button (JButton)
        └── Display Button (JButton)














        public void displayLecturerInformation() {
        try {
            StringBuilder info = new StringBuilder();
            for (Teacher teacher : addedLecturers) {
                if (teacher instanceof Lecturer) {
                    Lecturer lecturer = (Lecturer) teacher;
                    // Append information about each lecturer to the StringBuilder
                    info.append("Teacher ID: ").append(lecturer.getTeacherId()).append("\n");
                    info.append("Teacher Name: ").append(lecturer.getTeacherName()).append("\n");
                    info.append("Address: ").append(lecturer.getAddress()).append("\n");
                    info.append("Working Type: ").append(lecturer.getWorkingType()).append("\n");
                    info.append("Employment Status: ").append(lecturer.getEmploymentStatus()).append("\n");
                    info.append("Department: ").append(lecturer.getDepartment()).append("\n");
                    info.append("Graded Score: ").append(lecturer.getGradedScore()).append("\n");
                    info.append("Years of Experience: ").append(lecturer.getYearsOfExperience()).append("\n");
                    info.append("\n"); // Add a newline between each lecturer's information
                }
            }
            // Set the complete information in the JTextArea
            messageForLecturer.setText(info.toString());
            // Display success message after setting all information
            messageForLecturer.append("\nInformation Displayed Successfully");
        } catch (Exception e) {
            // Handle specific exceptions if necessary
            messageForLecturer.setText("Something went wrong: " + e.getMessage());
        }

    }

*/