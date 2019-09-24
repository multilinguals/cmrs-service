package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class MealReservationGroupOwnerTurnOverEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationGroupId mealReservationGroupId;

    private UserId pastOwnerId;

    private UserId currentOwnerId;

    public MealReservationGroupOwnerTurnOverEvent(MealReservationGroupId mealReservationGroupId, UserId pastOwnerId, UserId currentOwnerId) {
        this.mealReservationGroupId = mealReservationGroupId;
        this.pastOwnerId = pastOwnerId;
        this.currentOwnerId = currentOwnerId;
    }

    public MealReservationGroupId getMealReservationGroupId() {
        return mealReservationGroupId;
    }

    public UserId getPastOwnerId() {
        return pastOwnerId;
    }

    public UserId getCurrentOwnerId() {
        return currentOwnerId;
    }
}
