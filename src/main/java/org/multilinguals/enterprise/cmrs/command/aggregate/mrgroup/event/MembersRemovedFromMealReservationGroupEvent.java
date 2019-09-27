package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class MembersRemovedFromMealReservationGroupEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationGroupId groupId;

    @NotEmpty
    private List<UserId> removedMemberIdList;

    public MembersRemovedFromMealReservationGroupEvent(MealReservationGroupId groupId, @NotEmpty List<UserId> removedMemberIdList) {
        this.groupId = groupId;
        this.removedMemberIdList = removedMemberIdList;
    }

    public MealReservationGroupId getGroupId() {
        return groupId;
    }

    public List<UserId> getRemovedMemberIdList() {
        return removedMemberIdList;
    }
}
