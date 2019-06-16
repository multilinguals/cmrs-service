package org.multilinguals.enterprise.cmrs.command.aggregate.password.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class BindUserToUserPasswordCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private UserPasswordId userPasswordId;

    private UserId userId;

    public BindUserToUserPasswordCommand(UserId userId, UserPasswordId userPasswordId) {
        this.userPasswordId = userPasswordId;
        this.userId = userId;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }

    public UserId getUserId() {
        return userId;
    }
}
