package org.multilinguals.enterprise.cmrs.command.aggregate.user.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import java.util.List;

public class SetRolesToUserCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private UserId userId;

    private List<RoleId> roleIdList;

    public SetRolesToUserCommand(UserId userId, List<RoleId> roleIdList) {
        this.userId = userId;
        this.roleIdList = roleIdList;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public List<RoleId> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<RoleId> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
