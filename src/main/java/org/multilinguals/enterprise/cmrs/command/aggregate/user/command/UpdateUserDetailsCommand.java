package org.multilinguals.enterprise.cmrs.command.aggregate.user.command;

import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotNull;

public class UpdateUserDetailsCommand extends AbstractCommand {
    @AggregateIdentifier
    @NotNull
    private UserId id;

    private String realName;

    public UpdateUserDetailsCommand(UserId id, String realName) {
        this.id = id;
        this.realName = realName;
    }

    public UserId getId() {
        return id;
    }

    public String getRealName() {
        return realName;
    }
}
