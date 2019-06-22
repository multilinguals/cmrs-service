package org.multilinguals.enterprise.cmrs.command.aggregate.user.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class UserDetailsUpdatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private UserId id;

    private String realName;

    public UserDetailsUpdatedEvent(UserId id, String realName) {
        this.id = id;
        this.realName = realName;
    }

    public UserId getId() {
        return id;
    }

    public String getRealName() {
        return realName;
    }
}
