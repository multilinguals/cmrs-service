package org.multilinguals.enterprise.cmrs.command.aggregate.role.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;

public class RoleCreatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private RoleId id;

    private String name;

    public RoleCreatedEvent(RoleId id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
