package org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;

public class MealReservationActivityStartedEvent {
    @TargetAggregateIdentifier
    private MealReservationActivityId id;

    public MealReservationActivityStartedEvent(MealReservationActivityId id) {
        this.id = id;
    }

    public MealReservationActivityId getId() {
        return id;
    }
}
