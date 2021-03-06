package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant;

import org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.DishTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.taste.TasteId;

import java.math.BigDecimal;

public class SingleMenuItem extends MenuItem {
    private DishTypeId dishTypeId;

    private TasteId tasteId;

    public SingleMenuItem(MenuItemId id, String name, MenuItemTypeId menuItemTypeId, BigDecimal price, Boolean onShelve, DishTypeId dishTypeId, TasteId tasteId) {
        super(id, name, menuItemTypeId, price, onShelve);
        this.dishTypeId = dishTypeId;
        this.tasteId = tasteId;
    }

    public DishTypeId getDishTypeId() {
        return dishTypeId;
    }

    public TasteId getTasteId() {
        return tasteId;
    }

    public void setDishTypeId(DishTypeId dishTypeId) {
        this.dishTypeId = dishTypeId;
    }

    public void setTasteId(TasteId tasteId) {
        this.tasteId = tasteId;
    }
}