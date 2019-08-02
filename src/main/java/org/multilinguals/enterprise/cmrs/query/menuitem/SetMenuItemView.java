package org.multilinguals.enterprise.cmrs.query.menuitem;

import org.multilinguals.enterprise.cmrs.infrastructure.i18n.I18Translator;
import org.multilinguals.enterprise.cmrs.infrastructure.persistence.Localizable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SetMenuItemView implements Localizable {
    @Id
    private String id;

    private String restaurantId;

    private String name;

    private String menuItemTypeId;

    private String menuItemTypeName;

    private BigDecimal price;

    private Boolean onShelve;

    @DBRef
    private List<SingleMenuItemView> singleMenuItems;

    private Date createdAt;

    private Date updatedAt;

    public SetMenuItemView() {
    }

    public SetMenuItemView(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public SetMenuItemView(String id, String restaurantId) {
        this.id = id;
        this.restaurantId = restaurantId;
    }

    public SetMenuItemView(String id, String restaurantId, String name, String menuItemTypeId, String menuItemTypeName, BigDecimal price, Boolean onShelve, List<SingleMenuItemView> singleMenuItems, Date createdAt) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.menuItemTypeId = menuItemTypeId;
        this.menuItemTypeName = menuItemTypeName;
        this.price = price;
        this.onShelve = onShelve;
        this.singleMenuItems = singleMenuItems;
        this.createdAt = createdAt;
    }

    @Override
    public void localize(I18Translator i18Translator) {
        for (SingleMenuItemView item : this.singleMenuItems) {
            item.localize(i18Translator);
        }
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

    public Iterable<SingleMenuItemView> getSingleMenuItems() {
        return singleMenuItems;
    }

    public void setSingleMenuItems(List<SingleMenuItemView> singleMenuItems) {
        this.singleMenuItems = singleMenuItems;
    }

    public void addSingleMenuItem(SingleMenuItemView singleMenuItem) {
        this.singleMenuItems.add(singleMenuItem);
    }

    public void removeSingleMenuItem(SingleMenuItemView ToDeleteItem) {
        for (SingleMenuItemView singleMenuItemView : this.singleMenuItems) {
            if (singleMenuItemView.getId().equals(ToDeleteItem.getId())) {
                this.singleMenuItems.remove(singleMenuItemView);
            }
        }
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
