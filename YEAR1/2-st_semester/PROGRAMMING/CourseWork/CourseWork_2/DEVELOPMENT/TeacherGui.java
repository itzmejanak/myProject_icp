package AT_CLASS.HW.Course_Works.CourseWork_2.DEVELOPMENT;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class TeacherGui extends JFrame implements ActionListener {
    ArrayList<Teacher> addedTeachers = new ArrayList<>();

    JTextField lecturerIdTextField, lecturerNameTextField, lecturerAddressTextField, lecturerWorkingTypeTextField,
            lecturerEmploymentStatusTextField, lecturerDepartmentTextField, lecturerGradedScoreTextField, lecturerYearsOfExperienceTextField,
            tutorIdTextField, tutorNameTextField, tutorAddressTextField, tutorWorkingTypeTextField,
            tutorEmploymentStatusTextField, tutorWorkingHoursTextField, tutorSalaryTextField,
            tutorSpecializationTextField, tutorAcademicQualificationsTextField, tutorPerformanceIndexTextField,
            assignmentTeacherIdTextField, assignmentGradedScoreTextField, assignmentDepartmentTextField, assignmentYearsOfExperienceTextField,
            salaryTutorIdTextField, salaryTutorNewSalaryTextField, salaryTutorNewPerformanceIndexTextField, removeTutorIdTextField;
    JPanel mainPanel;
    CardLayout cardLayout;
    JTextArea messageForLecturer, messageForTutor, messageForAssignment, messageForSetSalary;
    JButton addLecturer, addTutor, gradeAssignment, setSalaryOfTutor, removeTutor, displayButton, nextButton, preButton, clearLecturer, clearTutor, clearAssignment, clearSetSalaryOfTutor, menuAddLecturerButton,
            menuAddTutorButton, menuGradeAssignmentButton, menuSetSalaryButton, mainMenuBtnForLecturer, mainMenuBtnForTutor, mainMenuBtnForGrade, mainMenuBtnForSalay, nowDisplay, nowRemoveTutor, displayBtnForDisplaying;
    JTable tutorTable, lecturerTable;
    Object[][] lecturerData,tutorData;


    /*____________________________________________________________________________________________________________________________________________________________*/
    // GUI (GRAPHIC USER INTERFACE) PART :-
    public TeacherGui() {
        setupFrame();
        addSections();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true); // Make the frame visible
    }

    public void addSections() {
        // Add sections to the main panel
        mainPanel.add(createMainMenuPanel(), "main menu");
        mainPanel.add(createAddLecturerSection(), "addLecturer");
        mainPanel.add(createAddTutorSection(), "addTutor");
        mainPanel.add(createGradeAssignmentsSection(), "gradeAssignments");
        mainPanel.add(createSetSalaryOfTutorSection(), "setSalaryOfTutor");
        mainPanel.add(createDisplaySection(), "displaySection");
    }

    public void setupFrame() {
        setTitle("University Management System"); // Set the title of the frame
        setSize(1010, 700); // Set the size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Specify what happens when the frame is closed
        setResizable(false);

        // Create the main panel with CardLayout for switching pages
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout); // Set CardLayout to the main panel

        getContentPane().add(mainPanel, BorderLayout.CENTER); // Add the main panel to the frame's content pane

        // Create Next button
        nextButton = new JButton("Menu ≣");
        nextButton.setBackground(new Color(73, 84, 100));
        nextButton.setForeground(Color.white);
        nextButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        nextButton.addActionListener(this);

        // Create Previous button
        preButton = new JButton("Previous  \uD83D\uDD02");
        preButton.setBackground(new Color(73, 84, 100));
        preButton.setForeground(Color.white);
        preButton.setFont(new Font("Montserrat", Font.PLAIN, 13));
        preButton.addActionListener(this);

        // Create and set position of button on panels
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(230, 241, 228));
        buttonPanel.add(nextButton);

        JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel1.setBackground(new Color(230, 241, 228));
        buttonPanel1.add(preButton);

        // Create a container panel to hold both button panels
        JPanel buttonsContainer = new JPanel(new BorderLayout());
        buttonsContainer.setBackground(new Color(73, 84, 100));
        buttonsContainer.add(buttonPanel, BorderLayout.EAST);
        buttonsContainer.add(buttonPanel1, BorderLayout.WEST);

        // Add the container panel to the frame's content pane
        getContentPane().add(buttonsContainer, BorderLayout.SOUTH);
    }


    public JPanel createMainMenuPanel() {
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new BorderLayout());
        mainMenuPanel.setBackground(new Color(112, 119, 161)); // Set background color

        // Create panel for topic label
        JPanel topicPanel = new JPanel();
        topicPanel.setBackground(new Color(73, 84, 100)); // Set background color
        JLabel topicLabel = new JLabel("University Management System");
        topicLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font size and style
        topicLabel.setForeground(new Color(255, 153, 0)); // Set text color
        topicPanel.add(topicLabel);

        // Create left panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(143, 79, 10));
        buttonPanel.setLayout(new GridLayout(6, 1, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Create buttons
        menuAddLecturerButton = new JButton("Add Lecturer");
        menuAddLecturerButton.addActionListener(this);
        menuAddTutorButton = new JButton("Add Tutor");
        menuAddTutorButton.addActionListener(this);
        menuGradeAssignmentButton = new JButton("Grade Assignment");
        menuGradeAssignmentButton.addActionListener(this);
        menuSetSalaryButton = new JButton("Set Salary");
        menuSetSalaryButton.addActionListener(this);
        nowRemoveTutor = new JButton("Remove Tutor");
        nowRemoveTutor.addActionListener(this);
        nowDisplay = new JButton("Display");
        nowDisplay.addActionListener(this);

        // Apply styling to buttons
        applyButtonStyle(menuAddLecturerButton);
        applyButtonStyle(menuAddTutorButton);
        applyButtonStyle(menuGradeAssignmentButton);
        applyButtonStyle(menuSetSalaryButton);
        applyButtonStyle(nowRemoveTutor);
        applyButtonStyle(nowDisplay);

        // Add buttons to button panel
        buttonPanel.add(menuAddLecturerButton);
        buttonPanel.add(menuAddTutorButton);
        buttonPanel.add(menuGradeAssignmentButton);
        buttonPanel.add(menuSetSalaryButton);
        buttonPanel.add(nowRemoveTutor);
        buttonPanel.add(nowDisplay);

        // Create right panel for description
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setBackground(new Color(73, 84, 100)); // Set background color
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Create labels for button descriptions
        String htmlContent = "<html>\n" +
                "    <div style='text-align: justify; color: #fff; font-size: 16px; padding: 21px 250px 1px 20px; background-color: rgb(73, 84, 100); border-radius: 10px; box-shadow: 0 8px 16px rgba(0, 0, 0, 1.1); border: 4px solid #ccc;'>\n" +
                "        <span style='font-weight: 600; color: white;'>Description:</span><br><br>\n" +
                "        <ul style='list-style-type: none; padding-left: 0;'>\n" +
                "            <li style='margin-bottom: 16px; padding-bottom: 8px; border-bottom: 1px solid #ccc;'>\n" +
                "                <span style='font-weight: 600; color: #ff6600;'>Add Lecturer:</span><br>\n" +
                "                <span style='font-size: 12px;'>Add a new lecturer to the system.</span>\n" +
                "            </li>\n" +
                "            <li style='margin-bottom: 16px; padding-bottom: 8px; border-bottom: 1px solid #ccc;'>\n" +
                "                <span style='font-weight: 600; color: #ff6600;'>Add Tutor:</span><br>\n" +
                "                <span style='font-size: 12px;'>Add a new tutor to the system.</span>\n" +
                "            </li>\n" +
                "            <li style='margin-bottom: 16px; padding-bottom: 8px; border-bottom: 1px solid #ccc;'>\n" +
                "                <span style='font-weight: 600; color: #ff6600;'>Grade Assignment:</span><br>\n" +
                "                <span style='font-size: 12px;'>Grade assignments submitted by students.</span>\n" +
                "            </li>\n" +
                "            <li style='margin-bottom: 16px; padding-bottom: 8px; border-bottom: 1px solid #ccc;'>\n" +
                "                <span style='font-weight: 600; color: #ff6600;'>Set Salary:</span><br>\n" +
                "                <span style='font-size: 12px;'>Set salary for lecturers or tutors.</span>\n" +
                "            </li>\n" +
                "            <li style='margin-bottom: 16px; padding-bottom: 8px; border-bottom: 1px solid #ccc;'>\n" +
                "                <span style='font-weight: 600; color: #ff6600;'>Remove Tutor:</span><br>\n" +
                "                <span style='font-size: 12px;'>Remove a tutor from the system.</span>\n" +
                "            </li>\n" +
                "            <li style='padding-bottom: 8px;'>\n" +
                "                <span style='font-weight: 600; color: #ff6600;'>Display:</span><br>\n" +
                "                <span style='font-size: 12px;'>Display various information from the system.</span>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "</html>";

        JLabel descriptionLabel = new JLabel(htmlContent);

        // Add description label to description panel
        descriptionPanel.add(descriptionLabel, BorderLayout.CENTER);

        // Add panels to main menu panel
        mainMenuPanel.add(topicPanel, BorderLayout.NORTH);
        mainMenuPanel.add(buttonPanel, BorderLayout.EAST);
        mainMenuPanel.add(descriptionPanel, BorderLayout.CENTER);

        return mainMenuPanel;
    }


    private static void applyButtonStyle(JButton button) {
        button.setBackground(new Color(236, 102, 31));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Montserrat", Font.BOLD, 18));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public JPanel createAddLecturerSection() {
        JPanel lecturerSectionPanel = new JPanel();
        lecturerSectionPanel.setLayout(new BorderLayout());

        JPanel lecturerSectionPanelLeft = new JPanel();
        lecturerSectionPanelLeft.setPreferredSize(new Dimension(500,700));
        lecturerSectionPanelLeft.setBorder(BorderFactory.createTitledBorder("Add Lecturer Section"));
        lecturerSectionPanelLeft.setLayout(new GridBagLayout()); // Set GridBagLayout
        lecturerSectionPanelLeft.setBackground(new Color(244, 244, 242)); // Set background color

        JPanel lecturerSectionPanelRight = new JPanel(); // Create a new JPanel for Add Lecturer section
        lecturerSectionPanelRight.setBackground(new Color(232, 232, 232));
        lecturerSectionPanelRight.setLayout(new BorderLayout()); // Set GridBagLayout for right panel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Set insets for spacing

        // Add JLabels and JTextFields for each input field
        JLabel lecturerIdLabel = new JLabel("Teacher ID:");
        lecturerIdLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        lecturerIdTextField = new JTextField(15); // Set the preferred width
        lecturerIdTextField.setPreferredSize(new Dimension(300, 25));
        lecturerSectionPanelLeft.add(lecturerIdLabel, gbc);

        gbc.gridx = 1;
        lecturerSectionPanelLeft.add(lecturerIdTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lecturerNameLabel = new JLabel("Teacher Name:");
        lecturerNameLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        lecturerSectionPanelLeft.add(lecturerNameLabel, gbc);

        gbc.gridx = 1;
        lecturerNameTextField = new JTextField(15); // Set the preferred width
        lecturerNameTextField.setPreferredSize(new Dimension(200, 25));
        lecturerSectionPanelLeft.add(lecturerNameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lecturerAddressLabel = new JLabel("Address:");
        lecturerAddressLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        lecturerSectionPanelLeft.add(lecturerAddressLabel, gbc);

        gbc.gridx = 1;
        lecturerAddressTextField = new JTextField(15); // Set the preferred width
        lecturerAddressTextField.setPreferredSize(new Dimension(200, 25));
        lecturerSectionPanelLeft.add(lecturerAddressTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lecturerWorkingTypeLabel = new JLabel("Working Type:");
        lecturerWorkingTypeLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        lecturerSectionPanelLeft.add(lecturerWorkingTypeLabel, gbc);

        gbc.gridx = 1;
        lecturerWorkingTypeTextField = new JTextField(15); // Set the preferred width
        lecturerWorkingTypeTextField.setPreferredSize(new Dimension(200, 25));
        lecturerSectionPanelLeft.add(lecturerWorkingTypeTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lecturerEmploymentStatusLabel = new JLabel("Employment Status:");
        lecturerEmploymentStatusLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        lecturerSectionPanelLeft.add(lecturerEmploymentStatusLabel, gbc);

        gbc.gridx = 1;
        lecturerEmploymentStatusTextField = new JTextField(15); // Set the preferred width
        lecturerEmploymentStatusTextField.setPreferredSize(new Dimension(200, 25));
        lecturerSectionPanelLeft.add(lecturerEmploymentStatusTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lecturerDepartmentLabel = new JLabel("Department:");
        lecturerDepartmentLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        lecturerSectionPanelLeft.add(lecturerDepartmentLabel, gbc);

        gbc.gridx = 1;
        lecturerDepartmentTextField = new JTextField(15); // Set the preferred width
        lecturerDepartmentTextField.setPreferredSize(new Dimension(200, 25));
        lecturerSectionPanelLeft.add(lecturerDepartmentTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lecturerGradedScoreLabel = new JLabel("Graded Score:");
        lecturerGradedScoreLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        lecturerSectionPanelLeft.add(lecturerGradedScoreLabel, gbc);

        gbc.gridx = 1;
        lecturerGradedScoreTextField = new JTextField(15); // Set the preferred width
        lecturerGradedScoreTextField.setPreferredSize(new Dimension(200, 25));
        lecturerSectionPanelLeft.add(lecturerGradedScoreTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lecturerYearsOfExperienceLabel = new JLabel("Years of Experience:");
        lecturerYearsOfExperienceLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        lecturerSectionPanelLeft.add(lecturerYearsOfExperienceLabel, gbc);

        gbc.gridx = 1;
        lecturerYearsOfExperienceTextField = new JTextField(15); // Set the preferred width
        lecturerYearsOfExperienceTextField.setPreferredSize(new Dimension(200, 25));
        lecturerSectionPanelLeft.add(lecturerYearsOfExperienceTextField, gbc);


        // Add "Add Lecturer" button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span two columns
        addLecturer = new JButton("Add Lecturer");
        addLecturer.setBackground(new Color(73, 84, 100));
        addLecturer.setForeground(Color.white);
        addLecturer.setFont(new Font("Montserrat", Font.BOLD, 15));
        addLecturer.addActionListener(this);
        lecturerSectionPanelLeft.add(addLecturer, gbc); // Add the button to the panel

        // Add main Menu Button
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        mainMenuBtnForLecturer = new JButton("Main Menu");
        mainMenuBtnForLecturer.setBackground(new Color(249, 148, 23));
        mainMenuBtnForLecturer.setForeground(Color.white);
        mainMenuBtnForLecturer.setFont(new Font("Montserrat", Font.BOLD, 15));
        mainMenuBtnForLecturer.addActionListener(this);
        lecturerSectionPanelLeft.add(mainMenuBtnForLecturer, gbc);

        // Add Clear Button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        clearLecturer = new JButton("Clear");
        clearLecturer.setBackground(Color.red);
        clearLecturer.setForeground(Color.white);
        clearLecturer.setFont(new Font("Montserrat", Font.BOLD, 15));
        clearLecturer.addActionListener(this);
        lecturerSectionPanelLeft.add(clearLecturer, gbc);

        // Add message box
        JLabel labelTopicLecturer = new JLabel("Lecturer Section: 😊");
        labelTopicLecturer.setForeground(new Color(73, 84, 100));
        labelTopicLecturer.setFont(new Font("Montserrat", Font.BOLD, 30));
        labelTopicLecturer.setHorizontalAlignment(SwingConstants.CENTER); // Align label to center
        labelTopicLecturer.setBorder(BorderFactory.createEmptyBorder(105, 0, 0, 0)); // Add top margin
        lecturerSectionPanelRight.add(labelTopicLecturer, BorderLayout.NORTH); // Add label to the top
        lecturerSectionPanelRight.add(createMessageBoxForLecturer(), BorderLayout.SOUTH); // Add message box to the center



        // Add panels to main panel
        lecturerSectionPanel.add(lecturerSectionPanelLeft, BorderLayout.WEST);
        lecturerSectionPanel.add(lecturerSectionPanelRight, BorderLayout.EAST);

        return lecturerSectionPanel; // Return the main panel
    }






    public JPanel createAddTutorSection() {
        JPanel tutorSectionPanel = new JPanel(); // Create a new JPanel for Add Tutor section
        tutorSectionPanel.setBorder(BorderFactory.createTitledBorder("Add Tutor Section"));
        tutorSectionPanel.setLayout(new BorderLayout()); // Set BorderLayout

        // Left panel for labels and text fields
        JPanel tutorSectionPanelLeft = new JPanel();
        tutorSectionPanelLeft.setPreferredSize(new Dimension(500,700));
        tutorSectionPanelLeft.setLayout(new GridBagLayout());
        tutorSectionPanelLeft.setBackground(new Color(232, 232, 232));

        // Right panel for message box
        JPanel tutorSectionPanelRight = new JPanel();
        tutorSectionPanelRight.setBackground(new Color(244, 244, 242));
        tutorSectionPanelRight.setLayout(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Set insets for spacing

        // Add JLabels and JTextFields for each input field to the left panel
        JLabel tutorIdLabel = new JLabel("Teacher ID:");
        tutorIdLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        tutorIdTextField = new JTextField(15); // Set the preferred width
        tutorIdTextField.setPreferredSize(new Dimension(200, 25));
        tutorSectionPanelLeft.add(tutorIdLabel, gbc);

        gbc.gridx = 1;
        tutorSectionPanelLeft.add(tutorIdTextField, gbc);


        gbc.gridx = 0;
        gbc.gridy++;
        JLabel tutorNameLabel = new JLabel("Teacher Name:");
        tutorNameLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        tutorSectionPanelLeft.add(tutorNameLabel, gbc);

        gbc.gridx = 1;
        tutorNameTextField = new JTextField(15); // Set the preferred width
        tutorNameTextField.setPreferredSize(new Dimension(200, 25));
        tutorSectionPanelLeft.add(tutorNameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel tutorAddressLabel = new JLabel("Address:");
        tutorAddressLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        tutorSectionPanelLeft.add(tutorAddressLabel, gbc);

        gbc.gridx = 1;
        tutorAddressTextField = new JTextField(15); // Set the preferred width
        tutorAddressTextField.setPreferredSize(new Dimension(200, 25));
        tutorSectionPanelLeft.add(tutorAddressTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel tutorWorkingTypeLabel = new JLabel("Working Type:");
        tutorWorkingTypeLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        tutorSectionPanelLeft.add(tutorWorkingTypeLabel, gbc);

        gbc.gridx = 1;
        tutorWorkingTypeTextField = new JTextField(15); // Set the preferred width
        tutorWorkingTypeTextField.setPreferredSize(new Dimension(200, 25));
        tutorSectionPanelLeft.add(tutorWorkingTypeTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel tutorEmploymentStatusLabel = new JLabel("Employment Status:");
        tutorEmploymentStatusLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        tutorSectionPanelLeft.add(tutorEmploymentStatusLabel, gbc);

        gbc.gridx = 1;
        tutorEmploymentStatusTextField = new JTextField(15);
        tutorEmploymentStatusTextField.setPreferredSize(new Dimension(200, 25));
        tutorSectionPanelLeft.add(tutorEmploymentStatusTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel tutorWorkingHoursLabel = new JLabel("Working Hours:");
        tutorWorkingHoursLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        tutorSectionPanelLeft.add(tutorWorkingHoursLabel, gbc);

        gbc.gridx = 1;
        tutorWorkingHoursTextField = new JTextField(15); // Set the preferred width
        tutorWorkingHoursTextField.setPreferredSize(new Dimension(200, 25));
        tutorSectionPanelLeft.add(tutorWorkingHoursTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel tutorSalaryLabel = new JLabel("Salary:");
        tutorSalaryLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        tutorSectionPanelLeft.add(tutorSalaryLabel, gbc);

        gbc.gridx = 1;
        tutorSalaryTextField = new JTextField(15); // Set the preferred width
        tutorSalaryTextField.setPreferredSize(new Dimension(200, 25));
        tutorSectionPanelLeft.add(tutorSalaryTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel tutorSpecializationLabel = new JLabel("Specialization:");
        tutorSpecializationLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        tutorSectionPanelLeft.add(tutorSpecializationLabel, gbc);

        gbc.gridx = 1;
        tutorSpecializationTextField = new JTextField(15); // Set the preferred width
        tutorSpecializationTextField.setPreferredSize(new Dimension(200, 25));
        tutorSectionPanelLeft.add(tutorSpecializationTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel tutorAcademicQualificationsLabel = new JLabel("Academic Qualifications:");
        tutorAcademicQualificationsLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        tutorSectionPanelLeft.add(tutorAcademicQualificationsLabel, gbc);

        gbc.gridx = 1;
        tutorAcademicQualificationsTextField = new JTextField(15); // Set the preferred width
        tutorAcademicQualificationsTextField.setPreferredSize(new Dimension(200, 25));
        tutorSectionPanelLeft.add(tutorAcademicQualificationsTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel tutorPerformanceIndexLabel = new JLabel("Performance Index:");
        tutorPerformanceIndexLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        tutorSectionPanelLeft.add(tutorPerformanceIndexLabel, gbc);

        gbc.gridx = 1;
        tutorPerformanceIndexTextField = new JTextField(15); // Set the preferred width
        tutorPerformanceIndexTextField.setPreferredSize(new Dimension(200, 25));
        tutorSectionPanelLeft.add(tutorPerformanceIndexTextField, gbc);

        // Add "Add Tutor" button to the left panel
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span two columns
        addTutor = new JButton("Add Tutor");
        addTutor.setBackground(new Color(73, 84, 100));
        addTutor.setForeground(Color.white);
        addTutor.setFont(new Font("Montserrat", Font.BOLD, 15));
        addTutor.addActionListener(this); // Assuming ActionListener is implemented in the class
        tutorSectionPanelLeft.add(addTutor, gbc);

        // Add main Menu Button
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        mainMenuBtnForTutor = new JButton("Main Menu");
        mainMenuBtnForTutor.setBackground(new Color(249, 148, 23));
        mainMenuBtnForTutor.setForeground(Color.white);
        mainMenuBtnForTutor.setFont(new Font("Montserrat", Font.BOLD, 15));
        mainMenuBtnForTutor.addActionListener(this);
        tutorSectionPanelLeft.add(mainMenuBtnForTutor, gbc);

        // Add Clear Button to the left panel
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        clearTutor = new JButton("Clear");
        clearTutor.setBackground(Color.red);
        clearTutor.setForeground(Color.white);
        clearTutor.setFont(new Font("Montserrat", Font.BOLD, 15));
        clearTutor.addActionListener(this);
        tutorSectionPanelLeft.add(clearTutor, gbc);

        // Add message box to the right panel
        JLabel labelTopicTutor = new JLabel("Tutor Section: 😍");
        labelTopicTutor.setForeground(new Color(73, 84, 100));
        labelTopicTutor.setFont(new Font("Montserrat", Font.BOLD, 30));
        labelTopicTutor.setHorizontalAlignment(SwingConstants.CENTER);
        labelTopicTutor.setBorder(BorderFactory.createEmptyBorder(105, 0, 0, 0));
        tutorSectionPanelRight.add(labelTopicTutor, BorderLayout.NORTH);
        tutorSectionPanelRight.add(createMessageBoxForTutor(), BorderLayout.SOUTH);

        // Add left and right panels to the main tutorSectionPanel
        tutorSectionPanel.add(tutorSectionPanelLeft, BorderLayout.WEST);
        tutorSectionPanel.add(tutorSectionPanelRight, BorderLayout.EAST);

        return tutorSectionPanel; // Return the panel
    }





    public JPanel createGradeAssignmentsSection() {
        JPanel gradeAssignmentsSectionPanel = new JPanel(); // Create a new JPanel for Grade Assignments section
        gradeAssignmentsSectionPanel.setBorder(BorderFactory.createTitledBorder("Grade Assignments Section"));
        gradeAssignmentsSectionPanel.setLayout(new BorderLayout()); // Set BorderLayout
        gradeAssignmentsSectionPanel.setBackground(Color.WHITE); // Set background color

        // Left panel for labels and text fields
        JPanel gradeAssignmentsSectionPanelLeft = new JPanel();
        gradeAssignmentsSectionPanelLeft.setLayout(new GridBagLayout());
        gradeAssignmentsSectionPanelLeft.setBackground(new Color(244, 244, 242));

        // Right panel for message box
        JPanel gradeAssignmentsSectionPanelRight = new JPanel();
        gradeAssignmentsSectionPanelRight.setBackground(new Color(232, 232, 232));
        gradeAssignmentsSectionPanelRight.setLayout(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Set insets for spacing

        // Add JLabels and JTextFields for each input field to the left panel
        JLabel assignmentTeacherIdLabel = new JLabel("Teacher ID:");
        assignmentTeacherIdLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        assignmentTeacherIdTextField = new JTextField(15); // Set the preferred width
        assignmentTeacherIdTextField.setPreferredSize(new Dimension(200, 25));
        gradeAssignmentsSectionPanelLeft.add(assignmentTeacherIdLabel, gbc);

        gbc.gridx = 1;
        gradeAssignmentsSectionPanelLeft.add(assignmentTeacherIdTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel assignmentGradedScoreLabel = new JLabel("Graded Score:");
        assignmentGradedScoreLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        gradeAssignmentsSectionPanelLeft.add(assignmentGradedScoreLabel, gbc);

        gbc.gridx = 1;
        assignmentGradedScoreTextField = new JTextField(15); // Set the preferred width
        assignmentGradedScoreTextField.setPreferredSize(new Dimension(200, 25));
        gradeAssignmentsSectionPanelLeft.add(assignmentGradedScoreTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel assignmentYearsOfExperienceLabel = new JLabel("Years of Experience:");
        assignmentYearsOfExperienceLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        gradeAssignmentsSectionPanelLeft.add(assignmentYearsOfExperienceLabel, gbc);

        gbc.gridx = 1;
        assignmentYearsOfExperienceTextField = new JTextField(15); // Set the preferred width
        assignmentYearsOfExperienceTextField.setPreferredSize(new Dimension(200, 25));
        gradeAssignmentsSectionPanelLeft.add(assignmentYearsOfExperienceTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel assignmentDepartmentLabel = new JLabel("Department:");
        assignmentDepartmentLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        gradeAssignmentsSectionPanelLeft.add(assignmentDepartmentLabel, gbc);

        gbc.gridx = 1;
        assignmentDepartmentTextField = new JTextField(15); // Set the preferred width
        assignmentDepartmentTextField.setPreferredSize(new Dimension(200, 25));
        gradeAssignmentsSectionPanelLeft.add(assignmentDepartmentTextField, gbc);


        // Add "Grade Assignments" button to the left panel
        gbc.gridx = 0;
        gbc.gridy++;
        gradeAssignment = new JButton("Grade Now");
        gradeAssignment.setBackground(new Color(73, 84, 100));
        gradeAssignment.setForeground(Color.white);
        gradeAssignment.setFont(new Font("Montserrat", Font.BOLD, 15));
        gradeAssignment.addActionListener(this); // Assuming ActionListener is implemented in the class
        gradeAssignmentsSectionPanelLeft.add(gradeAssignment, gbc);

        // Add main Menu Button
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainMenuBtnForGrade = new JButton("Main Menu");
        mainMenuBtnForGrade.setBackground(new Color(249, 148, 23));
        mainMenuBtnForGrade.setForeground(Color.white);
        mainMenuBtnForGrade.setFont(new Font("Montserrat", Font.BOLD, 15));
        mainMenuBtnForGrade.addActionListener(this);
        gradeAssignmentsSectionPanelLeft.add(mainMenuBtnForGrade, gbc);

        // Add Clear Button to the left panel
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        clearAssignment = new JButton("Clear");
        clearAssignment.setBackground(Color.red);
        clearAssignment.setForeground(Color.white);
        clearAssignment.setFont(new Font("Montserrat", Font.BOLD, 15));
        clearAssignment.addActionListener(this);
        gradeAssignmentsSectionPanelLeft.add(clearAssignment, gbc);

        // Add message box to the right panel
        JLabel labelTopicAssignment = new JLabel("Assignments Section: 😎");
        labelTopicAssignment.setForeground(new Color(73, 84, 100));
        labelTopicAssignment.setFont(new Font("Montserrat", Font.BOLD, 30));
        labelTopicAssignment.setHorizontalAlignment(SwingConstants.CENTER);
        labelTopicAssignment.setBorder(BorderFactory.createEmptyBorder(105, 0, 0, 0));
        gradeAssignmentsSectionPanelRight.add(labelTopicAssignment, BorderLayout.NORTH);
        gradeAssignmentsSectionPanelRight.add(createMessageBoxForAssignment(), BorderLayout.SOUTH);

        // Add left and right panels to the main gradeAssignmentsSectionPanel
        gradeAssignmentsSectionPanel.add(gradeAssignmentsSectionPanelLeft, BorderLayout.CENTER);
        gradeAssignmentsSectionPanel.add(gradeAssignmentsSectionPanelRight, BorderLayout.EAST);

        return gradeAssignmentsSectionPanel; // Return the panel
    }



    public JPanel createSetSalaryOfTutorSection() {
        JPanel setSalaryOfTutorSectionPanel = new JPanel(); // Create a new JPanel for Set Salary of Tutor section
        setSalaryOfTutorSectionPanel.setLayout(new BorderLayout()); // Set BorderLayout
        setSalaryOfTutorSectionPanel.setBorder(BorderFactory.createTitledBorder("Set Salary Section"));
        setSalaryOfTutorSectionPanel.setBackground(Color.white); // Set background color

        // Left panel for input fields, buttons, and remove tutor section
        JPanel setSalaryOfTutorSectionPanelLeft = new JPanel();
        setSalaryOfTutorSectionPanelLeft.setPreferredSize(new Dimension(500,700));
        setSalaryOfTutorSectionPanelLeft.setLayout(new GridBagLayout());
        setSalaryOfTutorSectionPanelLeft.setBackground(new Color(232, 232, 232));


        GridBagConstraints gbc = new GridBagConstraints();

        // Set GridBagConstraints for topicLabelRemove
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel topicLabelRemove = new JLabel("Set Salary Section: ");
        topicLabelRemove.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
        topicLabelRemove.setForeground(new Color(73, 84, 100));
        topicLabelRemove.setFont(new Font("Montserrat", Font.BOLD, 20));
        setSalaryOfTutorSectionPanelLeft.add(topicLabelRemove, gbc);

        // Reset GridBagConstraints for the rest of the components
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1; // Start from the next row
        gbc.insets = new Insets(5, 5, 5, 5); // Set insets for spacing

        // Add JLabels and JTextFields for each input field to the left panel
        JLabel salaryTutorIdLabel = new JLabel("Teacher ID:");
        salaryTutorIdLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        salaryTutorIdTextField = new JTextField(15); // Set the preferred width
        salaryTutorIdTextField.setPreferredSize(new Dimension(200, 25));
        setSalaryOfTutorSectionPanelLeft.add(salaryTutorIdLabel, gbc);

        gbc.gridx = 1;
        setSalaryOfTutorSectionPanelLeft.add(salaryTutorIdTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel salaryTutorNewPerformanceIndexLabel = new JLabel("New Performance Index:");
        salaryTutorNewPerformanceIndexLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        setSalaryOfTutorSectionPanelLeft.add(salaryTutorNewPerformanceIndexLabel, gbc);

        gbc.gridx = 1;
        salaryTutorNewPerformanceIndexTextField = new JTextField(15); // Set the preferred width
        salaryTutorNewPerformanceIndexTextField.setPreferredSize(new Dimension(200, 25));
        setSalaryOfTutorSectionPanelLeft.add(salaryTutorNewPerformanceIndexTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel salaryTutorNewSalaryLabel = new JLabel("New Salary:");
        salaryTutorNewSalaryLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        setSalaryOfTutorSectionPanelLeft.add(salaryTutorNewSalaryLabel, gbc);

        gbc.gridx = 1;
        salaryTutorNewSalaryTextField = new JTextField(15); // Set the preferred width
        salaryTutorNewSalaryTextField.setPreferredSize(new Dimension(200, 25));
        setSalaryOfTutorSectionPanelLeft.add(salaryTutorNewSalaryTextField, gbc);




        // Add "Set Salary of Tutor" button to the left panel
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span two columns
        setSalaryOfTutor = new JButton("Set Salary of Tutor");
        setSalaryOfTutor.setBackground(new Color(73, 84, 100));
        setSalaryOfTutor.setForeground(Color.white);
        setSalaryOfTutor.setFont(new Font("Montserrat", Font.BOLD, 15));
        setSalaryOfTutor.addActionListener(this); // Assuming ActionListener is implemented in the class
        setSalaryOfTutorSectionPanelLeft.add(setSalaryOfTutor, gbc); // Add the button to the left panel

        // Add main Menu Button
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainMenuBtnForSalay = new JButton("Main Menu");
        mainMenuBtnForSalay.setBackground(new Color(249, 148, 23));
        mainMenuBtnForSalay.setForeground(Color.white);
        mainMenuBtnForSalay.setFont(new Font("Montserrat", Font.BOLD, 15));
        mainMenuBtnForSalay.addActionListener(this);
        setSalaryOfTutorSectionPanelLeft.add(mainMenuBtnForSalay, gbc);

        // Add Clear Button to the left panel
        gbc.gridx = 0;
        gbc.gridy ++;
        gbc.gridwidth = 2;
        JLabel spaceLabel = new JLabel("__________________________________________");
        spaceLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        spaceLabel.setBackground(Color.red);
        spaceLabel.setForeground(Color.white);
        spaceLabel.setFont(new Font("Montserrat", Font.BOLD, 15));
        setSalaryOfTutorSectionPanelLeft.add(spaceLabel, gbc);

        // Add Clear Button to the left panel
        gbc.gridx = 0;
        gbc.gridy ++;
        gbc.gridwidth = 2;
        JLabel topicLabelSetSalary = new JLabel("Remove Tutor Section: ");
        topicLabelSetSalary.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        topicLabelSetSalary.setForeground(new Color(73, 84, 100));
        topicLabelSetSalary.setFont(new Font("Montserrat", Font.BOLD, 20));
        setSalaryOfTutorSectionPanelLeft.add(topicLabelSetSalary, gbc);

        // Add Clear Button to the left panel
        gbc.gridx = 0;
        gbc.gridy ++;
        gbc.gridwidth = 2;
        clearSetSalaryOfTutor = new JButton("Clear");
        clearSetSalaryOfTutor.setBackground(Color.red);
        clearSetSalaryOfTutor.setForeground(Color.white);
        clearSetSalaryOfTutor.setFont(new Font("Montserrat", Font.BOLD, 15));
        clearSetSalaryOfTutor.addActionListener(this);
        setSalaryOfTutorSectionPanelLeft.add(clearSetSalaryOfTutor, gbc);


        // Add "Remove Tutor" panel to the left panel
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span two columns
        JPanel removeTutorPanel = new JPanel(); // Create a new JPanel for Remove Tutor section
        removeTutorPanel.setLayout(new GridBagLayout()); // Set GridBagLayout for remove tutor panel
        Border innerBorder = BorderFactory.createTitledBorder("Remove Tutor");
        Border outerBorder = BorderFactory.createEmptyBorder(20, 10, 20, 10);
        removeTutorPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        removeTutorPanel.setBackground(Color.WHITE); // Set background color


        GridBagConstraints gbcRemoveTutor = new GridBagConstraints();
        gbcRemoveTutor.anchor = GridBagConstraints.WEST;
        gbcRemoveTutor.gridx = 0;
        gbcRemoveTutor.gridy = 0;
        gbcRemoveTutor.insets = new Insets(5, 5, 5, 5); // Set insets for spacing

        // Add JLabels and JTextFields for each input field in remove tutor panel
        JLabel removeTutorIdLabel = new JLabel("Teacher ID to Remove:");
        removeTutorIdLabel.setFont(new Font("Montserrat", Font.BOLD, 17));
        removeTutorIdTextField  = new JTextField(15); // Set the preferred width
        removeTutorIdTextField.setPreferredSize(new Dimension(200, 25));
        removeTutorPanel.add(removeTutorIdLabel, gbcRemoveTutor);
        gbcRemoveTutor.gridx++;
        removeTutorPanel.add(removeTutorIdTextField, gbcRemoveTutor);

        // Add "Remove Tutor" button in remove tutor panel
        gbcRemoveTutor.gridx = 0;
        gbcRemoveTutor.gridy++;
        gbcRemoveTutor.gridwidth = 2; // Span two columns
        removeTutor = new JButton("Remove Tutor");
        removeTutor.setBackground(new Color(73, 84, 100));
        removeTutor.setForeground(Color.white);
        removeTutor.setFont(new Font("Montserrat", Font.BOLD, 15));
        removeTutor.addActionListener(this);
        removeTutorPanel.add(removeTutor, gbcRemoveTutor); // Add the button to the panel

        setSalaryOfTutorSectionPanelLeft.add(removeTutorPanel, gbc); // Add the remove tutor panel to the left panel



        // Add "Display" button to the left panel
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        displayButton = new JButton("      Display      ");
        displayButton.setBackground(new Color(251, 109, 72));
        displayButton.setForeground(Color.white);
        displayButton.setFont(new Font("Montserrat", Font.BOLD, 15));
        displayButton.addActionListener(this);
        setSalaryOfTutorSectionPanelLeft.add(displayButton, gbc);


        // Right panel for topic and message box
        JPanel setSalaryOfTutorSectionPanelRight = new JPanel();
        setSalaryOfTutorSectionPanelRight.setBackground(new Color(244, 244, 242));
        setSalaryOfTutorSectionPanelRight.setLayout(new BorderLayout());

        // Add topic label to the right panel
        JLabel labelTopicSalary = new JLabel("Salary & Remove Section: 🤑");
        labelTopicSalary.setForeground(new Color(73, 84, 100));
        labelTopicSalary.setFont(new Font("Montserrat", Font.BOLD, 30));
        labelTopicSalary.setHorizontalAlignment(SwingConstants.CENTER);
        labelTopicSalary.setBorder(BorderFactory.createEmptyBorder(95, 0, 0, 0));
        setSalaryOfTutorSectionPanelRight.add(labelTopicSalary, BorderLayout.NORTH);

        // Add message box to the right panel
        setSalaryOfTutorSectionPanelRight.add(createMessageBoxForSetSalary(), BorderLayout.SOUTH); // Assuming createMessageBox method returns a component

        // Add left and right panels to the main setSalaryOfTutorSectionPanel
        setSalaryOfTutorSectionPanel.add(setSalaryOfTutorSectionPanelLeft, BorderLayout.WEST);
        setSalaryOfTutorSectionPanel.add(setSalaryOfTutorSectionPanelRight, BorderLayout.CENTER);

        return setSalaryOfTutorSectionPanel; // Return the panel
    }


    public JPanel createDisplaySection() {
        JPanel mainDisplaySectionPanel = new JPanel(new BorderLayout());
        mainDisplaySectionPanel.setBackground(new Color(249, 148, 23));
        mainDisplaySectionPanel.setBorder(BorderFactory.createTitledBorder("Display Section"));

        JPanel displaySectionPanel = new JPanel(new GridLayout(2, 1));
        displaySectionPanel.setBackground(Color.WHITE);

        JPanel displayLecturerPanel = new JPanel(new BorderLayout());
        displayLecturerPanel.setBackground(Color.WHITE);
        displayLecturerPanel.setBorder(BorderFactory.createTitledBorder("Lecturers"));
        displayLecturerPanel.setBackground(Color.GREEN);

        JPanel displayTutorPanel = new JPanel(new BorderLayout());
        displayTutorPanel.setBackground(Color.WHITE);
        displayTutorPanel.setBorder(BorderFactory.createTitledBorder("Tutors"));
        displayTutorPanel.setBackground(Color.cyan);

        String[] lecturerColumnNames = {"Teacher ID", "Name", "Address", "Working Type", "Employment Status", "Department", "Years of Experience", "Graded Score"};
        Object[][] lecturerData = {
                {"Demo", "_", "_", "_", "_", "_", "_", "_"},
        };
        lecturerTable = new JTable(lecturerData, lecturerColumnNames);
        customizeTable(lecturerTable);

        JScrollPane lecturerScrollPane = new JScrollPane(lecturerTable);
        displayLecturerPanel.add(lecturerScrollPane, BorderLayout.CENTER);

        String[] tutorColumnNames = {"Teacher ID", "Name", "Address", "Working Type", "Employment Status", "Working Hours", "Salary", "Specialization", "Academic Qualifications", "Performance Index"};
        Object[][] tutorData = {
                {"Demo", "_", "_", "_", "_", "_", "_", "_", "_", "_"},
        };
        tutorTable = new JTable(tutorData, tutorColumnNames);
        customizeTable(tutorTable);

        JScrollPane tutorScrollPane = new JScrollPane(tutorTable);
        displayTutorPanel.add(tutorScrollPane, BorderLayout.CENTER);

        displayBtnForDisplaying = new JButton("Display");
        applyButtonStyle(displayBtnForDisplaying);
        displayBtnForDisplaying.addActionListener(this);
        displaySectionPanel.add(displayLecturerPanel);
        displaySectionPanel.add(displayTutorPanel);
        mainDisplaySectionPanel.add(displayBtnForDisplaying, BorderLayout.PAGE_END);

        mainDisplaySectionPanel.add(displaySectionPanel);
        return mainDisplaySectionPanel;
    }

    private void customizeTable(JTable table) {
        // Set font for the table
        table.setFont(new Font("Montserrat", Font.PLAIN, 13));

        // Set font for the table header
        table.getTableHeader().setFont(new Font("Montserrat", Font.BOLD, 13));
        table.getTableHeader().setBackground(Color.gray);
        table.getTableHeader().setForeground(Color.white);

        // Set row height
        table.setRowHeight(25);

        // Set foreground and background color for the table
        table.setForeground(Color.BLACK); // Set text color
        table.setBackground(Color.WHITE); // Set background color

        // Set grid color
        table.setGridColor(Color.LIGHT_GRAY);

        // Set selection background and foreground color
        table.setSelectionBackground(Color.orange);
        table.setSelectionForeground(Color.WHITE);

        // Set cell alignment (e.g., center alignment for all cells)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
    }




    public JScrollPane createMessageBoxStyle(JTextArea textArea) {
        textArea.setBackground(new Color(73, 84, 100));
        textArea.setForeground(new Color(245, 240, 255));
        textArea.setText("We Show Message Here..... !");
        textArea.setEditable(false);
        textArea.setFont(new Font("Noto Sans", Font.BOLD, 17));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        return new JScrollPane(textArea);
    }

    public JScrollPane createMessageBoxForLecturer() {
        messageForLecturer = new JTextArea(15, 32);
        messageForLecturer.setBorder(new EmptyBorder(7, 10, 3, 2));
        return createMessageBoxStyle(messageForLecturer);
    }

    public JScrollPane createMessageBoxForTutor() {
        messageForTutor = new JTextArea(15, 31);
        messageForTutor.setBorder(new EmptyBorder(7, 10, 3, 2));
        return createMessageBoxStyle(messageForTutor);
    }

    public JScrollPane createMessageBoxForAssignment() {
        messageForAssignment = new JTextArea(15, 33);
        messageForAssignment.setBorder(new EmptyBorder(7, 10, 3, 2));
        return createMessageBoxStyle(messageForAssignment);
    }

    public JScrollPane createMessageBoxForSetSalary() {
        messageForSetSalary = new JTextArea(16, 37);
        messageForSetSalary.setBorder(new EmptyBorder(7, 10, 3, 2));
        return createMessageBoxStyle(messageForSetSalary);
    }


    /*____________________________________________________________________________________________________________________________________________________________*/
    // CODING PARTS HERE GOES :-
    public void clearLecturerFields() {
        messageForLecturer.setText("Cleared successfully!");
        lecturerIdTextField.setText("");
        lecturerNameTextField.setText("");
        lecturerAddressTextField.setText("");
        lecturerWorkingTypeTextField.setText("");
        lecturerEmploymentStatusTextField.setText("");
        lecturerDepartmentTextField.setText("");
        lecturerGradedScoreTextField.setText("");
        lecturerYearsOfExperienceTextField.setText("");
    }

    public void clearTutorFields() {
        messageForTutor.setText("Cleared successfully!");
        tutorIdTextField.setText("");
        tutorNameTextField.setText("");
        tutorAddressTextField.setText("");
        tutorWorkingTypeTextField.setText("");
        tutorEmploymentStatusTextField.setText("");
        tutorWorkingHoursTextField.setText("");
        tutorSalaryTextField.setText("");
        tutorSpecializationTextField.setText("");
        tutorAcademicQualificationsTextField.setText("");
        tutorPerformanceIndexTextField.setText("");
    }

    public void clearAssignmentFields() {
        messageForAssignment.setText("Cleared successfully!");
        assignmentTeacherIdTextField.setText("");
        assignmentGradedScoreTextField.setText("");
        assignmentDepartmentTextField.setText("");
        assignmentYearsOfExperienceTextField.setText("");
    }

    public void clearSetSalaryFields() {
        messageForSetSalary.setText("Cleared successfully!");
        salaryTutorIdTextField.setText("");
        salaryTutorNewSalaryTextField.setText("");
        salaryTutorNewPerformanceIndexTextField.setText("");
        removeTutorIdTextField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addLecturer) {
            lecturerSectionFunctionality();

        } else if (e.getSource() == addTutor) {
            addTutor();

        } else if (e.getSource() == gradeAssignment) {
            gradeAssignment();

        } else if (e.getSource() == setSalaryOfTutor) {
            setSalaryOfTutor();

        } else if (e.getSource() == removeTutor) {
            removeTutor();

        } else if (e.getSource() == clearLecturer) {
            clearLecturerFields();

        } else if (e.getSource() == clearTutor) {
            clearTutorFields();

        } else if (e.getSource() == clearAssignment) {
            clearAssignmentFields();

        } else if (e.getSource() == clearSetSalaryOfTutor) {
            clearSetSalaryFields();

        } else if (e.getSource() == displayButton) {
            displayInfo();

        }else if (e.getSource() == preButton) {
            cardLayout.previous(mainPanel);
        } else if (e.getSource() == menuAddLecturerButton) {
            cardLayout.show(mainPanel, "addLecturer");
        }else if (e.getSource() == menuAddTutorButton) {
            cardLayout.show(mainPanel, "addTutor");
        }else if (e.getSource() == menuGradeAssignmentButton) {
            cardLayout.show(mainPanel, "gradeAssignments");
        }else if (e.getSource() == menuSetSalaryButton) {
            cardLayout.show(mainPanel, "setSalaryOfTutor");
        }else if (e.getSource() == nowRemoveTutor) {
            cardLayout.show(mainPanel, "setSalaryOfTutor");
        } else if (e.getSource() == mainMenuBtnForLecturer || e.getSource() == mainMenuBtnForTutor || e.getSource() == mainMenuBtnForGrade || e.getSource() == mainMenuBtnForSalay || e.getSource() == nextButton) {
            cardLayout.show(mainPanel, "main menu");
        }else if (e.getSource() == displayBtnForDisplaying) {
            displayInfoTable();
        }else if (e.getSource() == nowDisplay) {
            cardLayout.show(mainPanel, "displaySection");
        }
    }

    /*###############################################################################################################*/
    // Functional Coding For Each Section and For Each Class

    // Main functionality function
    public void lecturerSectionFunctionality() {
        // Call the addNewLecturer function
        addNewLecturer();
    }


    public void addTutor(){
        // Pass
//        messageForTutor.setText("Tutor added successfully!");
        addNewTutor();
    }

    public  void gradeAssignment(){
        // Pass
//        messageForAssignment.setText("Graded assignment successfully!");
        newGradeAssignment();
    }

    public void setSalaryOfTutor(){
        // Pass
//        messageForSetSalary.setText("Salary updated  successfully!");
        newSetSalaryOfTutor();
    }

    public void removeTutor(){
        // pass
//        messageForSetSalary.setText("Tutor remove successfully!");
        newRemoveTutor();
    }

    public void displayInfo() {

//        messageForSetSalary.setText("Information Displayed Successfully");
        lastlyDisplayInfo();
    }


    // Function to add a new lecturer
    public void addNewLecturer() {
        String name = lecturerNameTextField.getText().trim();
        String address = lecturerAddressTextField.getText().trim();
        String workingType = lecturerWorkingTypeTextField.getText().trim();
        String employmentStatus = lecturerEmploymentStatusTextField.getText().trim();
        String department = lecturerDepartmentTextField.getText().trim();
        int id;
        int gradedScore;
        int yearsOfExperience;
        if (!validateAddLecturer(name, address, workingType, employmentStatus, department)) {
            return; // Stop execution if validation fails
        }
        try {
            // Getting values from text fields
            id = Integer.parseInt(lecturerIdTextField.getText().trim());
            gradedScore = (int) Double.parseDouble(lecturerGradedScoreTextField.getText().trim());
            yearsOfExperience = Integer.parseInt(lecturerYearsOfExperienceTextField.getText().trim());

            // Perform validation
            if (!validateAddLecturer(name, address, workingType, employmentStatus, department)) {
                return; // Stop execution if validation fails
            }


            for (Teacher teacher : addedTeachers) {
                if (teacher.getTeacherId() == id) {
                    // Display message for duplicate ID
                    messageForLecturer.setText("Teacher with ID " + id + " already registered.");
                    return;
                }
            }

            // Creating a new Lecturer object
            Lecturer newLecturer = new Lecturer(id, name, address, workingType, employmentStatus, department, yearsOfExperience);
            newLecturer.setGradedScore(gradedScore);
            addedTeachers.add(newLecturer);

            String messageForLecturers = "\n\tLecturer " + name + " Info: \n" +
                    "Name: " + name + "\n" +
                    "Address: " + address + "\n" +
                    "Working Type: " + workingType + "\n" +
                    "Employment Status: " + employmentStatus + "\n" +
                    "Department: " + department + "\n" +
                    "ID: " + id + "\n" +
                    "Graded Score: " + gradedScore + "\n" +
                    "Years of Experience: " + yearsOfExperience;


            // Display success message
            messageForLecturer.setText("Lecturer added successfully!: "+ id+"\n");
            messageForLecturer.append(messageForLecturers);
        } catch (NumberFormatException ex) {
            // Handle the case where a non-integer value is entered for the ID
            messageForLecturer.setText("Error: Please enter valid values for ID, Graded Score, and Years of Experience.");
        }
    }

    // Validation method for addLecturer
    public boolean validateAddLecturer(String name, String address, String workingType, String employmentStatus, String department) {
        if (name.isEmpty() || address.isEmpty() || workingType.isEmpty() || employmentStatus.isEmpty() || department.isEmpty()) {
            // Display error message if any of the required fields are empty
            messageForLecturer.setText("Error: Please fill in all required fields.");
            return false;
        }

        // Check if any string fields contain numeric values
        if (containsNumeric(name) || containsNumeric(address) || containsNumeric(workingType) || containsNumeric(employmentStatus) || containsNumeric(department)) {
            messageForLecturer.setText("Error: String fields should not contain numeric values.");
            return false;
        }

        return true;
    }


    private boolean containsNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }


    // Function to add a new tutor
    public void addNewTutor() {
        String name = tutorNameTextField.getText().trim();
        String address = tutorAddressTextField.getText().trim();
        String workingType = tutorWorkingTypeTextField.getText().trim();
        String employmentStatus = tutorEmploymentStatusTextField.getText().trim();
        String specialization = tutorSpecializationTextField.getText().trim();
        String academicQualifications = tutorAcademicQualificationsTextField.getText().trim();
        if (name.isEmpty() || address.isEmpty() || workingType.isEmpty() || employmentStatus.isEmpty() || specialization.isEmpty() || academicQualifications.isEmpty()) {
            // Display error message if any of the required fields are empty
            messageForTutor.setText("Error: Please fill in all required fields.");
            return;
        }else{
        try {
            // Getting values from text fields
            int id = Integer.parseInt(tutorIdTextField.getText().trim());
            int workingHours = Integer.parseInt(tutorWorkingHoursTextField.getText().trim());
            double salary = Double.parseDouble(tutorSalaryTextField.getText());
            int performanceIndex = Integer.parseInt(tutorPerformanceIndexTextField.getText().trim());

            // Perform validation
            if (!validateAddTutor(name, address, workingType, employmentStatus, workingHours, specialization, academicQualifications)) {
                return; // Stop execution if validation fails
            }

            for (Teacher teacher : addedTeachers) {
                if (teacher.getTeacherId() == id) {
                    // Display message for duplicate ID
                    messageForTutor.setText("Tutor with ID " + id + " already registered.");
                    return;
                }
            }

            // Creating a new Tutor object
            Tutor newTutor = new Tutor(id, name, address, workingType, employmentStatus, workingHours, salary, specialization, academicQualifications, performanceIndex);
            addedTeachers.add(newTutor);

            String messageForTutors = "\n\tTutor " + name + " Info: \n" +
                    "Name: " + name + "\n" +
                    "Address: " + address + "\n" +
                    "Working Type: " + workingType + "\n" +
                    "Employment Status: " + employmentStatus + "\n" +
                    "Specialization: " + specialization + "\n" +
                    "Academic Qualifications: " + academicQualifications + "\n" +
                    "ID: " + id + "\n" +
                    "Working Hours: " + workingHours + "\n" +
                    "Salary: " + salary + "\n" +
                    "Performance Index: " + performanceIndex;

            // Display success message
            messageForTutor.setText("Tutor added successfully!: "+id);
            messageForTutor.append(messageForTutors);
        } catch (NumberFormatException ex) {
            // Handle the case where a non-integer value is entered for ID, working hours, or performance index
            messageForTutor.setText("Error: Please enter valid values for ID, Working Hours, Salary, and Performance Index.");
            }
        }
    }

    // Validation method for addTutor
    public boolean validateAddTutor(String name, String address, String workingType, String employmentStatus, int workingHours, String specialization, String academicQualifications) {
        if (name.isEmpty() || address.isEmpty() || workingType.isEmpty() || employmentStatus.isEmpty() || specialization.isEmpty() || academicQualifications.isEmpty()) {
            // Display error message if any of the required fields are empty
            messageForTutor.setText("Error: Please fill in all required fields.");
            return false;
        }

        // Check if any string fields contain numeric values
        if (containsNumeric(name) || containsNumeric(address) || containsNumeric(workingType) || containsNumeric(employmentStatus) || containsNumeric(specialization) || containsNumeric(academicQualifications)) {
            messageForTutor.setText("Error: String fields should not contain numeric values.");
            return false;
        }

        // Check if the Default working hours is less than 20 for further work
        if(workingHours < 20){
            JOptionPane.showMessageDialog(null, "Your Working Hour is less than 20:\n Appraisal will not be applied later.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        return true;
    }


    // Function to grade assignments
    public void newGradeAssignment() {
        try {
            // Getting values from text fields
            int teacherId = Integer.parseInt(assignmentTeacherIdTextField.getText());
            int gradedScore = Integer.parseInt(assignmentGradedScoreTextField.getText());
            String department = assignmentDepartmentTextField.getText();
            int yearsOfExperience = Integer.parseInt(assignmentYearsOfExperienceTextField.getText());

            // Displaying entered information
            String message = "\nTeacher ID: " + teacherId + "\n";
            message += "Graded Score: " + gradedScore + "\n";
            message += "Department: " + department + "\n";
            message += "Years of Experience: " + yearsOfExperience + "\n";

            messageForAssignment.setText(message);
            messageForAssignment.append("\nYour Information is Processing\n");

            // Find the lecturer with the provided ID and grade the assignment
            boolean lecturerFound = false;
            for (Teacher teacher : addedTeachers) {
                if (teacher instanceof Lecturer && teacher.getTeacherId() == teacherId) {
                    Lecturer lecturer = (Lecturer) teacher;
                    if (yearsOfExperience >= 5 && lecturer.getDepartment().equals(department) && (gradedScore >= 0 && gradedScore <= 100)) {
                        lecturer.gradeAssignment(gradedScore, department, yearsOfExperience);
                        lecturer.setGradedScore(gradedScore);
                        messageForAssignment.append("\nGraded Successfully Teacher: "+ teacherId);
                        messageForAssignment.append(message);
                        messageForAssignment.append("\nTeacher with ID: " + teacherId + " has Graded\n");
                    } else {
                        lecturer.gradeAssignment(gradedScore, department, yearsOfExperience);
                        messageForAssignment.append("\nGrading failed. \n\nCheck: \n1. 5+ years exp. required. \n2. Dept. must match lecturer's. \n3. Graded score: 0-100.\n");
                    }
                    lecturerFound = true;
                    break;
                }
            }

            // If no lecturer with the provided ID is found
            if (!lecturerFound) {
                JOptionPane.showMessageDialog(null, "No lecturer found with the provided ID: "+ teacherId, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            // Handle the case where a non-numeric value is entered for ID, graded score, or years of experience
            JOptionPane.showMessageDialog(null, "Grading failed !. \nCheck: \n1. 5+ years exp. required. \n2. Dept. must match lecturer's. \n3. Graded score: 0-100.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    // Function to set the salary of a tutor
    public void newSetSalaryOfTutor() {
        try {
            // Getting values from text fields
            int tutorId = Integer.parseInt(salaryTutorIdTextField.getText());
            double newSalary = Double.parseDouble(salaryTutorNewSalaryTextField.getText());
            int newPerformanceIndex = Integer.parseInt(salaryTutorNewPerformanceIndexTextField.getText());

            // Displaying entered information
            String message = "Tutor ID: " + tutorId + "\n";
            message += "New Salary: " + newSalary + "\n";
            message += "New Performance Index: " + newPerformanceIndex;
            messageForSetSalary.setText(message);
            messageForAssignment.append("\nYour Information is Processing\n");

            boolean tutorFound = false;
            // Find the tutor with the provided ID and set the salary
            for (Teacher teacher : addedTeachers) {
                if (teacher instanceof Tutor && teacher.getTeacherId() == tutorId) {
                    Tutor tutor = (Tutor) teacher;
                    if (newPerformanceIndex >= 3 && tutor.getWorkingHours() > 20) {
                        tutor.setSalary(newSalary, newPerformanceIndex);
                        messageForSetSalary.append("\nSalary Set Successfully Teacher: "+ tutorId);
                        messageForAssignment.append(message);
                        messageForAssignment.append("\nTeacher with ID: " + tutorId + " has Set Salary\n");
                        return;
                    } else if (tutor.getWorkingHours() <= 20) {
                        tutor.setSalary(newSalary, newPerformanceIndex);
                        JOptionPane.showMessageDialog(null, "Teacher: "+tutorId+" has Working Hour less than 20 hrs.", "Reminder", JOptionPane.PLAIN_MESSAGE);
                    }
                    tutorFound = true;
                    break;
                }
            }

            if (!tutorFound) {
                // If no tutor with the provided ID is found
                JOptionPane.showMessageDialog(null, "No tutor found with the provided ID: "+ tutorId, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Salary Set failed. \n\nCheck: \n1. 3+ PerformanceIndex required. \n2. WorkingHours of Tutor must be > 20. \n3. Check Numeric values", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    // Function to remove a tutor
    public void newRemoveTutor() {
        try {
            // Getting value from text field
            int tutorId = Integer.parseInt(removeTutorIdTextField.getText());

            // Find the tutor with the provided ID and remove it
            for (Teacher teacher : addedTeachers) {
                if (teacher instanceof Tutor && teacher.getTeacherId() == tutorId) {
                    Tutor tutor = (Tutor) teacher;

                    // calling method of remove tutor
                    tutor.removeTutor();

                    // removing tutor from the ArrayList
                    addedTeachers.remove(teacher);
                    JOptionPane.showMessageDialog(null, "Tutor with ID " + tutorId + " removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

            // If no tutor with the provided ID is found
            JOptionPane.showMessageDialog(null, "No tutor found with the provided ID.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            // Handle the case where a non-numeric value is entered for ID
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric value for Tutor ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayInfoTable() {
        System.out.println("hello we are displaying");

        // Initialize data arrays with the correct size
        lecturerData = new Object[addedTeachers.size()][8];
        tutorData = new Object[addedTeachers.size()][10];
        String[] tutorColumnNames = {"Teacher ID", "Name", "Address", "Working Type", "Employment Status", "Working Hours", "Salary", "Specialization", "Academic Qualifications", "Performance Index"};
        String[] lecturerColumnNames = {"Teacher ID", "Name", "Address", "Working Type", "Employment Status", "Department", "Years of Experience", "Graded Score"};
        int lecturerIndex = 0;
        int tutorIndex = 0;
        for (Teacher teacher : addedTeachers) {
            if (teacher instanceof Lecturer) {
                Lecturer lecturer = (Lecturer) teacher;
                // Add lecturer data to the lecturerData array
                lecturerData[lecturerIndex] = new Object[]{
                        lecturer.getTeacherId(),
                        lecturer.getTeacherName(),
                        lecturer.getAddress(),
                        lecturer.getWorkingType(),
                        lecturer.getEmploymentStatus(),
                        lecturer.getDepartment(),
                        lecturer.getYearsOfExperience(),
                        lecturer.getGradedScore()
                };
                lecturerIndex++;
            } else if (teacher instanceof Tutor) {
                Tutor tutor = (Tutor) teacher;
                // Add tutor data to the tutorData array
                tutorData[tutorIndex] = new Object[]{
                        tutor.getTeacherId(),
                        tutor.getTeacherName(),
                        tutor.getAddress(),
                        tutor.getWorkingType(),
                        tutor.getEmploymentStatus(),
                        tutor.getWorkingHours(),
                        tutor.getSalary(),
                        tutor.getSpecialization(),
                        tutor.getAcademicQualifications(),
                        tutor.getPerformanceIndex()
                };
                tutorIndex++;
            }
        }

        // Create DefaultTableModel instances for lecturer and tutor tables
        DefaultTableModel lecturerTableModel = new DefaultTableModel(lecturerData, lecturerColumnNames);
        DefaultTableModel tutorTableModel = new DefaultTableModel(tutorData, tutorColumnNames);

        // Set the models for the lecturerTable and tutorTable
        lecturerTable.setModel(lecturerTableModel);
        tutorTable.setModel(tutorTableModel);
    }


    // Function to display information
    public void lastlyDisplayInfo() {
        // Create a StringBuilder to store the information
        StringBuilder info = new StringBuilder();

        // Iterate through the list of added teachers
        for (Teacher teacher : addedTeachers) {
            // Append the information based on the class type
            if (teacher instanceof Lecturer) {
                Lecturer lecturer = (Lecturer) teacher;
                teacher.display();
                info.append("Lecturer Information:\n");
                info.append("Teacher ID: ").append(lecturer.getTeacherId()).append("\n");
                info.append("Teacher Name: ").append(lecturer.getTeacherName()).append("\n");
                info.append("Address: ").append(lecturer.getAddress()).append("\n");
                info.append("Working Type: ").append(lecturer.getWorkingType()).append("\n");
                info.append("Employment Status: ").append(lecturer.getEmploymentStatus()).append("\n");
                info.append("Department: ").append(lecturer.getDepartment()).append("\n");
                info.append("Years of Experience: ").append(lecturer.getYearsOfExperience()).append("\n");
                info.append("Graded Score: ").append(lecturer.getGradedScore()).append("\n");
                info.append("\n");
            } else if (teacher instanceof Tutor) {
                Tutor tutor = (Tutor) teacher;
                teacher.display();
                info.append("Tutor Information:\n");
                info.append("Teacher ID: ").append(tutor.getTeacherId()).append("\n");
                info.append("Teacher Name: ").append(tutor.getTeacherName()).append("\n");
                info.append("Address: ").append(tutor.getAddress()).append("\n");
                info.append("Working Type: ").append(tutor.getWorkingType()).append("\n");
                info.append("Employment Status: ").append(tutor.getEmploymentStatus()).append("\n");
                info.append("Working Hours: ").append(tutor.getWorkingHours()).append("\n");
                info.append("Salary: ").append(tutor.getSalary()).append("\n");
                info.append("Specialization: ").append(tutor.getSpecialization()).append("\n");
                info.append("Academic Qualifications: ").append(tutor.getAcademicQualifications()).append("\n");
                info.append("Performance Index: ").append(tutor.getPerformanceIndex()).append("\n");
                info.append("\n");
            }
        }

        messageForSetSalary.setText(String.valueOf(info));

        // Display the information in a dialog
        JOptionPane.showMessageDialog(null, info.toString(), "Teacher Information", JOptionPane.INFORMATION_MESSAGE);
    }





    public static void main(String[] args) {
        TeacherGui teacherGui = new TeacherGui(); // Create an instance of DebugNew class
    }
}
