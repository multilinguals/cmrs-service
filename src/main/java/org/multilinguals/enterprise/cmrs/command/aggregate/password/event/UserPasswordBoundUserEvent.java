package org.multilinguals.enterprise.cmrs.command.aggregate.password.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class UserPasswordBoundUserEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private UserPasswordId userPasswordId;

    private UserId userId;

    public UserPasswordBoundUserEvent(UserPasswordId userPasswordId, UserId userId) {
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
