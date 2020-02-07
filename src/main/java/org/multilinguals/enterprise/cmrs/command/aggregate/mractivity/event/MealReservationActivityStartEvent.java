package org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;

public class MealReservationActivityStartEvent {
    @TargetAggregateIdentifier
    private MealReservationActivityId activityId;

    public MealReservationActivityStartEvent(MealReservationActivityId activityId) {
        this.activityId = activityId;
    }

    public MealReservationActivityId getActivityId() {
        return activityId;
    }
}
