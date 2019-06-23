package org.multilinguals.enterprise.cmrs.command.handler.password;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class UpdateUserPasswordCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private UserPasswordId id;

    private UserId userId;

    private String newUserPassword;

    public UpdateUserPasswordCommand(UserPasswordId id, UserId userId, String newUserPassword) {
        this.id = id;
        this.userId = userId;
        this.newUserPassword = newUserPassword;
    }

    public UserPasswordId getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getNewUserPassword() {
        return newUserPassword;
    }
}
