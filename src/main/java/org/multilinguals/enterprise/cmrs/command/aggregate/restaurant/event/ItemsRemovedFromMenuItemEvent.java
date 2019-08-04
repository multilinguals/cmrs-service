package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.SetSubItemId;

import java.math.BigDecimal;
import java.util.List;

public class ItemsRemovedFromMenuItemEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private RestaurantId id;

    private MenuItemId setMenuItemId;

    private BigDecimal price;

    private List<SetSubItemId> subItemIdList;

    public ItemsRemovedFromMenuItemEvent(RestaurantId id, MenuItemId setMenuItemId, BigDecimal price, List<SetSubItemId> subItemIdList) {
        this.id = id;
        this.setMenuItemId = setMenuItemId;
        this.price = price;
        this.subItemIdList = subItemIdList;
    }

    public RestaurantId getId() {
        return id;
    }

    public void setId(RestaurantId id) {
        this.id = id;
    }

    public MenuItemId getSetMenuItemId() {
        return setMenuItemId;
    }

    public void setSetMenuItemId(MenuItemId setMenuItemId) {
        this.setMenuItemId = setMenuItemId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<SetSubItemId> getSubItemIdList() {
        return subItemIdList;
    }

    public void setSubItemIdList(List<SetSubItemId> subItemIdList) {
        this.subItemIdList = subItemIdList;
    }
}
