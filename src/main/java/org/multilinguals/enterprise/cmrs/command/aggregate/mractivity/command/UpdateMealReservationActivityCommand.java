package org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class UpdateMealReservationActivityCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private MealReservationActivityId id;

    @NotNull
    private MealReservationGroupId groupId;

    private List<RestaurantId> restaurantIdList;

    @NotNull
    private UserId operatorId;

    private Date startedAt;

    private Date endAt;

    public UpdateMealReservationActivityCommand(MealReservationActivityId id, MealReservationGroupId groupId, List<RestaurantId> restaurantIdList, UserId operatorId, Date startedAt, Date endAt) {
        this.id = id;
        this.groupId = groupId;
        this.restaurantIdList = restaurantIdList;
        this.operatorId = operatorId;
        this.startedAt = startedAt;
        this.endAt = endAt;
    }

    public MealReservationActivityId getId() {
        return id;
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
