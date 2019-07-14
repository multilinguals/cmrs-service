package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.DishTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.taste.TasteId;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class UpdateSingleMenuItemCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private RestaurantId restaurantId;

    @NotNull
    private MenuItemId id;

    private String name;

    private DishTypeId dishTypeId;

    private TasteId tasteId;

    private BigDecimal price;

    private Boolean onShelve;

    public UpdateSingleMenuItemCommand(RestaurantId restaurantId, @NotNull MenuItemId id, String name, DishTypeId dishTypeId, TasteId tasteId, BigDecimal price, Boolean onShelve) {
        this.restaurantId = restaurantId;
        this.id = id;
        this.name = name;
        this.dishTypeId = dishTypeId;
        this.tasteId = tasteId;
        this.price = price;
        this.onShelve = onShelve;
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

    public DishTypeId getDishTypeId() {
        return dishTypeId;
    }

    public void setDishTypeId(DishTypeId dishTypeId) {
        this.dishTypeId = dishTypeId;
    }

    public TasteId getTasteId() {
        return tasteId;
    }

    public void setTasteId(TasteId tasteId) {
        this.tasteId = tasteId;
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
}
