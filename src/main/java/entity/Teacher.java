package entity;

import dao.TeacherTypeDao;
import java.util.List;
import java.util.Objects;

public class Teacher  extends User{

    private static final long serialVersionUID = 4402350169102148284L;
    private List<Subject> subjectList;
    private TeacherType type;
    private Boolean isAdmin;

    public Teacher(){}

    public Teacher (String lastName, String firstName, String middleName, String login, String password, List<Subject> subjects, TeacherType type, Boolean isAdmin){
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.login = login;
        this.password = password;
        this.subjectList  = subjects;
        this.type = type;
        this.isAdmin = isAdmin;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubject(List<Subject> subjects) {
        this.subjectList = subjects;
    }

    public TeacherType getType() {
        return type;
    }

    public void setType(TeacherType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher  = (Teacher) o;
        return  id.equals(teacher.id) &&
                lastName.equals(teacher.lastName) &&
                firstName.equals(teacher.firstName) &&
                Objects.equals(middleName, teacher.middleName) &&
                login.equals(teacher.login) &&
                password.equals(teacher.password)&&
                subjectList.equals(teacher.subjectList)&&
                type.equals(teacher.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, middleName, login, password, subjectList);
    }
    @Override
    public String toString() {
        StringBuilder subjectBuilder = new StringBuilder();
        for(Subject subject: subjectList){
            subjectBuilder.append(subject).append("/");
        }
        return id+ ". " + lastName + " " + firstName+ " "+ middleName + " /" + subjectBuilder +" "+ type.getTypeName();
    }

}
