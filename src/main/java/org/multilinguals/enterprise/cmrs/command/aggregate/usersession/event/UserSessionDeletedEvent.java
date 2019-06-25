package org.multilinguals.enterprise.cmrs.command.aggregate.usersession.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.command.aggregate.usersession.UserSessionId;

public class UserSessionDeletedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private UserSessionId userSessionId;

    private UserId userId;

    public UserSessionDeletedEvent(UserSessionId userSessionId, UserId userId) {
        this.userSessionId = userSessionId;
        this.userId = userId;
    }

    public UserSessionId getUserSessionId() {
        return userSessionId;
    }

    public UserId getUserId() {
        return userId;
    }
}
