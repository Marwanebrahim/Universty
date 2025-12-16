package universty.Gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import universty.model.Department;

public class DepartmentManager {
    public static void showSubMenu(JFrame parentFrame, RigesterSystem rigesterSystem) {
        JDialog dialog = new JDialog(parentFrame, "Department Menu", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(5, 1));

        JButton addButton = new JButton("Add Department");
        addButton.addActionListener(e -> {
            dialog.dispose();
            showAddDialog(parentFrame, rigesterSystem);
        });

        JButton removeButton = new JButton("Remove Department");
        removeButton.addActionListener(e -> {
            dialog.dispose();
            showRemoveDialog(parentFrame, rigesterSystem);
        });

        JButton searchButton = new JButton("Search Department");
        searchButton.addActionListener(e -> {
            dialog.dispose();
            showSearchDialog(parentFrame, rigesterSystem);
        });

        JButton listButton = new JButton("List Departments");
        listButton.addActionListener(e -> {
            dialog.dispose();
            String list = rigesterSystem.getDepartmentsList();
            JOptionPane.showMessageDialog(parentFrame, list);
        });

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> dialog.dispose());

        dialog.add(addButton);
        dialog.add(removeButton);
        dialog.add(searchButton);
        dialog.add(listButton);
        dialog.add(backButton);
        dialog.setVisible(true);
    }

    public static void showAddDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        JTextField nameField = new JTextField();
        JTextField feeField = new JTextField();
        Object[] message = { "Department Name:", nameField, "Course Fee:", feeField };
        int option = JOptionPane.showConfirmDialog(parentFrame, message, "Add Department",
                JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String feeStr = feeField.getText().trim();
            if (name.isEmpty() || feeStr.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Please fill all fields.");
                return;
            }
            try {
                double fee = Double.parseDouble(feeStr);
                Department dept = new Department(name, fee);
                boolean added = rigesterSystem.addDepartment(dept);
                JOptionPane.showMessageDialog(parentFrame,
                        added ? "Department added successfully!" : "Department already exists.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parentFrame, "Invalid fee.");
            }
        }
    }

    public static void showRemoveDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = getDepartmentNames(rigesterSystem);
        if (depts.length == 0) {
            JOptionPane.showMessageDialog(parentFrame, "No departments available.");
            return;
        }
        String selected = (String) JOptionPane.showInputDialog(parentFrame, "Select Department to Remove:",
                "Remove Department",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (selected != null) {
            boolean removed = rigesterSystem.removeDepartment(selected);
            JOptionPane.showMessageDialog(parentFrame,
                    removed ? "Department removed successfully!" : "Department not found.");
        }
    }

    public static void showSearchDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String name = JOptionPane.showInputDialog(parentFrame, "Enter Department Name to Search:");
        if (name != null) {
            Department dept = rigesterSystem.searchDepartment(name);
            JOptionPane.showMessageDialog(parentFrame,
                    dept != null ? "Found: " + dept.getDepartmentName() + ", Fee: " + dept.getCourseFees()
                            : "Department not found.");
        }
    }

    public static String[] getDepartmentNames(RigesterSystem rigesterSystem) {
        List<String> names = new ArrayList<>();
        var temp = rigesterSystem.departments.head;
        while (temp != null) {
            names.add(temp.data.getDepartmentName());
            temp = temp.next;
        }
        return names.toArray(new String[0]);
    }
}