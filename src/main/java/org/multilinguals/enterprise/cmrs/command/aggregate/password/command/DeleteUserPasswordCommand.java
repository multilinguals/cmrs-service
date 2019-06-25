package org.multilinguals.enterprise.cmrs.command.aggregate.password.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;

public class DeleteUserPasswordCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private UserPasswordId userPasswordId;

    public DeleteUserPasswordCommand(UserPasswordId userPasswordId) {
        this.userPasswordId = userPasswordId;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }
}
