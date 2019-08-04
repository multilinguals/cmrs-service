package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.SetSubItem;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class SetMenuItemUpdatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private RestaurantId restaurantId;

    @NotNull
    private MenuItemId id;

    private String name;

    private BigDecimal price;

    private Boolean onShelve;

    private List<SetSubItem> subItems;

    public SetMenuItemUpdatedEvent(RestaurantId restaurantId, @NotNull MenuItemId id, String name, BigDecimal price, Boolean onShelve, List<SetSubItem> subItems) {
        this.restaurantId = restaurantId;
        this.id = id;
        this.name = name;
        this.price = price;
        this.onShelve = onShelve;
        this.subItems = subItems;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }

    public MenuItemId getId() {
        return id;
    }

    public void setId(MenuItemId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<SetSubItem> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<SetSubItem> subItems) {
        this.subItems = subItems;
    }
}
