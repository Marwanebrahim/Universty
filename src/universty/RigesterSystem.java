package universty;

import java.util.Scanner;

import universty.Structures.Node;
import universty.Structures.SingleLinkedList;

public class RigesterSystem {
    SingleLinkedList<Department> departments;
    double totalBalance;

    public RigesterSystem() {
        departments = new SingleLinkedList<Department>();
        totalBalance = 0;
    }

    public boolean addDepartment(Department department) {
        boolean found = searchDepartment(department.getDepartmentName()) != null;
        if (found) {
            return false;
        }
        return departments.AddNode(department);
    }

    public boolean removeDepartment(String departmentName) {
        Node<Department> temp = departments.head;
        while (temp != null && !temp.data.getKey().equals(departmentName)) {
            temp = temp.next;
        }
        if (temp != null) {
            return departments.DeleteNode(temp.data);
        }
        return false;
    }

    public Department searchDepartment(String departmentName) {
        Node<Department> temp = departments.head;
        while (temp != null) {
            if (temp.data.getKey().equals(departmentName)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public boolean addStudentToDepartment(String departmentName, Student student) {
        Department dept = searchDepartment(departmentName);
        if (dept != null) {
            return dept.addStudent(student);
        }
        return false;
    }

    public boolean removeStudentFromDepartment(String departmentName, String studentID) {
        Department dept = searchDepartment(departmentName);
        if (dept != null) {
            return dept.removeStudent(studentID);
        }
        return false;
    }

    public Student searchStudentInDepartment(String departmentName, String studentID) {
        Department dept = searchDepartment(departmentName);
        if (dept != null) {
            return dept.searchStudent(studentID);
        }
        return null;
    }

    public int addCourseToStudent(Department department, Student student, Courses course) {

        if (student != null) {
            return student.addCourse(course);
        }
        return 100;
    }

    public int removeCourseFromStudent(String studentID, Courses course) {
        Node<Department> tempDept = departments.head;
        while (tempDept != null) {
            Student student = tempDept.data.searchStudent(studentID);
            if (student != null) {
                return student.removeCourse(course);
            }
            tempDept = tempDept.next;
        }
        return 100;
    }

    public boolean addInstructorToDepartment(String departmentName, Instructor instructor) {
        Department dept = searchDepartment(departmentName);
        if (dept != null) {
            return dept.addInstructor(instructor);
        }
        return false;
    }

    public boolean removeInstructorFromDepartment(String departmentName, String instructorID) {
        Department dept = searchDepartment(departmentName);
        if (dept != null) {
            return dept.removeInstructor(instructorID);
        }
        return false;
    }

    public Instructor searchInstructorInDepartment(String departmentName, String instructorID) {
        Department dept = searchDepartment(departmentName);
        if (dept != null) {
            return dept.searchInstructor(instructorID);
        }
        return null;
    }

    public boolean addCourseToInstructor(String instructorID, Courses course) {
        Node<Department> tempDept = departments.head;
        while (tempDept != null) {
            Instructor instructor = tempDept.data.searchInstructor(instructorID);
            if (instructor != null) {
                course.setInstructor(instructor);
                return instructor.addCourse(course);
            }
            tempDept = tempDept.next;
        }
        return false;
    }

    public boolean removeCourseFromInstructor(String instructorID, Courses course) {
        Node<Department> tempDept = departments.head;
        while (tempDept != null) {
            Instructor instructor = tempDept.data.searchInstructor(instructorID);
            if (instructor != null) {
                return instructor.removeCourse(course);
            }
            tempDept = tempDept.next;
        }
        return false;
    }

    public boolean addCourseToDepartment(String departmentName, Courses course) {
        Department dept = searchDepartment(departmentName);
        if (dept != null) {
            return dept.addCourse(course);
        }
        return false;
    }

    public boolean removeCourseFromDepartment(String departmentName, String courseID) {
        Department dept = searchDepartment(departmentName);
        if (dept != null) {
            return dept.removeCourse(courseID);
        }
        return false;
    }

    public Courses searchCourseInDepartment(String departmentName, String courseID) {
        Department dept = searchDepartment(departmentName);
        if (dept != null) {
            return dept.searchCourse(courseID);
        }
        return null;
    }

    public int printDepartments() {
        Node<Department> temp = departments.head;
        if (temp == null) {
            System.out.println("No departments available.");
        }
        while (temp != null) {
            System.out.println("Department Name: " + temp.data.getDepartmentName());
            temp = temp.next;
        }
        return 0;
    }

    public int printCoursesInDepartment(String departmentName) {
        Department dept = searchDepartment(departmentName);
        if (dept != null) {
            dept.printCourses();
            return 0;
        }
        System.out.println("Department not found.");
        return -1;
    }

    public int listStudentsInCourse(Department department, String courseID) {
        Courses course = department.searchCourse(courseID);
        if (course != null) {
            course.printEnrolledStudents();
            return 0;
        }
        System.out.println("Course not found.");
        return -1;
    }

    public double getTotalBalance() {
        totalBalance = 0;
        Node<Department> temp = departments.head;
        while (temp != null) {
            totalBalance += temp.data.getBalance();
            temp = temp.next;
        }
        return totalBalance;
    }

    public double getTotalSalaries() {
        double totalSalaries = 0;
        Node<Department> temp = departments.head;
        while (temp != null) {
            totalSalaries += temp.data.getTotalSalaries();
            temp = temp.next;
        }
        return totalSalaries;
    }

    public double getTotalFees() {
        double totalFees = 0;
        Node<Department> temp = departments.head;
        while (temp != null) {
            totalFees += temp.data.getTotalFees();
            temp = temp.next;
        }
        return totalFees;
    }

    public Department isDepartmentFound(Scanner input) {
        System.out.println("Enter Department Name: ");
        int n = printDepartments();
        if (n != 0) {
            System.out.println("No departments available. Please add a department first.");
            return null;
        }

        String deptName = input.nextLine();

        Department dept = searchDepartment(deptName);
        return dept;
    }
}