package org.multilinguals.enterprise.cmrs.command.aggregate.taste;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.command.aggregate.taste.command.CreateTasteCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.taste.event.TasteCreatedEvent;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Taste {
    @AggregateIdentifier
    private TasteId id;

    private String name;

    @CommandHandler
    public Taste(CreateTasteCommand command) {
        apply(new TasteCreatedEvent(new TasteId(), command.getName()));
    }

    @EventSourcingHandler
    public void on(TasteCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
    }

    public TasteId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
