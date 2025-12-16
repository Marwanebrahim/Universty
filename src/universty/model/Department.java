package universty.model;

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
        if (instructor == null) {
            return false;
        }
        totalSalaries += instructor.getSalary();
        return instructors.insert(instructor);
    }

    public boolean removeInstructor(String key) {
        Instructor instructor = instructors.searchByKey(key);
        if (instructor != null) {
            totalSalaries -= instructor.getSalary();
        }
        return instructors.deleteByKey(key);
    }

    public Instructor searchInstructor(String key) {
        Instructor instructor = instructors.searchByKey(key);

        return instructor;
    }

    public boolean addStudent(Student student) {
        boolean found = searchStudent(student.getId()) != null;
        if (found) {
            return false;
        }
        return students.insert(student);
    }

    public boolean removeStudent(String key) {
        Student student = students.searchByKey(key);
        if (student != null) {
            totalFees -= student.getTotalFees();
            Node<Courses> enrolledcourses = student.enrolledCourses.head;
            while (enrolledcourses != null && enrolledcourses.data != null) {
                Courses course = courses.searchByKey(enrolledcourses.data.getKey());
                if (course != null) {
                    course.removeStudent(student);
                }
                enrolledcourses = enrolledcourses.next;
            }
        }
        return students.deleteByKey(key);
    }

    public Student searchStudent(String key) {
        Student student = students.searchByKey(key);
        if (student != null) {
            return student;
        }
        return null;
    }

    public boolean addCourse(Courses course) {
        boolean found = searchCourse(course.getKey()) != null;
        if (found) {
            return false;
        }
        return courses.insert(course);
    }

    public boolean removeCourse(String key) {
        Courses course = courses.searchByKey(key);
        if (course != null) {
            Node<Student> enrolledStudents = course.enrolledStudents.head;
            while (enrolledStudents != null) {
                enrolledStudents.data.removeCourse(course);
                course.removeStudent(enrolledStudents.data);
                enrolledStudents = enrolledStudents.next;
            }

        }
        return courses.deleteByKey(key);
    }

    public Courses searchCourse(String key) {
        Courses course = courses.searchByKey(key);
        if (course != null) {
            return course;
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

    public void printCourses() {
        courses.printHashTable();
    }

    public void printStudents() {
        students.printHashTable();
    }

    public void printInstructors() {
        instructors.printHashTable();
    }

    public double getTotalSalaries() {
        return totalSalaries;
    }

    public double getTotalFees() {
        return totalFees;
    }

    public HashTable<Instructor> getInstructors() {
        return instructors;
    }

    public HashTable<Student> getStudents() {
        return students;
    }

    public HashTable<Courses> getCourses() {
        return courses;
    }

    @Override
    public String getKey() {
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
