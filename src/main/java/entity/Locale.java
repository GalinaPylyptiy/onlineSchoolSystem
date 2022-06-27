package entity;

import java.io.Serializable;
import java.util.Objects;

public class Locale implements Serializable {
    private static final long serialVersionUID = 7493053096291005808L;
    private Integer id;
    private String shortName;
    private String fullName;

    private Locale(){
       id=1;
       shortName="ru_RU";
       fullName="РУССКИЙ";
    }
    public Locale(int id, String shortName, String fullName) {
        this.id = id;
        this.shortName = shortName;
        this.fullName = fullName;
    }
    public static Locale getDefault(){
        return new Locale();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locale locale = (Locale) o;
        return id.equals(locale.id) &&
                Objects.equals(shortName, locale.shortName) &&
                Objects.equals(fullName, locale.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shortName, fullName);
    }

    @Override
    public String toString() {
        return "Locale{" +
                "id=" + id +
                ", shortName='" + shortName + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
