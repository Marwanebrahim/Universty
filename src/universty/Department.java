package universty;

import universty.Structures.HashTable;
import universty.Structures.Node;

public class Department {
    String departmentName;
    double courseFees;
    double totalSalaries;
    double totalFees;
    HashTable<Instructor> instructors;
    HashTable<Student> students;
    HashTable<Courses> courses;

    public Department(String name, double courseFees) {
        this.departmentName = name;
        this.courseFees = courseFees;
        instructors = new HashTable<Instructor>();
        students = new HashTable<Student>();
        courses = new HashTable<Courses>();
    }

    public boolean addInstructor(Instructor instructor) {
        totalSalaries += instructor.getSalary();
        return instructors.insert(instructor);
    }

    public boolean removeInstructor(String key) {
        Node<Instructor> instructor = instructors.SearchByKey(key);
        if (instructor != null) {
            totalSalaries -= instructor.data.getSalary();
        }
        return instructors.DeleteByKey(key);
    }

    public boolean searchInstructor(String key) {
        return instructors.SearchByKey(key) != null;
    }

    public boolean addStudent(Student student) {
        totalFees += student.getTotalFees();
        return students.insert(student);
    }

    public boolean removeStudent(String key) {
        Node<Student> student = students.SearchByKey(key);
        if (student != null) {
            totalFees -= student.data.getTotalFees();
        }
        return students.DeleteByKey(key);
    }

    public boolean searchStudent(String key) {
        return students.SearchByKey(key) != null;
    }

    public boolean addCourse(Courses course) {
        return courses.insert(course);
    }

    public boolean removeCourse(String key) {
        return courses.DeleteByKey(key);
    }

    public boolean searchCourse(String key) {
        return courses.SearchByKey(key) != null;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public double getCourseFees() {
        return courseFees;
    }

    public void setCourseFees(double courseFees) {
        this.courseFees = courseFees;
    }
}
