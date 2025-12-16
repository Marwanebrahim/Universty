package universty.system;

import java.util.Scanner;

import universty.Structures.Node;
import universty.Structures.SingleLinkedList;
import universty.model.Courses;
import universty.model.Department;
import universty.model.Instructor;
import universty.model.Student;

public class RigesterSystem {
    SingleLinkedList<Department> departments;
    double totalBalance;

    public RigesterSystem() {
        departments = new SingleLinkedList<Department>();
        totalBalance = 0;
    }

    // CRUD Department
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

    // CRUD Student
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
        return -1;
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
        return -1;
    }

    // CRUD Instructor
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
                course.addInstructor(instructor);
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

    // CRUD Course
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

    public Courses searchCourseInDepartment(Department department, String courseID) {
        if (department != null) {
            return department.searchCourse(courseID);
        }

        return null;
    }

    // Reports and Listings
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

    public double getBalance(Department department) {
        return department.getBalance();
    }

    public double getTotalFeesInDepartment(Department department) {
        return department.getTotalFees();
    }

    public double getTotalSalariesInDepartment(Department department) {
        return department.getTotalSalaries();
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
        int n = printDepartments();
        if (n != 0) {
            System.out.println("No departments available. Please add a department first.");
            return null;
        }
        System.out.println("Enter Department Name: ");

        String deptName = input.nextLine();

        Department dept = searchDepartment(deptName);
        return dept;
    }

    public void DumbyData() {
        Department csDept = new Department("Computer Science", 5000.0);
        Department mathDept = new Department("Mathematics", 4000.0);
        Department physicsDept = new Department("Physics", 4500.0);
        Department chemistryDept = new Department("Chemistry", 4200.0);
        Department biologyDept = new Department("Biology", 3800.0);
        addDepartment(csDept);
        addDepartment(mathDept);
        addDepartment(physicsDept);
        addDepartment(chemistryDept);
        addDepartment(biologyDept);

        // Create 4 Instructors (distributed across departments)
        Instructor alice = new Instructor("Alice Johnson", "I001", csDept, 60000.0);
        Instructor bob = new Instructor("Bob Smith", "I002", mathDept, 55000.0);
        Instructor charlie = new Instructor("Charlie Brown", "I003", physicsDept, 58000.0);
        Instructor diana = new Instructor("Diana Prince", "I004", chemistryDept, 57000.0);
        addInstructorToDepartment("Computer Science", alice);
        addInstructorToDepartment("Mathematics", bob);
        addInstructorToDepartment("Physics", charlie);
        addInstructorToDepartment("Chemistry", diana);
        // Biology has no instructor for variety

        // Create 20 Students (4 per department)
        // CS Students
        Student john = new Student("John Doe", "S001", csDept);
        Student jane = new Student("Jane Smith", "S002", csDept);
        Student mike = new Student("Mike Johnson", "S003", csDept);
        Student lisa = new Student("Lisa Brown", "S004", csDept);
        // Math Students
        Student paul = new Student("Paul Wilson", "S005", mathDept);
        Student emma = new Student("Emma Davis", "S006", mathDept);
        Student david = new Student("David Miller", "S007", mathDept);
        Student sophia = new Student("Sophia Garcia", "S008", mathDept);
        // Physics Students
        Student oliver = new Student("Oliver Martinez", "S009", physicsDept);
        Student ava = new Student("Ava Rodriguez", "S010", physicsDept);
        Student william = new Student("William Lopez", "S011", physicsDept);
        Student isabella = new Student("Isabella Gonzalez", "S012", physicsDept);
        // Chemistry Students
        Student james = new Student("James Perez", "S013", chemistryDept);
        Student mia = new Student("Mia Taylor", "S014", chemistryDept);
        Student benjamin = new Student("Benjamin Anderson", "S015", chemistryDept);
        Student charlotte = new Student("Charlotte Thomas", "S016", chemistryDept);

        // Biology Students
        Student henry = new Student("Henry Jackson", "S017", biologyDept);
        Student amelia = new Student("Amelia White", "S018", biologyDept);
        Student lucas = new Student("Lucas Harris", "S019", biologyDept);
        Student harper = new Student("Harper Clark", "S020", biologyDept);
        addStudentToDepartment("Computer Science", john);
        addStudentToDepartment("Computer Science", jane);
        addStudentToDepartment("Computer Science", mike);
        addStudentToDepartment("Computer Science", lisa);
        addStudentToDepartment("Mathematics", paul);
        addStudentToDepartment("Mathematics", emma);
        addStudentToDepartment("Mathematics", david);
        addStudentToDepartment("Mathematics", sophia);
        addStudentToDepartment("Physics", oliver);
        addStudentToDepartment("Physics", ava);
        addStudentToDepartment("Physics", william);
        addStudentToDepartment("Physics", isabella);
        addStudentToDepartment("Chemistry", james);
        addStudentToDepartment("Chemistry", mia);
        addStudentToDepartment("Chemistry", benjamin);
        addStudentToDepartment("Chemistry", charlotte);
        addStudentToDepartment("Biology", henry);
        addStudentToDepartment("Biology", amelia);
        addStudentToDepartment("Biology", lucas);
        addStudentToDepartment("Biology", harper);

        // Create 6 Courses (distributed across departments)
        Courses ds = new Courses("Data Structures", "C101", csDept, 3);
        Courses algo = new Courses("Algorithms", "C102", csDept, 4);
        Courses calc = new Courses("Calculus", "M101", mathDept, 4);
        Courses stats = new Courses("Statistics", "M102", mathDept, 3);
        Courses mechanics = new Courses("Mechanics", "P101", physicsDept, 4);
        Courses organicChem = new Courses("Organic Chemistry", "CH101", chemistryDept, 3);
        addCourseToDepartment("Computer Science", ds);
        addCourseToDepartment("Computer Science", algo);
        addCourseToDepartment("Mathematics", calc);
        addCourseToDepartment("Mathematics", stats);
        addCourseToDepartment("Physics", mechanics);
        addCourseToDepartment("Chemistry", organicChem);
        // Assign courses to instructors
        addCourseToInstructor("I001", ds);
        addCourseToInstructor("I001", algo);
        addCourseToInstructor("I002", calc);
        addCourseToInstructor("I002", stats);
        addCourseToInstructor("I003", mechanics);
        addCourseToInstructor("I004", organicChem);
        // Enroll students in courses (simulate some enrollments, respecting credit
        // limits)
        // CS Students
        addCourseToStudent(csDept, john, ds);
        addCourseToStudent(csDept, jane, algo);
        addCourseToStudent(csDept, mike, ds);
        addCourseToStudent(csDept, lisa, algo);
        // Math Students
        addCourseToStudent(mathDept, paul, calc);
        addCourseToStudent(mathDept, emma, stats);
        addCourseToStudent(mathDept, david, calc);
        addCourseToStudent(mathDept, sophia, stats);
        // Physics Students
        addCourseToStudent(physicsDept, oliver, mechanics);
        addCourseToStudent(physicsDept, ava, mechanics);
        // Chemistry Students
        addCourseToStudent(chemistryDept, james, organicChem);
        addCourseToStudent(chemistryDept, mia, organicChem);
        // Biology Students (no courses, as expected)
        // No enrollments for biology since there are no courses
        System.out.println(
                "Dummy data added successfully: 5 departments, 4 instructors, 20 students, 6 courses, and sample enrollments.");
    }

}