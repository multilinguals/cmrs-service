package org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.event;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;

public class MenuItemTypeCreatedEvent extends AbstractEvent {
    @AggregateIdentifier
    private MenuItemTypeId id;

    private String name;

    public MenuItemTypeCreatedEvent(MenuItemTypeId id, String name) {
        this.id = id;
        this.name = name;
    }

    public MenuItemTypeId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
