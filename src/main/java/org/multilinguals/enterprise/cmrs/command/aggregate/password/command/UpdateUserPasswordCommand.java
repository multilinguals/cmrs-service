package org.multilinguals.enterprise.cmrs.command.aggregate.password.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotNull;

public class UpdateUserPasswordCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private UserPasswordId userPasswordId;

    @NotNull
    private UserId userId;

    private String newUserPassword;

    public UpdateUserPasswordCommand(UserPasswordId userPasswordId, @NotNull UserId userId, String newUserPassword) {
        this.userPasswordId = userPasswordId;
        this.userId = userId;
        this.newUserPassword = newUserPassword;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }

    public void setUserPasswordId(UserPasswordId userPasswordId) {
        this.userPasswordId = userPasswordId;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public String getNewUserPassword() {
        return newUserPassword;
    }

    public void setNewUserPassword(String newUserPassword) {
        this.newUserPassword = newUserPassword;
    }
}
