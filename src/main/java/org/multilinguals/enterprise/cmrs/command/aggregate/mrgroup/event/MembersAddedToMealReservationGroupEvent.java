package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.GroupMember;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.GroupMemberId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

public class MembersAddedToMealReservationGroupEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationGroupId groupId;

    @NotEmpty
    private Map<GroupMemberId, GroupMember> newMembers;

    public MembersAddedToMealReservationGroupEvent(MealReservationGroupId groupId, @NotEmpty Map<GroupMemberId, GroupMember> newMembers) {
        this.groupId = groupId;
        this.newMembers = newMembers;
    }

    public MealReservationGroupId getGroupId() {
        return groupId;
    }

    public Map<GroupMemberId, GroupMember> getNewMembers() {
        return newMembers;
    }
}
