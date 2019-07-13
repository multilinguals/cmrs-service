package org.multilinguals.enterprise.cmrs.query.menuitem;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;

public class MenuItemView {
    @Id
    private String id;

    private String restaurantId;

    private String name;

    private String menuItemTypeId;

    private String menuItemName;

    private String dishTypeId;

    private String dishTypeName;

    private String tasteId;

    private String tasteName;

    private BigDecimal price;

    private Boolean onShelve;

    private Date createdAt;

    private Date updatedAt;

    public MenuItemView() {
    }

    public MenuItemView(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public MenuItemView(String id, String restaurantId, String name, String menuItemTypeId, String menuItemName, String dishTypeId, String dishTypeName, String tasteId, String tasteName, BigDecimal price, Boolean onShelve, Date createdAt) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.menuItemTypeId = menuItemTypeId;
        this.menuItemName = menuItemName;
        this.dishTypeId = dishTypeId;
        this.dishTypeName = dishTypeName;
        this.tasteId = tasteId;
        this.tasteName = tasteName;
        this.price = price;
        this.onShelve = onShelve;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public String getMenuItemTypeId() {
        return menuItemTypeId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public String getDishTypeId() {
        return dishTypeId;
    }

    public String getDishTypeName() {
        return dishTypeName;
    }

    public String getTasteId() {
        return tasteId;
    }

    public String getTasteName() {
        return tasteName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean getOnShelve() {
        return onShelve;
    }

    public void setOnShelve(Boolean onShelve) {
        this.onShelve = onShelve;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
