package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class AddMembersToMealReservationGroupCommand extends AbstractCommand {
    @AggregateIdentifier
    private MealReservationGroupId groupId;

    @NotEmpty
    private Map<UserId, String> newMembers;

    @NotNull
    private UserId operatorId;

    public AddMembersToMealReservationGroupCommand(MealReservationGroupId groupId, Map<UserId, String> newMembers, UserId operatorId) {
        this.groupId = groupId;
        this.newMembers = newMembers;
        this.operatorId = operatorId;
    }

    public MealReservationGroupId getGroupId() {
        return groupId;
    }

    public void setGroupId(MealReservationGroupId groupId) {
        this.groupId = groupId;
    }

    public Map<UserId, String> getNewMembers() {
        return newMembers;
    }

    public void setNewMembers(Map<UserId, String> newMembers) {
        this.newMembers = newMembers;
    }

    public UserId getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(UserId operatorId) {
        this.operatorId = operatorId;
    }
}
