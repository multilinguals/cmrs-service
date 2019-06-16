package org.multilinguals.enterprise.cmrs.command.aggregate.role;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.command.aggregate.permission.PermissionId;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.command.CreateRoleCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.event.RoleCreatedEvent;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Role {
    @AggregateIdentifier
    private RoleId id;

    private String name;

    private List<PermissionId> permissionIdList = new ArrayList<>();

    protected Role() {
    }

    @CommandHandler
    public Role(CreateRoleCommand command) {
        apply(new RoleCreatedEvent(new RoleId(command.getName()), command.getName()));
    }

    @EventSourcingHandler
    public void on(RoleCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
    }
}
