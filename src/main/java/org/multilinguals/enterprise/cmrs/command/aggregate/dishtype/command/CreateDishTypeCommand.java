package org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;

public class CreateDishTypeCommand extends AbstractCommand {
    private String name;

    public CreateDishTypeCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
