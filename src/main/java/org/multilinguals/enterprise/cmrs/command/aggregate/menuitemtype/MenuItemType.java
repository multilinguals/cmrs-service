package org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.command.CreateMenuItemTypeCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.event.MenuItemTypeCreatedEvent;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class MenuItemType {
    @AggregateIdentifier
    private MenuTypeItemId id;

    private String name;

    @CommandHandler
    public MenuItemType(CreateMenuItemTypeCommand command) {
        apply(new MenuItemTypeCreatedEvent(new MenuTypeItemId(), command.getName()));
    }

    @EventSourcingHandler
    public void on(MenuItemTypeCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
    }

    public MenuTypeItemId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
