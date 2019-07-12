package org.multilinguals.enterprise.cmrs.query.menuitemtype;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class MenuItemTypeView {
    @Id
    private String id;

    private String name;

    private Date createdAt;

    private Date updatedAt;

    public MenuItemTypeView(String id, String name, Date createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
