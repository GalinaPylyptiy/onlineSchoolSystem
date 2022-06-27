package entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -4514793534348857576L;
    protected String lastName;
    protected String firstName;
    protected String middleName;
    protected String login;
    protected String password;

    User(){}

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId()) &&
                lastName.equals(user.lastName) &&
                firstName.equals(user.firstName) &&
                Objects.equals(middleName, user.middleName) &&
                login.equals(user.login) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), lastName, firstName, middleName, login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }
}
