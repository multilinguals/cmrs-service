package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.DishTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.taste.TasteId;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateSingleMenuItemCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private RestaurantId restaurantId;

    @NotNull
    private String name;

    @NotNull
    private MenuItemTypeId menuItemTypeId;

    @NotNull
    private DishTypeId dishTypeId;

    private TasteId tasteId;

    @NotNull
    private BigDecimal price;

    public CreateSingleMenuItemCommand(RestaurantId restaurantId, @NotNull String name, MenuItemTypeId menuItemTypeId, @NotNull DishTypeId dishTypeId, TasteId tasteId, @NotNull BigDecimal price) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.menuItemTypeId = menuItemTypeId;
        this.dishTypeId = dishTypeId;
        this.tasteId = tasteId;
        this.price = price;
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

    public void setMenuItemTypeId(MenuItemTypeId menuItemTypeId) {
        this.menuItemTypeId = menuItemTypeId;
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
