package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.GroupMember;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;

public class MealReservationGroupOwnerTurnOverEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationGroupId mealReservationGroupId;

    private GroupMember newGroupOwner;

    public MealReservationGroupOwnerTurnOverEvent(MealReservationGroupId mealReservationGroupId, GroupMember newGroupOwner) {
        this.mealReservationGroupId = mealReservationGroupId;
        this.newGroupOwner = newGroupOwner;
    }

    public MealReservationGroupId getMealReservationGroupId() {
        return mealReservationGroupId;
    }

    public GroupMember getNewGroupOwner() {
        return newGroupOwner;
    }
}
