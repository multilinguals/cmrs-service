package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant;

import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;

import java.math.BigDecimal;
import java.util.List;

public class SetMenuItem extends MenuItem {
    private List<MenuItemId> singleMenuItemList;

    public SetMenuItem(MenuItemId id, String name, MenuItemTypeId menuItemTypeId, BigDecimal price, Boolean onShelve, List<MenuItemId> singleMenuItemList) {
        super(id, name, menuItemTypeId, price, onShelve);
        this.singleMenuItemList = singleMenuItemList;
    }

    public List<MenuItemId> getSingleMenuItemList() {
        return singleMenuItemList;
    }
}
