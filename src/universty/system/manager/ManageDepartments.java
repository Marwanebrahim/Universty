package universty.system.manager;

import java.util.Scanner;

import universty.model.Department;
import universty.system.RigesterSystem;

public class ManageDepartments {

    public void manageDepartments(RigesterSystem rigesterSystem, Scanner input) {
        while (true) {
            System.out.println("------ Department Menu ------");
            System.out.println("1. Add Department");
            System.out.println("2. Remove Department");
            System.out.println("3. Search Department");
            System.out.println("4. List All Departments");
            System.out.println("5. Back to Main Menu");
            System.out.println("Choose option: ");
            String option = input.nextLine();
            switch (option) {
                case "1":
                    addDepartment(rigesterSystem, input);
                    break;
                case "2":
                    removeDepartment(rigesterSystem, input);
                    break;
                case "3":
                    searchDepartment(rigesterSystem, input);
                    break;
                case "4":
                    rigesterSystem.printDepartments();
                    break;
                case "5":
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid option.please try again.");

                    continue;
            }
        }
    }

    private void searchDepartment(RigesterSystem rigesterSystem, Scanner input) {
        Department foundDept = rigesterSystem.isDepartmentFound(input);
        if (foundDept != null) {
            System.out.println("Department Found: " + foundDept.getDepartmentName());
        } else {
            System.out.println("Department not found.");
        }
    }

    private void removeDepartment(RigesterSystem rigesterSystem, Scanner input) {
        System.out.println("Enter Department Name to remove: ");
        String deptNameToRemove = input.nextLine();
        boolean removed = rigesterSystem.removeDepartment(deptNameToRemove);
        System.out.println(removed ? "Department removed successfully." : "Department not found.");
    }

    private void addDepartment(RigesterSystem rigesterSystem, Scanner input) {
        System.out.println("Enter Department Name: ");
        String departmentName = input.nextLine();
        System.out.println("Enter Course Fees: ");
        double courseFees = Double.parseDouble(input.nextLine());
        Department newDepartment = new Department(departmentName, courseFees);
        rigesterSystem.addDepartment(newDepartment);
        System.out.println("Department added successfully.");
    }

}
