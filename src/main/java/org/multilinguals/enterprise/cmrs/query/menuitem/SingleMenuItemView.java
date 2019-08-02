package org.multilinguals.enterprise.cmrs.query.menuitem;

import org.multilinguals.enterprise.cmrs.infrastructure.i18n.I18Translator;
import org.multilinguals.enterprise.cmrs.infrastructure.persistence.Localizable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.util.Date;

public class SingleMenuItemView implements Localizable {
    @Id
    private String id;

    private String restaurantId;

    private String name;

    private String menuItemTypeId;

    private String menuItemTypeName;

    @Transient
    private String localMenuItemTypeName;

    private String dishTypeId;

    private String dishTypeName;

    @Transient
    private String localDishTypeName;

    private String tasteId;

    private String tasteName;

    @Transient
    private String localTasteName;

    private BigDecimal price;

    private Boolean onShelve;

    private Date createdAt;

    private Date updatedAt;

    public SingleMenuItemView() {
    }

    public SingleMenuItemView(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public SingleMenuItemView(String id, String restaurantId) {
        this.id = id;
        this.restaurantId = restaurantId;
    }

    public SingleMenuItemView(String id, String restaurantId, String name, String menuItemTypeId, String menuItemTypeName, String dishTypeId, String dishTypeName, BigDecimal price, Boolean onShelve, Date createdAt) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.menuItemTypeId = menuItemTypeId;
        this.menuItemTypeName = menuItemTypeName;
        this.dishTypeId = dishTypeId;
        this.dishTypeName = dishTypeName;
        this.price = price;
        this.onShelve = onShelve;
        this.createdAt = createdAt;
    }

    @Override
    public void localize(I18Translator i18Translator) {
        this.localMenuItemTypeName = i18Translator.localize("MENU_ITEM_TYPE_" + this.menuItemTypeName);
        this.localDishTypeName = i18Translator.localize("DISH_TYPE_" + this.dishTypeName);
        this.localTasteName = i18Translator.localize("TASTE_" + this.tasteName);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuItemTypeId() {
        return menuItemTypeId;
    }

    public void setMenuItemTypeId(String menuItemTypeId) {
        this.menuItemTypeId = menuItemTypeId;
    }

    public String getMenuItemTypeName() {
        return menuItemTypeName;
    }

    public void setMenuItemTypeName(String menuItemTypeName) {
        this.menuItemTypeName = menuItemTypeName;
    }

    public String getLocalMenuItemTypeName() {
        return localMenuItemTypeName;
    }

    public void setLocalMenuItemTypeName(String localMenuItemTypeName) {
        this.localMenuItemTypeName = localMenuItemTypeName;
    }

    public String getDishTypeId() {
        return dishTypeId;
    }

    public void setDishTypeId(String dishTypeId) {
        this.dishTypeId = dishTypeId;
    }

    public String getDishTypeName() {
        return dishTypeName;
    }

    public void setDishTypeName(String dishTypeName) {
        this.dishTypeName = dishTypeName;
    }

    public String getLocalDishTypeName() {
        return localDishTypeName;
    }

    public void setLocalDishTypeName(String localDishTypeName) {
        this.localDishTypeName = localDishTypeName;
    }

    public String getTasteId() {
        return tasteId;
    }

    public void setTasteId(String tasteId) {
        this.tasteId = tasteId;
    }

    public String getTasteName() {
        return tasteName;
    }

    public void setTasteName(String tasteName) {
        this.tasteName = tasteName;
    }

    public String getLocalTasteName() {
        return localTasteName;
    }

    public void setLocalTasteName(String localTasteName) {
        this.localTasteName = localTasteName;
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
