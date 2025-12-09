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

    public int addCourse(Courses course) {

        if (registeredHours + course.getCreditHours() <= 17) {
            if (enrolledCourses.AddNode(course)) {
                registeredHours += course.getCreditHours();
                totalFees += department.getCourseFees() * course.getCreditHours();
                course.addStudent(this);
            }
        }
        return registeredHours;
    }

    public int removeCourse(Courses course) {

        if (enrolledCourses.DeleteNode(course)) {
            registeredHours -= course.getCreditHours();
            totalFees -= department.getCourseFees() * course.getCreditHours();
            course.removeStudent(this);
        }
        return registeredHours;
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

    public String getStudentId() {
        return this.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Student))
            return false;
        Student s = (Student) obj;
        return this.getId().equals(s.getId());
    }

    @Override
    public String toString() {
        return "Student ID: " + this.getId() + ", Name: " + this.getName() + ", Department: "
                + this.department.getDepartmentName() + ", Registered Hours: " + this.registeredHours
                + ", Total Fees: " + this.totalFees;
    }

}
