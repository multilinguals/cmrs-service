package org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;

public class MealReservationActivityClosedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationActivityId id;

    public MealReservationActivityClosedEvent(MealReservationActivityId id) {
        this.id = id;
    }

    public MealReservationActivityId getId() {
        return id;
    }
}
