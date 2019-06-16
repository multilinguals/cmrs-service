package org.multilinguals.enterprise.cmrs.command.aggregate.permission.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;

import javax.validation.constraints.NotNull;

public class CreatePermissionCommand extends AbstractCommand {
    @NotNull
    private String name;

    @NotNull
    private String action;

    public CreatePermissionCommand(@NotNull String name, @NotNull String action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public String getAction() {
        return action;
    }
}
