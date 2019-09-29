package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.GroupMemberId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class MembersRemovedFromMealReservationGroupEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationGroupId groupId;

    @NotEmpty
    private List<GroupMemberId> removedMemberIdList;

    public MembersRemovedFromMealReservationGroupEvent(MealReservationGroupId groupId, @NotEmpty List<GroupMemberId> removedMemberIdList) {
        this.groupId = groupId;
        this.removedMemberIdList = removedMemberIdList;
    }

    public MealReservationGroupId getGroupId() {
        return groupId;
    }

    public List<GroupMemberId> getRemovedMemberIdList() {
        return removedMemberIdList;
    }
}
