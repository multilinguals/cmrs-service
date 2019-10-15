package org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class CreateMealReservationActivityCommand extends AbstractCommand {
    @NotNull
    private MealReservationGroupId groupId;

    @NotEmpty
    private List<RestaurantId> restaurantIdList;

    @NotNull
    private UserId operatorId;

    @NotNull
    private Date startedAt;

    @NotNull
    private Date endAt;

    public CreateMealReservationActivityCommand(@NotNull MealReservationGroupId groupId, @NotEmpty List<RestaurantId> restaurantIdList, @NotNull UserId operatorId, @NotNull Date startedAt, @NotNull Date endAt) {
        this.groupId = groupId;
        this.restaurantIdList = restaurantIdList;
        this.operatorId = operatorId;
        this.startedAt = startedAt;
        this.endAt = endAt;
    }

    public MealReservationGroupId getGroupId() {
        return groupId;
    }

    public List<RestaurantId> getRestaurantIdList() {
        return restaurantIdList;
    }

    public UserId getOperatorId() {
        return operatorId;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public Date getEndAt() {
        return endAt;
    }
}
