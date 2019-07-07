package org.multilinguals.enterprise.cmrs.command.aggregate.user.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class RemoveRoleFromUserCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private UserId userId;

    private String roleName;
}
