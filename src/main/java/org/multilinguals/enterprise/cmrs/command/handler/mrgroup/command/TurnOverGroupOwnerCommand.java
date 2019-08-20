package org.multilinguals.enterprise.cmrs.command.handler.mrgroup.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class TurnOverGroupOwnerCommand extends AbstractCommand {
    private UserId operatorId;

    private MealReservationGroupId groupId;

    private UserId targetUserId;

    public TurnOverGroupOwnerCommand(UserId operatorId, MealReservationGroupId groupId, UserId targetUserId) {
        this.operatorId = operatorId;
        this.groupId = groupId;
        this.targetUserId = targetUserId;
    }

    public UserId getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(UserId operatorId) {
        this.operatorId = operatorId;
    }

    public MealReservationGroupId getGroupId() {
        return groupId;
    }

    public void setGroupId(MealReservationGroupId groupId) {
        this.groupId = groupId;
    }

    public UserId getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(UserId targetUserId) {
        this.targetUserId = targetUserId;
    }
}