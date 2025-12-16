package universty.Gui;

import javax.swing.*;
import java.awt.*;
import universty.model.*;

public class InstructorManager {
    public static void showSubMenu(JFrame parentFrame, RigesterSystem rigesterSystem) {
        JDialog dialog = new JDialog(parentFrame, "Instructor Menu", true);
        dialog.setSize(300, 250);
        dialog.setLayout(new GridLayout(7, 1));

        JButton addButton = new JButton("Add Instructor");
        addButton.addActionListener(e -> {
            dialog.dispose();
            showAddDialog(parentFrame, rigesterSystem);
        });

        JButton removeButton = new JButton("Remove Instructor");
        removeButton.addActionListener(e -> {
            dialog.dispose();
            showRemoveDialog(parentFrame, rigesterSystem);
        });

        JButton searchButton = new JButton("Search Instructor");
        searchButton.addActionListener(e -> {
            dialog.dispose();
            showSearchDialog(parentFrame, rigesterSystem);
        });

        JButton addCourseButton = new JButton("Add Course to Instructor");
        addCourseButton.addActionListener(e -> {
            dialog.dispose();
            showAddCourseDialog(parentFrame, rigesterSystem);
        });

        JButton removeCourseButton = new JButton("Remove Course from Instructor");
        removeCourseButton.addActionListener(e -> {
            dialog.dispose();
            showRemoveCourseDialog(parentFrame, rigesterSystem);
        });

        JButton listButton = new JButton("List Instructors");
        listButton.addActionListener(e -> {
            dialog.dispose();
            showListDialog(parentFrame, rigesterSystem);
        });

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> dialog.dispose());

        dialog.add(addButton);
        dialog.add(removeButton);
        dialog.add(searchButton);
        dialog.add(addCourseButton);
        dialog.add(removeCourseButton);
        dialog.add(listButton);
        dialog.add(backButton);
        dialog.setVisible(true);
    }

    private static void showAddDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:", "Add Instructor",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName == null)
            return;

        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        JTextField salaryField = new JTextField();
        Object[] message = { "Instructor Name:", nameField, "Instructor ID:", idField, "Salary:", salaryField };
        int option = JOptionPane.showConfirmDialog(parentFrame, message, "Add Instructor to " + deptName,
                JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            String salaryStr = salaryField.getText().trim();
            if (name.isEmpty() || id.isEmpty() || salaryStr.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Please fill all fields.");
                return;
            }
            try {
                double salary = Double.parseDouble(salaryStr);
                Department dept = rigesterSystem.searchDepartment(deptName);
                Instructor instructor = new Instructor(name, id, dept, salary);
                boolean added = rigesterSystem.addInstructorToDepartment(deptName, instructor);
                JOptionPane.showMessageDialog(parentFrame,
                        added ? "Instructor added successfully!" : "Instructor already exists.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parentFrame, "Invalid salary.");
            }
        }
    }

    private static void showRemoveDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:", "Remove Instructor",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName == null)
            return;

        String id = JOptionPane.showInputDialog(parentFrame, "Enter Instructor ID to Remove:");
        if (id != null) {
            boolean removed = rigesterSystem.removeInstructorFromDepartment(deptName, id);
            JOptionPane.showMessageDialog(parentFrame,
                    removed ? "Instructor removed successfully!" : "Instructor not found.");
        }
    }

    private static void showSearchDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:", "Search Instructor",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName == null)
            return;

        String id = JOptionPane.showInputDialog(parentFrame, "Enter Instructor ID to Search:");
        if (id != null) {
            Instructor instructor = rigesterSystem.searchInstructorInDepartment(deptName, id);
            JOptionPane.showMessageDialog(parentFrame,
                    instructor != null ? "Found: " + instructor.toString() : "Instructor not found.");
        }
    }

    private static void showAddCourseDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String instructorId = JOptionPane.showInputDialog(parentFrame, "Enter Instructor ID:");
        String courseId = JOptionPane.showInputDialog(parentFrame, "Enter Course ID:");
        if (instructorId != null && courseId != null) {
            Courses course = null; // Placeholder; need to find course
            boolean added = rigesterSystem.addCourseToInstructor(instructorId, course);
            JOptionPane.showMessageDialog(parentFrame, added ? "Course added to instructor!" : "Failed.");
        }
    }

    private static void showRemoveCourseDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String instructorId = JOptionPane.showInputDialog(parentFrame, "Enter Instructor ID:");
        String courseId = JOptionPane.showInputDialog(parentFrame, "Enter Course ID:");
        if (instructorId != null && courseId != null) {
            Courses course = null; // Placeholder
            boolean removed = rigesterSystem.removeCourseFromInstructor(instructorId, course);
            JOptionPane.showMessageDialog(parentFrame, removed ? "Course removed from instructor!" : "Failed.");
        }
    }

    private static void showListDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:", "List Instructors",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName != null) {
            String result = rigesterSystem.getInstructorsListInDepartment(deptName);
            JOptionPane.showMessageDialog(parentFrame, result);
        }
    }
}