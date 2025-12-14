package universty.system.manager;

import java.util.Scanner;

import universty.model.Courses;
import universty.model.Department;
import universty.model.Student;
import universty.system.RigesterSystem;

public class ManageStudents {

    public void manageStudents(RigesterSystem rigesterSystem, Scanner input) {
        while (true) {
            System.out.println("------ Student Menu ------");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Add Course to Student");
            System.out.println("5. Remove Course from Student");
            System.out.println("6. List All Students in Department");
            System.out.println("7. Back to Main Menu");
            System.out.println("Choose option: ");
            String option = input.nextLine();
            switch (option) {
                case "1":
                    Department dept = rigesterSystem.isDepartmentFound(input);
                    if (dept != null) {
                        System.out.println("Enter Student Name: ");
                        String studentName = input.nextLine();
                        System.out.println("Enter Student ID: ");
                        String studentID = input.nextLine();
                        Student newStudent = new Student(studentName, studentID, dept);
                        boolean added = rigesterSystem.addStudentToDepartment(dept.getKey(), newStudent);
                        System.out.println(
                                added ? "Student added successfully to " + dept.getDepartmentName() + " department."
                                        : "Failed to add student to " + dept.getDepartmentName() + " department.");
                    } else {
                        System.out.println("Department not found.");
                    }

                    break;
                case "2":
                    Department deptToRemove = rigesterSystem.isDepartmentFound(input);
                    if (deptToRemove != null) {
                        System.out.println("Enter Student ID to remove: ");
                        String studentIDToRemove = input.nextLine();
                        boolean removed = rigesterSystem.removeStudentFromDepartment(deptToRemove.getKey(),
                                studentIDToRemove);
                        System.out.println(removed ? "Student removed successfully." : "Student not found.");
                    } else {
                        System.out.println("Department not found.");
                    }

                    break;
                case "3":
                    Department deptToSearch = rigesterSystem.isDepartmentFound(input);
                    if (deptToSearch == null) {
                        System.out.println("Department not found.");
                        break;
                    }
                    System.out.println("Enter Student ID to search: ");
                    String studentIDToSearch = input.nextLine();
                    Student foundStudent = rigesterSystem
                            .searchStudentInDepartment(deptToSearch.getDepartmentName(), studentIDToSearch);
                    if (foundStudent != null) {
                        System.out.println(
                                "Student Found: " + foundStudent.toString());
                    } else {
                        System.out.println("Student not found.");
                    }

                    break;
                case "4": {
                    Department deptForCourse = rigesterSystem.isDepartmentFound(input);
                    if (deptForCourse == null) {
                        System.out.println("Department not found.");
                        break;
                    }
                    System.out.println("Enter Student ID: ");
                    String studentId = input.nextLine();
                    Student student = rigesterSystem.searchStudentInDepartment(deptForCourse.getDepartmentName(),
                            studentId);
                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    int result = rigesterSystem.printCoursesInDepartment(deptForCourse.getDepartmentName());
                    if (result != 0) {
                        break;
                    }

                    while (true) {

                        System.out.println("Enter Course ID to add: ");
                        String courseIDToAdd = input.nextLine();
                        Courses courseToAdd = rigesterSystem.searchCourseInDepartment(deptForCourse.getDepartmentName(),
                                courseIDToAdd);
                        if (courseToAdd == null) {
                            System.out.println("Course not found in the department.");
                            break;
                        }
                        if (student.getRegisteredHours() + courseToAdd.getCreditHours() > 17) {
                            System.out.println("Maximum registered hours reached. Cannot add more courses.");
                            break;
                        }
                        int regidteredHours = rigesterSystem.addCourseToStudent(deptForCourse, student, courseToAdd);
                        System.out.println("Course added successfully. Total registered hours: " + regidteredHours);
                        if (regidteredHours == 100) {
                            System.out.println("Failed to add course. try again.");
                            continue;
                        }
                        if (regidteredHours >= 17) {
                            System.out.println("Maximum registered hours reached. Cannot add more courses.");
                            break;
                        }
                        System.out.println("Do you want to add another course? (yes/no): ");
                        String addAnother = input.nextLine();
                        if (!addAnother.equalsIgnoreCase("yes")) {
                            break;
                        }
                    }
                }
                    break;
                case "5": {
                    Department deptForCourse = rigesterSystem.isDepartmentFound(input);
                    if (deptForCourse == null) {
                        System.out.println("Department not found.");
                        break;
                    }
                    System.out.println("Enter Student ID: ");
                    String studentId = input.nextLine();
                    Student student = rigesterSystem.searchStudentInDepartment(deptForCourse.getDepartmentName(),
                            studentId);
                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    int result = rigesterSystem.printCoursesInDepartment(deptForCourse.getDepartmentName());
                    if (result != 0) {
                        System.out.println("No courses available in the department.");
                        break;
                    }
                    while (true) {
                        System.out.println("Enter Course ID to remove: ");
                        String courseIDToRemove = input.next();
                        Courses courseToRemove = rigesterSystem
                                .searchCourseInDepartment(deptForCourse.getDepartmentName(), courseIDToRemove);
                        if (courseToRemove == null) {
                            System.out.println("Course not found in the department.");
                            break;
                        }
                        int registeredHoursAfterRemove = rigesterSystem.removeCourseFromStudent(student.getId(),
                                courseToRemove);
                        if (registeredHoursAfterRemove == 100) {
                            System.out.println("Failed to remove course. try again.");
                            continue;
                        }
                        System.out.println("Course removed successfully. Total registered hours: "
                                + registeredHoursAfterRemove);
                        if (registeredHoursAfterRemove == 0) {
                            break;
                        }
                        System.out.println("Do you want to remove another course? (yes/no): ");
                        String removeAnother = input.next();
                        if (!removeAnother.equalsIgnoreCase("yes")) {
                            break;
                        }
                    }
                }
                    break;
                case "6": {
                    Department deptToList = rigesterSystem.isDepartmentFound(input);
                    if (deptToList != null) {
                        deptToList.printStudents();
                    }
                }
                    break;
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
