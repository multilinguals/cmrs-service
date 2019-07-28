package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AddItemsToSetMenuItemCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private RestaurantId restaurantId;

    @NotNull
    private MenuItemId setMenuItemId;

    private List<MenuItemId> singleItemsIdList;

    public AddItemsToSetMenuItemCommand(RestaurantId restaurantId, MenuItemId setMenuItemId, List<MenuItemId> singleItemsIdList) {
        this.restaurantId = restaurantId;
        this.setMenuItemId = setMenuItemId;
        this.singleItemsIdList = singleItemsIdList;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
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
