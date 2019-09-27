package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AddMembersToMealReservationGroupCommand extends AbstractCommand {
    @AggregateIdentifier
    private MealReservationGroupId groupId;

    @NotEmpty
    private List<UserId> newMemberIdList;

    @NotNull
    private UserId operatorId;

    public AddMembersToMealReservationGroupCommand(MealReservationGroupId groupId, @NotEmpty List<UserId> newMemberIdList, @NotNull UserId operatorId) {
        this.groupId = groupId;
        this.newMemberIdList = newMemberIdList;
        this.operatorId = operatorId;
    }

    public MealReservationGroupId getGroupId() {
        return groupId;
    }

    public void setGroupId(MealReservationGroupId groupId) {
        this.groupId = groupId;
    }

    public List<UserId> getNewMemberIdList() {
        return newMemberIdList;
    }

    public void setNewMemberIdList(List<UserId> newMemberIdList) {
        this.newMemberIdList = newMemberIdList;
    }

    public UserId getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(UserId operatorId) {
        this.operatorId = operatorId;
    }
}
