package entity;

import java.io.Serializable;
import java.util.Objects;

public class Level implements Serializable {
    private static final long serialVersionUID = 5936224069198481475L;
    private Integer id;
    private String name;

    public Level(){}
    public Level(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level level = (Level) o;
        return id.equals(level.id) &&
                name.equals(level.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return id + ". " +name;
    }
}
