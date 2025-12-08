package universty;

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
        return departments.AddNode(department);
    }

    public boolean removeDepartment(String departmentName) {
        Node<Department> temp = departments.head;
        while (temp != null && !temp.data.getKey().equals(departmentName)) {
            temp = temp.next;
            if (temp == null) {
                return false;
            }
        }
        return departments.DeleteNode(temp.data);
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

    public boolean addCourseToStudent(String studentID, Courses course) {
        Node<Department> tempDept = departments.head;
        while (tempDept != null) {
            Student student = tempDept.data.searchStudent(studentID);
            if (student != null) {
                return student.addCourse(course);
            }
            tempDept = tempDept.next;
        }
        return false;
    }

    public boolean removeCourseFromStudent(String studentID, Courses course) {
        Node<Department> tempDept = departments.head;
        while (tempDept != null) {
            Student student = tempDept.data.searchStudent(studentID);
            if (student != null) {
                return student.removeCourse(course);
            }
            tempDept = tempDept.next;
        }
        return false;
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

    public void printDepartments() {
        Node<Department> temp = departments.head;
        while (temp != null) {
            System.out.println("Department Name: " + temp.data.getDepartmentName());
            temp = temp.next;
        }
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
}