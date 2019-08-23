package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;

public class MealReservationGroupDetailsUpdatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationGroupId mealReservationGroupId;

    private String name;

    private String description;

    public MealReservationGroupDetailsUpdatedEvent(MealReservationGroupId mealReservationGroupId, String name, String description) {
        this.mealReservationGroupId = mealReservationGroupId;
        this.name = name;
        this.description = description;
    }

    public MealReservationGroupId getMealReservationGroupId() {
        return mealReservationGroupId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
