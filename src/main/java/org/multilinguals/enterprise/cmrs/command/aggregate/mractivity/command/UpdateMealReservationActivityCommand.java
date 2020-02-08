package org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UpdateMealReservationActivityCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private MealReservationActivityId id;

    private List<RestaurantId> restaurantIdList;

    @NotNull
    private UserId operatorId;

    public UpdateMealReservationActivityCommand(MealReservationActivityId id, List<RestaurantId> restaurantIdList, UserId operatorId) {
        this.id = id;
        this.restaurantIdList = restaurantIdList;
        this.operatorId = operatorId;
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
}
