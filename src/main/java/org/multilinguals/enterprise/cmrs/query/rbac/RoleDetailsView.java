package org.multilinguals.enterprise.cmrs.query.rbac;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class RoleDetailsView {
    @Id
    private String id;

    private String name;

    private List<PermissionItemView> permissionItems;

    private Date createdAt;

    private Date updatedAt;

    public RoleDetailsView(String id, String name, Date createdAt) {
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

    public List<PermissionItemView> getPermissionItems() {
        return permissionItems;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
