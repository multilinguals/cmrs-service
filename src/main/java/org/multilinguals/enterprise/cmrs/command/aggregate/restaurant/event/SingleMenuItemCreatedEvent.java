package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.DishTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.taste.TasteId;

import java.math.BigDecimal;

public class SingleMenuItemCreatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MenuItemId id;

    private RestaurantId restaurantId;

    private String name;

    private MenuItemTypeId menuItemTypeId;

    private DishTypeId dishTypeId;

    private TasteId tasteId;

    private BigDecimal price;

    private Boolean onShelve;

    public SingleMenuItemCreatedEvent(MenuItemId id, RestaurantId restaurantId, String name, MenuItemTypeId menuItemTypeId, DishTypeId dishTypeId, TasteId tasteId, BigDecimal price) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.menuItemTypeId = menuItemTypeId;
        this.dishTypeId = dishTypeId;
        this.tasteId = tasteId;
        this.price = price;
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

    public DishTypeId getDishTypeId() {
        return dishTypeId;
    }

    public TasteId getTasteId() {
        return tasteId;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
