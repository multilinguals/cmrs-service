package org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;

import java.util.Date;
import java.util.List;

public class MealReservationActivityUpdatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationActivityId activityId;

    private List<RestaurantId> restaurantIdList;

    private Date startedAt;

    private Date endAt;

    public MealReservationActivityUpdatedEvent(MealReservationActivityId activityId, List<RestaurantId> restaurantIdList, Date startedAt, Date endAt) {
        this.activityId = activityId;
        this.restaurantIdList = restaurantIdList;
        this.startedAt = startedAt;
        this.endAt = endAt;
    }

    public MealReservationActivityId getActivityId() {
        return activityId;
    }

    public List<RestaurantId> getRestaurantIdList() {
        return restaurantIdList;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public Date getEndAt() {
        return endAt;
    }
}
