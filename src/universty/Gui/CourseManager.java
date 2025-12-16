package universty.Gui;

import javax.swing.*;
import java.awt.*;
import universty.model.*;

public class CourseManager {
    public static void showSubMenu(JFrame parentFrame, RigesterSystem rigesterSystem) {
        JDialog dialog = new JDialog(parentFrame, "Course Menu", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(6, 1));

        JButton addButton = new JButton("Add Course");
        addButton.addActionListener(e -> {
            dialog.dispose();
            showAddDialog(parentFrame, rigesterSystem);
        });

        JButton removeButton = new JButton("Remove Course");
        removeButton.addActionListener(e -> {
            dialog.dispose();
            showRemoveDialog(parentFrame, rigesterSystem);
        });

        JButton searchButton = new JButton("Search Course");
        searchButton.addActionListener(e -> {
            dialog.dispose();
            showSearchDialog(parentFrame, rigesterSystem);
        });

        JButton listButton = new JButton("List Courses");
        listButton.addActionListener(e -> {
            dialog.dispose();
            showListDialog(parentFrame, rigesterSystem);
        });

        JButton listStudentsButton = new JButton("List Enrolled Students");
        listStudentsButton.addActionListener(e -> {
            dialog.dispose();
            showListStudentsDialog(parentFrame, rigesterSystem);
        });

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> dialog.dispose());

        dialog.add(addButton);
        dialog.add(removeButton);
        dialog.add(searchButton);
        dialog.add(listButton);
        dialog.add(listStudentsButton);
        dialog.add(backButton);
        dialog.setVisible(true);
    }

    private static void showAddDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:", "Add Course",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName == null)
            return;

        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        JTextField creditsField = new JTextField();
        JTextField instructorIdField = new JTextField();
        Object[] message = { "Course Name:", nameField, "Course ID:", idField, "Credits:", creditsField,
                "Instructor ID:", instructorIdField };
        int option = JOptionPane.showConfirmDialog(parentFrame, message, "Add Course to " + deptName,
                JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            String creditsStr = creditsField.getText().trim();
            String instructorId = instructorIdField.getText().trim();
            if (name.isEmpty() || id.isEmpty() || creditsStr.isEmpty() || instructorId.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Please fill all fields.");
                return;
            }
            try {
                int credits = Integer.parseInt(creditsStr);
                Department dept = rigesterSystem.searchDepartment(deptName);
                Instructor instructor = rigesterSystem.searchInstructorInDepartment(deptName, instructorId);
                if (instructor == null) {
                    JOptionPane.showMessageDialog(parentFrame, "Instructor not found.");
                    return;
                }
                Courses course = new Courses(name, id, dept, credits);
                boolean added = rigesterSystem.addCourseToDepartment(deptName, course);
                JOptionPane.showMessageDialog(parentFrame,
                        added ? "Course added successfully!" : "Course already exists.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parentFrame, "Invalid credits.");
            }
        }
    }

    private static void showRemoveDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:", "Remove Course",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName == null)
            return;

        String id = JOptionPane.showInputDialog(parentFrame, "Enter Course ID to Remove:");
        if (id != null) {
            boolean removed = rigesterSystem.removeCourseFromDepartment(deptName, id);
            JOptionPane.showMessageDialog(parentFrame, removed ? "Course removed successfully!" : "Course not found.");
        }
    }

    private static void showSearchDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:", "Search Course",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName == null)
            return;

        String id = JOptionPane.showInputDialog(parentFrame, "Enter Course ID to Search:");
        if (id != null) {
            Courses course = rigesterSystem.searchCourseInDepartment(rigesterSystem.searchDepartment(deptName), id);
            JOptionPane.showMessageDialog(parentFrame,
                    course != null ? "Found: " + course.toString() : "Course not found.");
        }
    }

    private static void showListDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:", "List Courses",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName != null) {
            String result = rigesterSystem.getCoursesListInDepartment(deptName);
            JOptionPane.showMessageDialog(parentFrame, result);
        }
    }

    private static void showListStudentsDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:",
                "List Enrolled Students",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName == null)
            return;

        String courseId = JOptionPane.showInputDialog(parentFrame, "Enter Course ID:");
        if (courseId != null) {
            Department dept = rigesterSystem.searchDepartment(deptName);
            String result = rigesterSystem.getStudentsInCourse(dept, courseId);
            JOptionPane.showMessageDialog(parentFrame, result);
        }
    }
}