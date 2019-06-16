package org.multilinguals.enterprise.cmrs.command.aggregate.permission;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.command.aggregate.permission.command.CreatePermissionCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.permission.event.PermissionCreatedEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Permission {
    @AggregateIdentifier
    private PermissionId id;

    private String name;

    private String action;

    protected Permission() {
    }

    @CommandHandler
    public Permission(CreatePermissionCommand command) {
        apply(new PermissionCreatedEvent(new PermissionId(), command.getName(), command.getAction()));
    }

    @EventSourcingHandler
    public void on(PermissionCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.action = event.getAction();
    }
}
