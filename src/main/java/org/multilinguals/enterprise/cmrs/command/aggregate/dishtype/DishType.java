package org.multilinguals.enterprise.cmrs.command.aggregate.dishtype;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.command.CreateDishTypeCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.event.DishTypeCreatedEvent;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class DishType {
    @AggregateIdentifier
    private DishTypeId id;

    private String name;

    public DishType() {
    }

    @CommandHandler
    public DishType(CreateDishTypeCommand command) {
        apply(new DishTypeCreatedEvent(new DishTypeId(), command.getName()));
    }

    @EventSourcingHandler
    public void on(DishTypeCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
    }

    public DishTypeId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
