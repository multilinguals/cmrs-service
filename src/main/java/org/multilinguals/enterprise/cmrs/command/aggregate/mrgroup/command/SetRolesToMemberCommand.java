package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.GroupMemberId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import java.util.List;

public class SetRolesToMemberCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private MealReservationGroupId groupId;

    private GroupMemberId memberId;

    private List<String> roleList;

    private UserId OperatorId;

    public SetRolesToMemberCommand(MealReservationGroupId groupId, GroupMemberId memberId, List<String> roleList, UserId operatorId) {
        this.groupId = groupId;
        this.memberId = memberId;
        this.roleList = roleList;
        OperatorId = operatorId;
    }

    public MealReservationGroupId getGroupId() {
        return groupId;
    }

    public GroupMemberId getMemberId() {
        return memberId;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public UserId getOperatorId() {
        return OperatorId;
    }
}
