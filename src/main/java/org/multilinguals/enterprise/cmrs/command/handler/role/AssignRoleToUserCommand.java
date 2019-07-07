package org.multilinguals.enterprise.cmrs.command.handler.role;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotNull;

public class AssignRoleToUserCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private UserId userId;

    @NotNull
    private RoleId roleId;

    public AssignRoleToUserCommand(UserId userId, @NotNull RoleId roleId) {
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
