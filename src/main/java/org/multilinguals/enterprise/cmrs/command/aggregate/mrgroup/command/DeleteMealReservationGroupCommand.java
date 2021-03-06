package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotNull;

public class DeleteMealReservationGroupCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private MealReservationGroupId groupId;

    @NotNull
    private UserId operatorId;

    public DeleteMealReservationGroupCommand(MealReservationGroupId groupId, UserId operatorId) {
        this.groupId = groupId;
        this.operatorId = operatorId;
    }

    public MealReservationGroupId getGroupId() {
        return groupId;
    }

    public void setGroupId(MealReservationGroupId groupId) {
        this.groupId = groupId;
    }

    public UserId getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(UserId operatorId) {
        this.operatorId = operatorId;
    }
}
