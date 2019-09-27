package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.GroupMember;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

public class MembersAddedToMealReservationGroupEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationGroupId groupId;

    @NotEmpty
    private Map<UserId, GroupMember> newMembers;

    public MembersAddedToMealReservationGroupEvent(MealReservationGroupId groupId, @NotEmpty Map<UserId, GroupMember> newMembers) {
        this.groupId = groupId;
        this.newMembers = newMembers;
    }

    public MealReservationGroupId getGroupId() {
        return groupId;
    }

    public Map<UserId, GroupMember> getNewMembers() {
        return newMembers;
    }
}
