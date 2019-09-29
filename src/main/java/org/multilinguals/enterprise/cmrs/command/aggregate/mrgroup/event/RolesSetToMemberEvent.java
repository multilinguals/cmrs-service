package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.GroupMemberId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;

import java.util.List;

public class RolesSetToMemberEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationGroupId mealReservationGroupId;

    private GroupMemberId memberId;

    private List<String> groupRoles;

    public RolesSetToMemberEvent(MealReservationGroupId mealReservationGroupId, GroupMemberId memberId, List<String> groupRoles) {
        this.mealReservationGroupId = mealReservationGroupId;
        this.memberId = memberId;
        this.groupRoles = groupRoles;
    }

    public MealReservationGroupId getMealReservationGroupId() {
        return mealReservationGroupId;
    }

    public GroupMemberId getMemberId() {
        return memberId;
    }

    public List<String> getGroupRoles() {
        return groupRoles;
    }
}
