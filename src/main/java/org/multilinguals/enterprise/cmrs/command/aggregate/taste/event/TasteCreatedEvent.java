package org.multilinguals.enterprise.cmrs.command.aggregate.taste.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.taste.TasteId;

public class TasteCreatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private TasteId id;

    private String name;

    public TasteCreatedEvent(TasteId id, String name) {
        this.id = id;
        this.name = name;
    }

    public TasteId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
