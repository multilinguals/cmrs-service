package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant;

import org.axonframework.modelling.command.EntityId;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;

import java.math.BigDecimal;

public abstract class MenuItem {
    @EntityId
    private MenuItemId id;

    private String name;

    private MenuItemTypeId menuItemTypeId;

    private BigDecimal price;

    private Boolean onShelve;

    public MenuItem(MenuItemId id, String name, MenuItemTypeId menuItemTypeId, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.menuItemTypeId = menuItemTypeId;
        this.price = price;
        this.onShelve = false;
    }

    public MenuItemId getId() {
        return id;
    }

    public void setId(MenuItemId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuItemTypeId getMenuItemTypeId() {
        return menuItemTypeId;
    }

    public void setMenuItemTypeId(MenuItemTypeId menuItemTypeId) {
        this.menuItemTypeId = menuItemTypeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getOnShelve() {
        return onShelve;
    }

    public void setOnShelve(Boolean onShelve) {
        this.onShelve = onShelve;
    }
}
