package entity;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 3808108883402350715L;
    protected Long id;
    protected Boolean isActive = true;

    BaseEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
