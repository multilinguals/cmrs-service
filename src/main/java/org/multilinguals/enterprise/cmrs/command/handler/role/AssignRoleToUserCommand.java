package org.multilinguals.enterprise.cmrs.command.handler.role;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class AssignRoleToUserCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private UserId userId;

    private String roleName;

    public AssignRoleToUserCommand(UserId userId, String roleName) {
        this.userId = userId;
        this.roleName = roleName;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getRoleName() {
        return roleName;
    }
}
