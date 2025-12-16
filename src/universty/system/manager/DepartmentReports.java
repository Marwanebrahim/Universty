package universty.system.manager;

import java.util.Scanner;

import universty.model.Department;
import universty.system.RigesterSystem;

public class DepartmentReports {

    public void departmentReports(RigesterSystem rigesterSystem, Scanner input) {
        while (true) {
            System.out.println("------ Department Reports Menu ------");
            System.out.println("1. Show Department Balance");
            System.out.println("2. Show Total Fees Collected in Department");
            System.out.println("3. Show Total Salaries Paid in Department");
            System.out.println("4. Show Total Fees Collected");
            System.out.println("5. Show Total Salaries Paid");
            System.out.println("6. Back to Main Menu");
            System.out.println("Choose option: ");
            String option = input.nextLine();
            switch (option) {
                case "1":
                    Department dept = rigesterSystem.isDepartmentFound(input);
                    if (dept != null) {
                        double balance = rigesterSystem.getBalance(dept);
                        System.out.println("Department Balance for " + dept.getDepartmentName() + ": " + balance);
                    }
                    break;
                case "2":
                    Department deptFees = rigesterSystem.isDepartmentFound(input);
                    if (deptFees != null) {
                        double totalFees = rigesterSystem.getTotalFeesInDepartment(deptFees);
                        System.out
                                .println("Total Fees Collected for " + deptFees.getDepartmentName() + ": " + totalFees);
                    }
                    break;
                case "3":
                    Department deptSalaries = rigesterSystem.isDepartmentFound(input);
                    if (deptSalaries != null) {
                        double totalSalaries = deptSalaries.getTotalSalaries();
                        System.out.println(
                                "Total Salaries Paid for " + deptSalaries.getDepartmentName() + ": " + totalSalaries);
                    }
                    break;
                case "4":
                    double totalFees = rigesterSystem.getTotalFees();
                    System.out.println("Total Fees Collected in University: " + totalFees);
                    break;
                case "5":
                    double totalSalaries = rigesterSystem.getTotalSalaries();
                    System.out.println("Total Salaries Paid in University: " + totalSalaries);
                    break;

                case "6":
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;

            }
        }
    }

}
