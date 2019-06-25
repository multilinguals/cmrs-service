package org.multilinguals.enterprise.cmrs.command.aggregate.usersession.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.usersession.UserSessionId;

public class DeleteUserSessionCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private UserSessionId id;

    public DeleteUserSessionCommand(UserSessionId id) {
        this.id = id;
    }

    public UserSessionId getId() {
        return id;
    }
}
