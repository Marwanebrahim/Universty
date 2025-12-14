package universty.system.manager;

import java.util.Scanner;

import universty.model.Courses;
import universty.model.Department;
import universty.model.Instructor;
import universty.system.RigesterSystem;

public class ManageCourses {

    public void manageCourses(RigesterSystem rigesterSystem,Scanner input) {
        while (true) {
            System.out.println("------ Course Menu ------");
            System.out.println("1. Add Course");
            System.out.println("2. Remove Course");
            System.out.println("3. Search Course by Code");
            System.out.println("4. List All Courses in Department");
            System.out.println("5. List All Enrolled Students in a Course");
            System.out.println("6. Back to Main Menu");
            System.out.println("Choose option: ");
            String option = input.nextLine();
            switch (option) {
                case "1":
                    Department deptToAddCourse = rigesterSystem.isDepartmentFound(input);
                    if (deptToAddCourse == null) {
                        System.out.println("Department not found.");
                        break;
                    }
                    System.out.println("Enter Course Name: ");
                    String courseName = input.nextLine();
                    System.out.println("Enter Course ID: ");
                    String courseID = input.nextLine();
                    System.out.println("Enter Course Credits: ");
                    int courseCredits = input.nextInt();
                    Instructor instructor;
                    while (true) {
                        System.out.println("Enter Instructor Name: ");
                        String instructorName = input.nextLine();
                        instructor = rigesterSystem.searchInstructorInDepartment(deptToAddCourse.getDepartmentName(),
                                instructorName);
                        if (instructor == null) {
                            System.out.println("Instructor not found in the department. Please try again.");
                            continue;
                        }
                        break;
                    }
                    Courses newCourse = new Courses(courseName, courseID, deptToAddCourse, courseCredits, instructor);
                    boolean added = rigesterSystem.addCourseToDepartment(deptToAddCourse.getDepartmentName(), newCourse);
                    System.out.println(added ? "Course added successfully." : "Course already exists.");
                    break;
                case "2":
                    Department deptToRemoveCourse = rigesterSystem.isDepartmentFound(input);
                    if (deptToRemoveCourse == null) {
                        System.out.println("Department not found.");
                        break;
                    }
                    System.out.println("Enter Course ID to remove: ");
                    String id = input.nextLine();
                    boolean removed = rigesterSystem.removeCourseFromDepartment(
                            deptToRemoveCourse.getDepartmentName(),
                            id);
                    System.out.println(removed ? "Course removed successfully." : "Course not found.");
                    break;
                case "3":
                    Department deptToSearch = rigesterSystem.isDepartmentFound(input);
                    if (deptToSearch == null) {
                        System.out.println("Department not found.");
                        break;
                    }
                    System.out.println("Enter Course Code to search: ");
                    String courseCodeToSearch = input.nextLine();
                    Courses foundCourse = rigesterSystem.searchCourseInDepartment(
                            deptToSearch.getDepartmentName(),
                            courseCodeToSearch);
                    if (foundCourse != null) {
                        System.out.println("Course Found: " + foundCourse.toString());
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case "4": {
                    Department dept = rigesterSystem.isDepartmentFound(input);
                    if (dept == null) {
                        System.out.println("Department not found.");
                        break;
                    }
                    rigesterSystem.printCoursesInDepartment(dept.getDepartmentName());
                }
                    break;
                case "5": {
                    Department dept = rigesterSystem.isDepartmentFound(input);
                    if (dept == null) {
                        System.out.println("Department not found.");
                        break;
                    }
                    System.out.println("Enter Course ID to list enrolled students: ");
                    String Id = input.nextLine();
                    rigesterSystem.listStudentsInCourse(dept, Id);
                }
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
