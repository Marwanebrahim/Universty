package universty;

import universty.Structures.HashTable;
import universty.Structures.Node;
import universty.interfaces.Hashable;

public class Department implements Hashable {
    String departmentName;
    double courseFees;
    double totalSalaries;
    double totalFees;
    double balance;
    HashTable<Instructor> instructors;
    HashTable<Student> students;
    HashTable<Courses> courses;

    public Department(String name, double courseFees) {
        this.departmentName = name;
        this.courseFees = courseFees;
        totalSalaries = 0;
        totalFees = 0;
        balance = 0;
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

    public Instructor searchInstructor(String key) {
        Node<Instructor> instructor = instructors.SearchByKey(key);
        if (instructor != null) {
            return instructor.data;
        }
        return null;
    }

    public boolean addStudent(Student student) {
        totalFees += student.getTotalFees();
        Node<Courses> enrolledcourses = student.enrolledCourses.head;
        while (enrolledcourses != null) {
            Node<Courses> course = courses.SearchByKey(enrolledcourses.data.GetKey());
            if (course != null) {
                course.data.enrollStudent(student);
            }
            enrolledcourses = enrolledcourses.next;
        }
        return students.insert(student);
    }

    public boolean removeStudent(String key) {
        Node<Student> student = students.SearchByKey(key);
        if (student != null) {
            totalFees -= student.data.getTotalFees();
            Node<Courses> enrolledcourses = student.data.enrolledCourses.head;
            while (enrolledcourses != null && enrolledcourses.data != null) {
                Node<Courses> course = courses.SearchByKey(enrolledcourses.data.GetKey());
                if (course != null) {
                    course.data.removeStudent(student.data);
                }
                enrolledcourses = enrolledcourses.next;
            }
        }
        return students.DeleteByKey(key);
    }

    public Student searchStudent(String key) {
        Node<Student> student = students.SearchByKey(key);
        if (student != null) {
            return student.data;
        }
        return null;
    }

    public boolean addCourse(Courses course) {

        return courses.insert(course);
    }

    public boolean removeCourse(String key) {
        Node<Courses> course = courses.SearchByKey(key);
        if (course != null) {
            Node<Student> enrolledStudents = course.data.enrolledStudents.head;
            while (enrolledStudents != null) {
                enrolledStudents.data.dropCourse(course.data);
                enrolledStudents = enrolledStudents.next;
            }

        }
        return courses.DeleteByKey(key);
    }

    public Courses searchCourse(String key) {
        Node<Courses> course = courses.SearchByKey(key);
        if (course != null) {
            return course.data;
        }
        return null;
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

    public double getBalance() {
        balance = totalFees - totalSalaries;
        return balance;
    }

    @Override
    public String GetKey() {
        return departmentName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Department))
            return false;
        Department d = (Department) obj;
        return this.departmentName.equals(d.departmentName);
    }
}
