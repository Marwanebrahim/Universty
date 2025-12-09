
package universty;

import java.util.Scanner;

public class Universty {
    static Scanner input = new Scanner(System.in);
    static RigesterSystem rigesterSystem = new RigesterSystem();

    public static void main(String[] args) {
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
                    ManageDepartments();
                    break;
                case "2":
                    ManageStudents();
                    break;
                case "3":
                    ManageInstructors();
                    break;
                case "4":
                    ManageCourses();
                    break;
                case "5":
                    DepartmentReports();

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

    private static void ManageDepartments() {
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
                    System.out.println("Enter Department Name: ");
                    String departmentName = input.nextLine();
                    System.out.println("Enter Course Fees: ");
                    double courseFees = Double.parseDouble(input.nextLine());
                    Department newDepartment = new Department(departmentName, courseFees);
                    rigesterSystem.addDepartment(newDepartment);
                    System.out.println("Department added successfully.");
                    break;
                case "2":
                    System.out.println("Enter Department Name to remove: ");
                    String deptNameToRemove = input.nextLine();
                    boolean removed = rigesterSystem.removeDepartment(deptNameToRemove);
                    System.out.println(removed ? "Department removed successfully." : "Department not found.");
                    break;
                case "3":
                    Department foundDept = rigesterSystem.isDepartmentFound(input);
                    if (foundDept != null) {
                        System.out.println("Department Found: " + foundDept.getDepartmentName());
                    } else {
                        System.out.println("Department not found.");
                    }
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

    private static void ManageStudents() {
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
                        boolean added = dept.addStudent(newStudent);
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
                        boolean removed = deptToRemove.removeStudent(studentIDToRemove);
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
                    Student student = deptForCourse.searchStudent(studentId);
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
                        Courses courseToAdd = deptForCourse.searchCourse(courseIDToAdd);
                        if (courseToAdd == null) {
                            System.out.println("Course not found in the department.");
                            break;
                        }
                        int regidteredHours = student.addCourse(courseToAdd);
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
                    Student student = deptForCourse.searchStudent(studentId);
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
                        Courses courseToRemove = deptForCourse.searchCourse(courseIDToRemove);
                        if (courseToRemove == null) {
                            System.out.println("Course not found in the department.");
                            break;
                        }
                        int registeredHoursAfterRemove = student.removeCourse(courseToRemove);
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

    private static void ManageCourses() {
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
                    boolean added = deptToAddCourse.addCourse(newCourse);
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

    private static void ManageInstructors() {
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
                    boolean added = dept.addInstructor(newInstructor);
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
                    boolean removed = deptToRemove.removeInstructor(instructorIDToRemove);
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
                    Courses courseToAdd = deptToAddCourse.searchCourse(courseIDToAdd);
                    if (courseToAdd == null) {
                        System.out.println("Course not found in the department.");
                        break;
                    }
                    boolean courseAdded = instructorToAddCourse.addCourse(courseToAdd);
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
                    Instructor instructor = deptToRemoveCourse.searchInstructor(instructorId);
                    if (instructor == null) {
                        System.out.println("Instructor not found.");
                        break;
                    }
                    System.out.println("Enter Course ID to remove from Instructor: ");
                    String courseIDToRemove = input.nextLine();
                    Courses courseToRemove = deptToRemoveCourse.searchCourse(courseIDToRemove);
                    if (courseToRemove == null) {
                        System.out.println("Course not found in the department.");
                        break;
                    }
                    boolean courseRemoved = instructor.removeCourse(courseToRemove);
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

    private static void DepartmentReports() {
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
                        double balance = dept.getBalance();
                        System.out.println("Department Balance for " + dept.getDepartmentName() + ": " + balance);
                    }
                    break;
                case "2":
                    Department deptFees = rigesterSystem.isDepartmentFound(input);
                    if (deptFees != null) {
                        double totalFees = deptFees.getTotalFees();
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
