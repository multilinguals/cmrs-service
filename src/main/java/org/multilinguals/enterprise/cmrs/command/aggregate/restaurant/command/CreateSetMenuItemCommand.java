package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class CreateSetMenuItemCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private RestaurantId restaurantId;

    private MenuItemTypeId menuItemTypeId;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private List<MenuItemId> singleItemIdList;

    public CreateSetMenuItemCommand(RestaurantId restaurantId, MenuItemTypeId menuItemTypeId, @NotNull String name, @NotNull BigDecimal price, @NotNull List<MenuItemId> singleItemIdList) {
        this.restaurantId = restaurantId;
        this.menuItemTypeId = menuItemTypeId;
        this.name = name;
        this.price = price;
        this.singleItemIdList = singleItemIdList;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }

    public MenuItemTypeId getMenuItemTypeId() {
        return menuItemTypeId;
    }

    public void setMenuItemTypeId(MenuItemTypeId menuItemTypeId) {
        this.menuItemTypeId = menuItemTypeId;
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

    public List<MenuItemId> getSingleItemIdList() {
        return singleItemIdList;
    }

    public void setSingleItemIdList(List<MenuItemId> singleItemIdList) {
        this.singleItemIdList = singleItemIdList;
    }
}
