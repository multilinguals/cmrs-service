package org.multilinguals.enterprise.cmrs.query.menuitem;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

public class SetSubItemView {
    @Id
    private String id;

    private Integer quantity;

    @DBRef
    private SingleMenuItemView singleMenuItemView;

    private Date createdAt;

    private Date updatedAt;

    public SetSubItemView(String id, Integer quantity, SingleMenuItemView singleMenuItemView, Date createdAt) {
        this.id = id;
        this.quantity = quantity;
        this.singleMenuItemView = singleMenuItemView;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public SingleMenuItemView getSingleMenuItemView() {
        return singleMenuItemView;
    }

    public void setSingleMenuItemView(SingleMenuItemView singleMenuItemView) {
        this.singleMenuItemView = singleMenuItemView;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
