package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;

public class MealReservationGroupDeletedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationGroupId id;

    public MealReservationGroupDeletedEvent(MealReservationGroupId id) {
        this.id = id;
    }

    public MealReservationGroupId getId() {
        return id;
    }
}
