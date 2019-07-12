package org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;

public class CreateMenuItemTypeCommand extends AbstractCommand {
    private String name;

    public CreateMenuItemTypeCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
