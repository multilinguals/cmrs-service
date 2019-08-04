package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.SetSubItemId;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class RemoveItemsFromMenuItemCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private RestaurantId restaurantId;

    @NotNull
    private MenuItemId setMenuItemId;

    private BigDecimal price;

    private List<SetSubItemId> subItemIdList;

    public RemoveItemsFromMenuItemCommand(RestaurantId restaurantId, @NotNull MenuItemId setMenuItemId, BigDecimal price, List<SetSubItemId> subItemIdList) {
        this.restaurantId = restaurantId;
        this.setMenuItemId = setMenuItemId;
        this.price = price;
        this.subItemIdList = subItemIdList;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<SetSubItemId> getSubItemIdList() {
        return subItemIdList;
    }

    public void setSubItemIdList(List<SetSubItemId> subItemIdList) {
        this.subItemIdList = subItemIdList;
    }
}
