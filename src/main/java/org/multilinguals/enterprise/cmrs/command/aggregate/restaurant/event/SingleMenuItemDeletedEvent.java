package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;

public class SingleMenuItemDeletedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private RestaurantId restaurantId;

    private MenuItemId menuItemId;

    public SingleMenuItemDeletedEvent(RestaurantId restaurantId, MenuItemId menuItemId) {
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
