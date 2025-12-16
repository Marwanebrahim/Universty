package universty.model;

import universty.Structures.Node;
import universty.Structures.SingleLinkedList;
import universty.interfaces.Hashable;

public class Courses implements Hashable {
    String courseName;
    String courseId;
    Department department;
    int creditHours;
    Instructor instructor;
    SingleLinkedList<Student> enrolledStudents;

    public Courses(String courseName, String courseId, Department department, int creditHours) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.department = department;
        this.creditHours = creditHours;
        this.instructor = null;
        this.enrolledStudents = new SingleLinkedList<Student>();
    }

    public boolean addStudent(Student student) {
        if (enrolledStudents.Search(student) != null) {
            return false;
        }
        return enrolledStudents.AddNode(student);
    }

    public boolean removeStudent(Student student) {
        return enrolledStudents.DeleteNode(student);
    }

    public boolean addInstructor(Instructor instructor) {
        if (this.instructor == null) {
            this.instructor = instructor;
            return true;
        }
        return false;
    }

    public boolean removeInstructor() {
        if (this.instructor != null) {
            this.instructor = null;
            return true;
        }
        return false;
    }

    public boolean isStudentEnrolled(Student student) {
        Node<Student> node = enrolledStudents.Search(student);
        return node != null;
    }

    public void printEnrolledStudents() {
        Node<Student> temp = enrolledStudents.head;
        if (temp == null) {
            System.out.println("No students enrolled in this course.");
        }
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    @Override
    public String getKey() {

        return courseId;
    }

    @Override
    public String toString() {
        return "Course Name: " + courseName + ", Course ID: " + courseId + ", Department: "
                + department.getDepartmentName() + ", Credit Hours: " + creditHours +
                ", Instructor: " + (instructor != null ? instructor.getName() : "None");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Courses))
            return false;
        Courses c = (Courses) obj;
        return this.getCourseId().equals(c.getCourseId());
    }
}
