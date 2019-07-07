package org.multilinguals.enterprise.cmrs.command.aggregate.user.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class BindRoleToUserCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private UserId userId;

    private RoleId roleId;

    public BindRoleToUserCommand(UserId userId, RoleId roleId) {
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
