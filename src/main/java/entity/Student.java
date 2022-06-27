package entity;

import java.util.Objects;

public class Student extends User {
    private static final long serialVersionUID = -1277431531600900895L;
    private Level level;
    public Student(){}
    public  Student(String lastName, String firstName, String middleName, String login, String password, Level level){
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.login = login;
        this.password = password;
        this.level  = level;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student  = (Student) o;
        return  id.equals(student.id)  &&
                lastName.equals(student.lastName) &&
                firstName.equals(student.firstName) &&
                Objects.equals(middleName, student.middleName) &&
                login.equals(student.login) &&
                password.equals(student.password)&&
                level.equals(student.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, middleName, login, password, level);
    }
    @Override
    public String toString() {
        return "Student : " +
                "id=" + id+
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", level = " + level.getName();
    }

}
