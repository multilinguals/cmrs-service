package org.multilinguals.enterprise.cmrs.command.aggregate.password.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;

public class UserPasswordUpdatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private UserPasswordId id;

    private String hashValue;

    public UserPasswordUpdatedEvent(UserPasswordId id, String hashValue) {
        this.id = id;
        this.hashValue = hashValue;
    }

    public UserPasswordId getId() {
        return id;
    }

    public String getHashValue() {
        return hashValue;
    }
}
