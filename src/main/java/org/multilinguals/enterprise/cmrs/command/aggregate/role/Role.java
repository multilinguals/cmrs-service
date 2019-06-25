package org.multilinguals.enterprise.cmrs.command.aggregate.role;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.command.CreateRoleCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.event.RoleCreatedEvent;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Role {
    @AggregateIdentifier
    private RoleId id;

    private String name;

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

    public RoleId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
