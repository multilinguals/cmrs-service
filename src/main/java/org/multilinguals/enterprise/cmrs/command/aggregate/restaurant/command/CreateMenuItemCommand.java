package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.DishType;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemType;

public class CreateMenuItemCommand extends AbstractCommand {
    private String name;

    private MenuItemType type;

    private DishType dishType;


}
