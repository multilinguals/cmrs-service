package org.multilinguals.enterprise.cmrs.command.aggregate.user.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class RoleBoundToUserEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private UserId userId;

    private RoleId roleId;

    public RoleBoundToUserEvent(UserId userId, RoleId roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserId getUserId() {
        return userId;
    }

    public RoleId getRoleId() {
        return roleId;
    }
}
