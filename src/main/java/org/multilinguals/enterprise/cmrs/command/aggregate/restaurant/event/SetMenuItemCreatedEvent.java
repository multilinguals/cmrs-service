package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.SetSubItem;

import java.math.BigDecimal;
import java.util.List;

public class SetMenuItemCreatedEvent extends AbstractEvent {
    private MenuItemId id;

    @TargetAggregateIdentifier
    private RestaurantId restaurantId;

    private String name;

    private MenuItemTypeId menuItemTypeId;

    private BigDecimal price;

    private List<SetSubItem> subItems;

    private Boolean onShelve;

    public SetMenuItemCreatedEvent(MenuItemId id, RestaurantId restaurantId, String name, MenuItemTypeId menuItemTypeId, BigDecimal price, List<SetSubItem> subItems, Boolean onShelve) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.menuItemTypeId = menuItemTypeId;
        this.price = price;
        this.subItems = subItems;
        this.onShelve = onShelve;
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

    public List<SetSubItem> getSubItems() {
        return subItems;
    }

    public Boolean getOnShelve() {
        return onShelve;
    }
}
