package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.DishTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.taste.TasteId;

import java.math.BigDecimal;

public class SingleMenuItemUpdatedEvent {
    @TargetAggregateIdentifier
    private MenuItemId id;

    private String name;

    private DishTypeId dishTypeId;

    private TasteId tasteId;

    private BigDecimal price;

    private Boolean onShelve;

    public SingleMenuItemUpdatedEvent(MenuItemId id, String name, DishTypeId dishTypeId, TasteId tasteId, BigDecimal price, Boolean onShelve) {
        this.id = id;
        this.name = name;
        this.dishTypeId = dishTypeId;
        this.tasteId = tasteId;
        this.price = price;
        this.onShelve = onShelve;
    }

    public MenuItemId getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public Boolean getOnShelve() {
        return onShelve;
    }
}
