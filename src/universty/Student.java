package universty;

import universty.Structures.SingleLinkedList;

public class Student extends Person {
    SingleLinkedList<Courses> enrolledCourses;
    int registeredHours;
    Double totalFees;

    public Student(String name, String id, Department department) {
        super(name, id, department);
        enrolledCourses = new SingleLinkedList<Courses>();
        registeredHours = 0;
        totalFees = 0.0;
    }

    public boolean registerCourse(Courses course) {
        boolean registered = false;
        if (enrolledCourses.AddNode(course)) {
            registeredHours += course.getCreditHours();
            totalFees += department.getCourseFees() * course.getCreditHours();
            registered = true;
        }
        return registered;
    }

    public boolean dropCourse(Courses course) {
        boolean dropped = false;
        if (enrolledCourses.DeleteNode(course)) {
            registeredHours -= course.getCreditHours();
            totalFees -= department.getCourseFees() * course.getCreditHours();
            dropped = true;
        }
        return dropped;
    }

    public int getRegisteredHours() {
        return registeredHours;
    }

    public void setRegisteredHours(int registeredHours) {
        this.registeredHours = registeredHours;
    }

    public Double getTotalFees() {
        return totalFees;
    }

}
