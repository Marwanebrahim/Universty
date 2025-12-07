package universty;

import universty.Structures.SingleLinkedList;

public class Instructor extends Person {
    Double salary;
    SingleLinkedList<Courses> teachingCourses;

    public Instructor(String name, String id, Department department, Double salary) {
        super(name, id, department);
        teachingCourses = new SingleLinkedList<Courses>();
        this.salary = salary;
    }

    public boolean assignCourse(Courses course) {
        return teachingCourses.AddNode(course);
    }

    public boolean removeCourse(Courses course) {
        return teachingCourses.DeleteNode(course);
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
