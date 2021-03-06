package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AddMembersToMealReservationGroupCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private MealReservationGroupId groupId;

    @NotEmpty
    private List<UserId> memberIdList;

    @NotNull
    private UserId operatorId;

    public AddMembersToMealReservationGroupCommand(MealReservationGroupId groupId, @NotEmpty List<UserId> memberIdList, @NotNull UserId operatorId) {
        this.groupId = groupId;
        this.memberIdList = memberIdList;
        this.operatorId = operatorId;
    }

    public MealReservationGroupId getGroupId() {
        return groupId;
    }

    public void setGroupId(MealReservationGroupId groupId) {
        this.groupId = groupId;
    }

    public List<UserId> getMemberIdList() {
        return memberIdList;
    }

    public void setMemberIdList(List<UserId> memberIdList) {
        this.memberIdList = memberIdList;
    }

    public UserId getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(UserId operatorId) {
        this.operatorId = operatorId;
    }
}
