package org.multilinguals.enterprise.cmrs.command.aggregate.user.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class BindRoleToUserCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private UserId userId;

    private RoleId roleId;

    private String roleName;

    public BindRoleToUserCommand(UserId userId, RoleId roleId, String roleName) {
        this.userId = userId;
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public UserId getUserId() {
        return userId;
    }

    public RoleId getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
