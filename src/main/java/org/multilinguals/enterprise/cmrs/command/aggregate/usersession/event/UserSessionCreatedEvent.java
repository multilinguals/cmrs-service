package org.multilinguals.enterprise.cmrs.command.aggregate.usersession.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.command.aggregate.usersession.UserSessionId;

import java.util.Date;

public class UserSessionCreatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private UserSessionId userSessionId;

    private UserId userId;

    private Date expiredAt;

    public UserSessionCreatedEvent(UserSessionId userSessionId, UserId userId, Date expiredAt) {
        this.userSessionId = userSessionId;
        this.userId = userId;
        this.expiredAt = expiredAt;
    }

    public UserSessionId getUserSessionId() {
        return userSessionId;
    }

    public UserId getUserId() {
        return userId;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }
}
