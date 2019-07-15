package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant;

import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;

import java.math.BigDecimal;
import java.util.Map;

public class SetMenuItem extends MenuItem {
    private BigDecimal totalPrice = BigDecimal.ZERO;

    private Map<MenuItemId, SingleMenuItem> singleMenuItems;

    public SetMenuItem(MenuItemId id, String name, MenuItemTypeId menuItemTypeId, BigDecimal price, BigDecimal totalPrice, Map<MenuItemId, SingleMenuItem> singleMenuItems) {
        super(id, name, menuItemTypeId, price);
        this.totalPrice = totalPrice;
        this.singleMenuItems = singleMenuItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Map<MenuItemId, SingleMenuItem> getSingleMenuItems() {
        return singleMenuItems;
    }
}
