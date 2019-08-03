package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant;

import org.axonframework.modelling.command.EntityId;

public class SetSubItem {
    @EntityId
    private SetSubItemId id;

    private MenuItemId singleMenuItemId;

    private Integer quantity;

    public SetSubItem(SetSubItemId id, MenuItemId singleMenuItemId, Integer quantity) {
        this.id = id;
        this.singleMenuItemId = singleMenuItemId;
        this.quantity = quantity;
    }

    public SetSubItemId getId() {
        return id;
    }

    public void setId(SetSubItemId id) {
        this.id = id;
    }

    public MenuItemId getSingleMenuItemId() {
        return singleMenuItemId;
    }

    public void setSingleMenuItemId(MenuItemId singleMenuItemId) {
        this.singleMenuItemId = singleMenuItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
