package org.multilinguals.enterprise.cmrs.command.aggregate.taste.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;

public class CreateTasteCommand extends AbstractCommand {
    private String name;

    public CreateTasteCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
