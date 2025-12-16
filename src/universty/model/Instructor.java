package universty.model;

import universty.Structures.Node;
import universty.Structures.SingleLinkedList;

public class Instructor extends Person {
    Double salary;
    SingleLinkedList<Courses> teachingCourses;

    public Instructor(String name, String id, Department department, Double salary) {
        super(name, id, department);
        teachingCourses = new SingleLinkedList<Courses>();
        this.salary = salary;
    }

    public boolean addCourse(Courses course) {
        Node<Courses> node = teachingCourses.Search(course);
        if (node != null) {
            return false;
        }
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

    public String printCoursesNames() {
        if (teachingCourses.IsEmpty()) {
            System.out.println("No courses assigned.");
            return "No courses assigned.";
        }
        String coursesNames = "";
        Node<Courses> current = teachingCourses.head;
        while (current != null) {
            coursesNames += current.data.getCourseName() + ", ";
            current = current.next;
        }
        return coursesNames;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Instructor))
            return false;
        Instructor i = (Instructor) obj;
        return this.getId().equals(i.getId());
    }

    @Override
    public String toString() {
        return "Instructor Name: " + getName() + ", ID: " + getId() + ", Department: "
                + getDepartment().getDepartmentName() + ", Salary: " + salary +
                ", Teaching Courses: " + (teachingCourses.IsEmpty() ? "none" : printCoursesNames());
    }
}
