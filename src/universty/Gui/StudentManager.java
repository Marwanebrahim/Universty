package universty.Gui;

import javax.swing.*;
import java.awt.*;
import universty.model.*;

public class StudentManager {
    public static void showSubMenu(JFrame parentFrame, RigesterSystem rigesterSystem) {
        JDialog dialog = new JDialog(parentFrame, "Student Menu", true);
        dialog.setSize(300, 250);
        dialog.setLayout(new GridLayout(7, 1));

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> {
            dialog.dispose();
            showAddDialog(parentFrame, rigesterSystem);
        });

        JButton removeButton = new JButton("Remove Student");
        removeButton.addActionListener(e -> {
            dialog.dispose();
            showRemoveDialog(parentFrame, rigesterSystem);
        });

        JButton searchButton = new JButton("Search Student");
        searchButton.addActionListener(e -> {
            dialog.dispose();
            showSearchDialog(parentFrame, rigesterSystem);
        });

        JButton addCourseButton = new JButton("Add Course to Student");
        addCourseButton.addActionListener(e -> {
            dialog.dispose();
            showAddCourseDialog(parentFrame, rigesterSystem);
        });

        JButton removeCourseButton = new JButton("Remove Course from Student");
        removeCourseButton.addActionListener(e -> {
            dialog.dispose();
            showRemoveCourseDialog(parentFrame, rigesterSystem);
        });

        JButton listButton = new JButton("List Students");
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

    private static void showListDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:", "List Students",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName != null) {
            String result = rigesterSystem.getStudentsListInDepartment(deptName);
            JOptionPane.showMessageDialog(parentFrame, result);
        }
    }

    private static void showAddDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0) {
            JOptionPane.showMessageDialog(parentFrame, "No departments available.");
            return;
        }
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:", "Add Student",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName == null)
            return;

        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        Object[] message = { "Student Name:", nameField, "Student ID:", idField };
        int option = JOptionPane.showConfirmDialog(parentFrame, message, "Add Student to " + deptName,
                JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            if (name.isEmpty() || id.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Please fill all fields.");
                return;
            }
            Department dept = rigesterSystem.searchDepartment(deptName);
            if (dept == null) {
                JOptionPane.showMessageDialog(parentFrame, "Department not found.");
                return;
            }
            Student student = new Student(name, id, dept);
            boolean added = rigesterSystem.addStudentToDepartment(deptName, student);
            JOptionPane.showMessageDialog(parentFrame,
                    added ? "Student added successfully!" : "Student already exists.");
        }
    }

    private static void showRemoveDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:", "Remove Student",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName == null)
            return;

        String id = JOptionPane.showInputDialog(parentFrame, "Enter Student ID to Remove:");
        if (id != null) {
            boolean removed = rigesterSystem.removeStudentFromDepartment(deptName, id);
            JOptionPane.showMessageDialog(parentFrame,
                    removed ? "Student removed successfully!" : "Student not found.");
        }
    }

    private static void showSearchDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:", "Search Student",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName == null)
            return;

        String id = JOptionPane.showInputDialog(parentFrame, "Enter Student ID to Search:");
        if (id != null) {
            Student student = rigesterSystem.searchStudentInDepartment(deptName, id);
            JOptionPane.showMessageDialog(parentFrame,
                    student != null ? "Found: " + student.toString() : "Student not found.");
        }
    }

    private static void showAddCourseDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:",
                "Add Course to Student",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName == null)
            return;

        String studentId = JOptionPane.showInputDialog(parentFrame, "Enter Student ID:");
        String courseId = JOptionPane.showInputDialog(parentFrame, "Enter Course ID:");
        if (studentId != null && courseId != null) {
            Student student = rigesterSystem.searchStudentInDepartment(deptName, studentId);
            Courses course = rigesterSystem.searchCourseInDepartment(rigesterSystem.searchDepartment(deptName),
                    courseId);
            if (student == null || course == null) {
                JOptionPane.showMessageDialog(parentFrame, "Student or Course not found.");
                return;
            }
            int result = rigesterSystem.addCourseToStudent(rigesterSystem.searchDepartment(deptName), student, course);
            JOptionPane.showMessageDialog(parentFrame,
                    result >= 0 ? "Course added! Hours: " + result : "Failed to add course.");
        }
    }

    private static void showRemoveCourseDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String studentId = JOptionPane.showInputDialog(parentFrame, "Enter Student ID:");
        String courseId = JOptionPane.showInputDialog(parentFrame, "Enter Course ID:");
        if (studentId != null && courseId != null) {
            Courses course = null; // Placeholder
            int result = rigesterSystem.removeCourseFromStudent(studentId, course);
            JOptionPane.showMessageDialog(parentFrame,
                    result >= 0 ? "Course removed! Hours: " + result : "Failed to remove course.");
        }
    }
}