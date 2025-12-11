package universty.manager;

import java.util.Scanner;

import universty.Courses;
import universty.Department;
import universty.Instructor;
import universty.RigesterSystem;

public class ManageInstructors {

    public void manageInstructors(RigesterSystem rigesterSystem, Scanner input) {
        while (true) {
            System.out.println("------ Instructor Menu ------");
            System.out.println("1. Add Instructor");
            System.out.println("2. Remove Instructor");
            System.out.println("3. Search Instructor by ID");
            System.out.println("4. Add Course to Instructor");
            System.out.println("5. Remove Course from Instructor");
            System.out.println("6. List All Instructors in Department");
            System.out.println("7. Back to Main Menu");
            System.out.println("Choose option: ");
            String option = input.nextLine();
            switch (option) {
                case "1":
                    System.out.println("Enter Department Name for the Instructor: ");
                    Department dept = rigesterSystem.isDepartmentFound(input);
                    if (dept == null) {
                        System.out.println("Department not found.");
                        break;
                    }
                    System.out.println("Enter Instructor Name: ");
                    String instructorName = input.nextLine();
                    System.out.println("Enter Instructor ID: ");
                    String instructorID = input.nextLine();
                    System.out.println("Enter Instructor Salary: ");
                    double instructorSalary = input.nextDouble();
                    Instructor newInstructor = new Instructor(instructorName, instructorID, dept, instructorSalary);
                    boolean added = rigesterSystem.addInstructorToDepartment(dept.getDepartmentName(), newInstructor);
                    System.out.println(
                            added ? "Instructor added successfully to " + dept.getDepartmentName() + " department."
                                    : "Failed to add instructor to " + dept.getDepartmentName() + " department.");
                    break;
                case "2":
                    Department deptToRemove = rigesterSystem.isDepartmentFound(input);
                    if (deptToRemove == null) {
                        System.out.println("Department not found.");
                        break;
                    }
                    System.out.println("Enter Instructor ID to remove: ");
                    String instructorIDToRemove = input.nextLine();
                    boolean removed = rigesterSystem.removeInstructorFromDepartment(deptToRemove.getDepartmentName(),
                            instructorIDToRemove);
                    System.out.println(removed ? "Instructor removed successfully." : "Instructor not found.");
                    break;
                case "3":
                    Department deptToSearch = rigesterSystem.isDepartmentFound(input);
                    if (deptToSearch == null) {
                        System.out.println("Department not found.");
                        break;
                    }
                    System.out.println("Enter Instructor ID to search: ");
                    String instructorIDToSearch = input.nextLine();
                    Instructor foundInstructor = rigesterSystem.searchInstructorInDepartment(
                            deptToSearch.getDepartmentName(), instructorIDToSearch);
                    if (foundInstructor != null) {
                        System.out.println("Instructor Found: " + foundInstructor.toString());
                    } else {
                        System.out.println("Instructor not found.");
                    }
                    break;
                case "4":
                    Department deptToAddCourse = rigesterSystem.isDepartmentFound(input);
                    if (deptToAddCourse == null) {
                        System.out.println("Department not found.");
                        break;
                    }
                    System.out.println("Enter Instructor ID: ");
                    String instructorIdToAddCourse = input.nextLine();
                    Instructor instructorToAddCourse = deptToAddCourse.searchInstructor(instructorIdToAddCourse);
                    if (instructorToAddCourse == null) {
                        System.out.println("Instructor not found.");
                        break;
                    }
                    System.out.println("Enter Course ID to add to Instructor: ");
                    String courseIDToAdd = input.nextLine();
                    Courses courseToAdd = rigesterSystem.searchCourseInDepartment(deptToAddCourse.getDepartmentName(), courseIDToAdd);
                    if (courseToAdd == null) {
                        System.out.println("Course not found in the department.");
                        break;
                    }
                    boolean courseAdded = rigesterSystem.addCourseToInstructor(instructorToAddCourse.getId(), courseToAdd);
                    System.out.println(courseAdded ? "Course added to instructor successfully."
                            : "Course already assigned to instructor.");
                    break;
                case "5":
                    Department deptToRemoveCourse = rigesterSystem.isDepartmentFound(input);
                    if (deptToRemoveCourse == null) {
                        System.out.println("Department not found.");
                        break;
                    }
                    System.out.println("Enter Instructor ID: ");
                    String instructorId = input.nextLine();
                    Instructor instructor = rigesterSystem
                            .searchInstructorInDepartment(deptToRemoveCourse.getDepartmentName(), instructorId);
                    if (instructor == null) {
                        System.out.println("Instructor not found.");
                        break;
                    }
                    System.out.println("Enter Course ID to remove from Instructor: ");
                    String courseIDToRemove = input.nextLine();
                    Courses courseToRemove = rigesterSystem
                            .searchCourseInDepartment(deptToRemoveCourse.getDepartmentName(), courseIDToRemove);
                    if (courseToRemove == null) {
                        System.out.println("Course not found in the department.");
                        break;
                    }
                    boolean courseRemoved = rigesterSystem.removeCourseFromInstructor(instructor.getId(),
                            courseToRemove);
                    System.out.println(courseRemoved ? "Course removed from instructor successfully."
                            : "Course not found in instructor assigned courses.");
                    break;
                case "6": {
                    Department deptToList = rigesterSystem.isDepartmentFound(input);
                    if (deptToList == null) {
                        System.out.println("Department not found.");
                        break;
                    }
                    deptToList.printInstructors();
                    break;
                }
                case "7":
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
        }
    }

}
