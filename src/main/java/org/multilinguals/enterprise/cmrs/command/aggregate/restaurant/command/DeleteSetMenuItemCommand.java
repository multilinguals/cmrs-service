package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;

public class DeleteSetMenuItemCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private RestaurantId restaurantId;

    private MenuItemId menuItemId;

    public DeleteSetMenuItemCommand(RestaurantId restaurantId, MenuItemId menuItemId) {
        this.restaurantId = restaurantId;
        this.menuItemId = menuItemId;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }

    public MenuItemId getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(MenuItemId menuItemId) {
        this.menuItemId = menuItemId;
    }
}
