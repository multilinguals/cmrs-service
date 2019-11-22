package org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class UpdateMealReservationActivityCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private MealReservationActivityId id;

    private List<RestaurantId> restaurantIdList;

    @NotNull
    private UserId operatorId;

    private Date startedAt;

    @Future
    private Date endAt;

    public UpdateMealReservationActivityCommand(MealReservationActivityId id, List<RestaurantId> restaurantIdList, UserId operatorId, Date startedAt, Date endAt) {
        this.id = id;
        this.restaurantIdList = restaurantIdList;
        this.operatorId = operatorId;
        this.startedAt = startedAt;
        this.endAt = endAt;
    }

    public MealReservationActivityId getId() {
        return id;
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
