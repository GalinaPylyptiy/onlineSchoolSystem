package entity;

import java.io.Serializable;
import java.util.Objects;

public class TeacherType implements Serializable {
    private static final long serialVersionUID = 6548767120708121596L;
    private Integer id;
    private String typeName;

    public TeacherType(){};

    public TeacherType(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherType that = (TeacherType) o;
        return id.equals(that.id) &&
                Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName);
    }

    @Override
    public String toString() {
        return "TeacherType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
