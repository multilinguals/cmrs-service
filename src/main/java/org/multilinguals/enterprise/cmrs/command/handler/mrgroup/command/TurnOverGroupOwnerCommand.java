package org.multilinguals.enterprise.cmrs.command.handler.mrgroup.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class TurnOverGroupOwnerCommand extends AbstractCommand {
    private MealReservationGroupId groupId;

    private UserId groupAdminId;

    private UserId operatorId;

    public TurnOverGroupOwnerCommand(MealReservationGroupId groupId, UserId groupAdminId, UserId operatorId) {
        this.groupId = groupId;
        this.groupAdminId = groupAdminId;
        this.operatorId = operatorId;
    }

    public MealReservationGroupId getGroupId() {
        return groupId;
    }

    public UserId getGroupAdminId() {
        return groupAdminId;
    }

    public UserId getOperatorId() {
        return operatorId;
    }
}