package org.multilinguals.enterprise.cmrs.command.aggregate.usersession.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.usersession.UserSessionId;

public class DeleteUserSessionCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private UserSessionId userSessionId;

    public DeleteUserSessionCommand(UserSessionId userSessionId) {
        this.userSessionId = userSessionId;
    }

    public UserSessionId getUserSessionId() {
        return userSessionId;
    }
}
