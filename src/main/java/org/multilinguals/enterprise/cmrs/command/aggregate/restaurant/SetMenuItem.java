package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant;

import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;

import java.math.BigDecimal;
import java.util.Map;

public class SetMenuItem extends MenuItem {
    private BigDecimal totalPrice = BigDecimal.ZERO;

    private Map<MenuItemId, SingleMenuItem> singleMenuItems;

    public SetMenuItem(MenuItemId id, String name, MenuItemTypeId menuItemTypeId, BigDecimal price, Map<MenuItemId, SingleMenuItem> singleMenuItems) {
        super(id, name, menuItemTypeId, price);

        for (Map.Entry<MenuItemId, SingleMenuItem> item : singleMenuItems.entrySet()) {
            this.totalPrice = this.totalPrice.add(item.getValue().getPrice());
        }

        this.singleMenuItems = singleMenuItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Map<MenuItemId, SingleMenuItem> getSingleMenuItems() {
        return singleMenuItems;
    }
}
