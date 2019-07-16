package org.multilinguals.enterprise.cmrs.query.menuitem;

import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.SingleMenuItem;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class SetMenuItemView {
    @Id
    private String id;

    private String name;

    private String menuItemTypeId;

    private String menuItemTypeName;

    private BigDecimal price;

    private Boolean onShelve;

    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Transient
    private Map<MenuItemId, SingleMenuItem> singleMenuItems;

    private Date createdAt;

    private Date updatedAt;
}
