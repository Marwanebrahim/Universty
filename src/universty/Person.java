package universty;

import universty.Structures.Node;
import universty.Structures.SingleLinkedList;
import universty.interfaces.Hashable;

public class Person implements Hashable {
    String name;
    String id;
    Department department;
    SingleLinkedList<Courses> courses;

    public Person(String name, String id, Department department) {
        this.name = name;
        this.id = id;
        this.department = department;
        courses = new SingleLinkedList<Courses>();
    }

    public boolean addCourse(Courses course) {
        return courses.AddNode(course);
    }

    public boolean removeCourse(Courses course) {
        return courses.DeleteNode(course);
    }

    public boolean isEnrolledInCourse(Courses course) {
        boolean found = false;
        Node<Courses> temp = courses.head;
        while (temp != null) {
            if (temp.data.equals(course)) {
                found = true;
                break;
            }
            temp = temp.next;
        }
        return found;
    }

    @Override
    public String GetKey() {
        return id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
