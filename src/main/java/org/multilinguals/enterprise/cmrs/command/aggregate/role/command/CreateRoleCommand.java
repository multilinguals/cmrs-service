package org.multilinguals.enterprise.cmrs.command.aggregate.role.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;

import javax.validation.constraints.NotNull;

public class CreateRoleCommand extends AbstractCommand {
    @NotNull
    private String name;

    public CreateRoleCommand(@NotNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
