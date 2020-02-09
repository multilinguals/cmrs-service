package org.multilinguals.enterprise.cmrs.command.aggregate.mrorder.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrorder.MealReservationOrderId;

public class MealReservationOrderCreatedEvent {
    @TargetAggregateIdentifier
    private MealReservationOrderId id;

    private MealReservationActivityId activityId;

    public MealReservationOrderCreatedEvent(MealReservationOrderId id, MealReservationActivityId activityId) {
        this.id = id;
        this.activityId = activityId;
    }

    public MealReservationOrderId getId() {
        return id;
    }

    public MealReservationActivityId getActivityId() {
        return activityId;
    }
}
