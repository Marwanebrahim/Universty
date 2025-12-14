package universty.model;

import universty.interfaces.Hashable;

public abstract class Person implements Hashable {
    String name;
    String id;
    Department department;

    public Person(String name, String id, Department department) {
        this.name = name;
        this.id = id;
        this.department = department;
    }

    @Override
    public String getKey() {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Person))
            return false;
        Person p = (Person) obj;
        return this.id.equals(p.id);
    }
}
