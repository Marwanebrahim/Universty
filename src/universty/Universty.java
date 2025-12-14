
package universty;

import java.util.Scanner;

import universty.system.RigesterSystem;
import universty.system.manager.DepartmentReports;
import universty.system.manager.ManageCourses;
import universty.system.manager.ManageDepartments;
import universty.system.manager.ManageInstructors;
import universty.system.manager.ManageStudents;

public class Universty {
    static Scanner input = new Scanner(System.in);
    static RigesterSystem rigesterSystem = new RigesterSystem();

    public static void main(String[] args) {
        ManageDepartments manageDepartments = new ManageDepartments();
        ManageStudents manageStudents = new ManageStudents();
        ManageCourses manageCourses = new ManageCourses();
        ManageInstructors manageInstructors = new ManageInstructors();
        DepartmentReports departmentReports = new DepartmentReports();
        rigesterSystem.DumbyData();
        boolean first_choice = true;
        while (first_choice) {

            System.out.println("========== University Registration System ==========");
            System.out.println("1. Manage Departments");
            System.out.println("2. Manage students");
            System.out.println("3. Manage Instructors");
            System.out.println("4. Manage Courses");
            System.out.println("5. Department Reports");
            System.out.println("6. Exit");
            System.out.println("Choose option: ");
            String option = input.nextLine();
            switch (option) {
                case "1":
                    manageDepartments.manageDepartments(rigesterSystem, input);
                    break;
                case "2":
                    manageStudents.manageStudents(rigesterSystem, input);
                    break;
                case "3":
                    manageInstructors.manageInstructors(rigesterSystem, input);
                    break;
                case "4":
                    manageCourses.manageCourses(rigesterSystem, input);
                    break;
                case "5":
                    departmentReports.departmentReports(rigesterSystem, input);

                    break;
                case "6":
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
        }
    }

}
