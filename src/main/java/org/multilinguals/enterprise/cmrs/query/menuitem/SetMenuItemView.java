package org.multilinguals.enterprise.cmrs.query.menuitem;

import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.SingleMenuItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class SetMenuItemView {
    private String id;

    private String name;

    private String menuItemTypeId;

    private String menuItemTypeName;

    private BigDecimal price;

    private Boolean onShelve;

    private BigDecimal totalPrice = BigDecimal.ZERO;

    private Map<MenuItemId, SingleMenuItem> singleMenuItems;

    private Date createdAt;

    private Date updatedAt;
}
