package org.multilinguals.enterprise.cmrs.command.aggregate.permission.event;

import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.permission.PermissionId;

public class PermissionCreatedEvent extends AbstractEvent {
    private PermissionId id;

    private String name;


    private String action;

    public PermissionCreatedEvent(PermissionId id, String name, String action) {
        this.id = id;
        this.name = name;
        this.action = action;
    }

    public PermissionId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAction() {
        return action;
    }
}
