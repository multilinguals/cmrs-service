package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant;

import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;

import java.math.BigDecimal;
import java.util.List;

public class SetMenuItem extends MenuItem {
    private List<SetSubItem> subItems;

    public SetMenuItem(MenuItemId id, String name, MenuItemTypeId menuItemTypeId, BigDecimal price, Boolean onShelve, List<SetSubItem> subItems) {
        super(id, name, menuItemTypeId, price, onShelve);
        this.subItems = subItems;
    }

    public List<SetSubItem> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<SetSubItem> subItems) {
        this.subItems = subItems;
    }


    public void addSubItem(SetSubItem subItem) {
        if (!this.subItems.contains(subItem)) {
            this.subItems.add(subItem);
        }
    }

    public void removeSubItem(SetSubItemId subItemId) {
        for (SetSubItem subItem : this.getSubItems()) {
            if (subItemId.equals(subItem.getId())) {
                this.subItems.remove(subItem);
            }
        }
    }
}
