package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;

import java.util.List;

public class ItemsAddedToSetMenuItemEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private RestaurantId id;

    private MenuItemId setMenuItemId;

    private List<MenuItemId> singleItemsIdList;

    public ItemsAddedToSetMenuItemEvent(RestaurantId id, MenuItemId setMenuItemId, List<MenuItemId> singleItemsIdList) {
        this.id = id;
        this.setMenuItemId = setMenuItemId;
        this.singleItemsIdList = singleItemsIdList;
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

    public List<MenuItemId> getSingleItemsIdList() {
        return singleItemsIdList;
    }

    public void setSingleItemsIdList(List<MenuItemId> singleItemsIdList) {
        this.singleItemsIdList = singleItemsIdList;
    }
}
