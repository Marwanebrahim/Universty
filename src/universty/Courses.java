package universty;

import universty.Structures.Node;
import universty.Structures.SingleLinkedList;
import universty.interfaces.Hashable;

public class Courses implements Hashable {
    String courseName;
    String courseId;
    Department department;
    int creditHours;
    String instructorName;
    SingleLinkedList<Student> enrolledStudents;

    public Courses(String courseName, String courseId, Department department, int creditHours, String instructorName) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.department = department;
        this.creditHours = creditHours;
        this.instructorName = instructorName;
        this.enrolledStudents = new SingleLinkedList<Student>();
    }

    public boolean enrollStudent(Student student) {
        return enrolledStudents.AddNode(student);
    }

    public boolean removeStudent(Student student) {
        return enrolledStudents.DeleteNode(student);
    }

    public boolean isStudentEnrolled(Student student) {
        Node<Student> node = enrolledStudents.Search(student);
        return node != null;
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

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    @Override
    public String GetKey() {

        return courseId;
    }

}
