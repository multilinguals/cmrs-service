package org.multilinguals.enterprise.cmrs.command.aggregate.usersession.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class CreateUserSessionCommand extends AbstractCommand {
    private UserId userId;

    public CreateUserSessionCommand(UserId userId) {
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }
}
