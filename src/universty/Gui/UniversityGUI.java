package universty.Gui;

import javax.swing.*;
import java.awt.*;

public class UniversityGUI {
    private RigesterSystem rigesterSystem;
    private JFrame frame;

    public UniversityGUI() {
        rigesterSystem = new RigesterSystem();
        rigesterSystem.DumbyData(); // Load dummy data

        frame = new JFrame("University Registration System - Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 1));

        // Main Menu Buttons
        JButton deptButton = new JButton("Manage Departments");
        deptButton.addActionListener(e -> DepartmentManager.showSubMenu(frame, rigesterSystem));

        JButton studentButton = new JButton("Manage Students");
        studentButton.addActionListener(e -> StudentManager.showSubMenu(frame, rigesterSystem));

        JButton instructorButton = new JButton("Manage Instructors");
        instructorButton.addActionListener(e -> InstructorManager.showSubMenu(frame, rigesterSystem));

        JButton courseButton = new JButton("Manage Courses");
        courseButton.addActionListener(e -> CourseManager.showSubMenu(frame, rigesterSystem));

        JButton reportButton = new JButton("Reports");
        reportButton.addActionListener(e -> ReportManager.showSubMenu(frame, rigesterSystem));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        frame.add(new JLabel("Main Menu - Choose an Option:", SwingConstants.CENTER));
        frame.add(deptButton);
        frame.add(studentButton);
        frame.add(instructorButton);
        frame.add(courseButton);
        frame.add(reportButton);
        frame.add(exitButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UniversityGUI::new);
    }
}