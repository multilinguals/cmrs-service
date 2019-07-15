package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.SingleMenuItem;

import java.math.BigDecimal;
import java.util.Map;

public class SetMenuItemCreatedEvent extends AbstractEvent {
    private MenuItemId id;

    @TargetAggregateIdentifier
    private RestaurantId restaurantId;

    private String name;

    private MenuItemTypeId menuItemTypeId;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private Map<MenuItemId, SingleMenuItem> singleMenuItems;

    public SetMenuItemCreatedEvent(MenuItemId id, RestaurantId restaurantId, String name, MenuItemTypeId menuItemTypeId, BigDecimal price, BigDecimal totalPrice, Map<MenuItemId, SingleMenuItem> singleMenuItems) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.menuItemTypeId = menuItemTypeId;
        this.price = price;
        this.totalPrice = totalPrice;
        this.singleMenuItems = singleMenuItems;
    }

    public MenuItemId getId() {
        return id;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public MenuItemTypeId getMenuItemTypeId() {
        return menuItemTypeId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Map<MenuItemId, SingleMenuItem> getSingleMenuItems() {
        return singleMenuItems;
    }
}
