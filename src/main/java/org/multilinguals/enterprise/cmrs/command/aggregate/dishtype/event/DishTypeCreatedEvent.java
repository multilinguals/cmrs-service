package org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.DishTypeId;

public class DishTypeCreatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private DishTypeId id;

    private String name;

    public DishTypeCreatedEvent(DishTypeId id, String name) {
        this.id = id;
        this.name = name;
    }

    public DishTypeId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
