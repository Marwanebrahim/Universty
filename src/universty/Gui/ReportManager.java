package universty.Gui;

import javax.swing.*;
import java.awt.*;
import universty.model.Department;

public class ReportManager {
    public static void showSubMenu(JFrame parentFrame, RigesterSystem rigesterSystem) {
        JDialog dialog = new JDialog(parentFrame, "Reports Menu", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(6, 1));

        JButton balanceButton = new JButton("Show Department Balance");
        balanceButton.addActionListener(e -> {
            dialog.dispose();
            showBalanceDialog(parentFrame, rigesterSystem);
        });

        JButton feesButton = new JButton("Show Total Fees in Department");
        feesButton.addActionListener(e -> {
            dialog.dispose();
            showFeesDialog(parentFrame, rigesterSystem);
        });

        JButton salariesButton = new JButton("Show Total Salaries in Department");
        salariesButton.addActionListener(e -> {
            dialog.dispose();
            showSalariesDialog(parentFrame, rigesterSystem);
        });

        JButton totalFeesButton = new JButton("Show Total Fees (University)");
        totalFeesButton.addActionListener(e -> {
            dialog.dispose();
            double totalFees = rigesterSystem.getTotalFees();
            JOptionPane.showMessageDialog(parentFrame, "Total Fees in University: " + totalFees);
        });

        JButton totalSalariesButton = new JButton("Show Total Salaries (University)");
        totalSalariesButton.addActionListener(e -> {
            dialog.dispose();
            double totalSalaries = rigesterSystem.getTotalSalaries();
            JOptionPane.showMessageDialog(parentFrame, "Total Salaries in University: " + totalSalaries);
        });

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> dialog.dispose());

        dialog.add(balanceButton);
        dialog.add(feesButton);
        dialog.add(salariesButton);
        dialog.add(totalFeesButton);
        dialog.add(totalSalariesButton);
        dialog.add(backButton);
        dialog.setVisible(true);
    }

    private static void showBalanceDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:", "Show Balance",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName != null) {
            Department dept = rigesterSystem.searchDepartment(deptName);
            if (dept != null) {
                double balance = rigesterSystem.getBalance(dept);
                JOptionPane.showMessageDialog(parentFrame, "Balance for " + deptName + ": " + balance);
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Department not found.");
            }
        }
    }

    private static void showFeesDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:", "Show Total Fees",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName != null) {
            Department dept = rigesterSystem.searchDepartment(deptName);
            if (dept != null) {
                double fees = rigesterSystem.getTotalFeesInDepartment(dept);
                JOptionPane.showMessageDialog(parentFrame, "Total Fees for " + deptName + ": " + fees);
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Department not found.");
            }
        }
    }

    private static void showSalariesDialog(JFrame parentFrame, RigesterSystem rigesterSystem) {
        String[] depts = DepartmentManager.getDepartmentNames(rigesterSystem);
        if (depts.length == 0)
            return;
        String deptName = (String) JOptionPane.showInputDialog(parentFrame, "Select Department:", "Show Total Salaries",
                JOptionPane.QUESTION_MESSAGE, null, depts, depts[0]);
        if (deptName != null) {
            Department dept = rigesterSystem.searchDepartment(deptName);
            if (dept != null) {
                double salaries = rigesterSystem.getTotalSalariesInDepartment(dept);
                JOptionPane.showMessageDialog(parentFrame, "Total Salaries for " + deptName + ": " + salaries);
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Department not found.");
            }
        }
    }
}
