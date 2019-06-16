package org.multilinguals.enterprise.cmrs.command.aggregate.permission.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.permission.PermissionId;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;

import javax.validation.constraints.NotNull;

public class AssociatePermissionToRoleCommand extends AbstractCommand {
    @NotNull
    private RoleId roleId;

    @NotNull
    private PermissionId permissionId;

    public AssociatePermissionToRoleCommand(@NotNull RoleId roleId, @NotNull PermissionId permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public RoleId getRoleId() {
        return roleId;
    }

    public PermissionId getPermissionId() {
        return permissionId;
    }
}
