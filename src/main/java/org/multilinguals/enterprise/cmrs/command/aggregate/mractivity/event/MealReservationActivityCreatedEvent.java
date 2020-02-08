package org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.constant.aggregate.mrgroup.MealReservationActivityStatus;

import java.util.List;

public class MealReservationActivityCreatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationActivityId id;

    private MealReservationGroupId groupId;

    private List<RestaurantId> restaurantIdList;

    private MealReservationActivityStatus status;

    public MealReservationActivityCreatedEvent(MealReservationActivityId id, MealReservationGroupId groupId, List<RestaurantId> restaurantIdList, MealReservationActivityStatus status) {
        this.id = id;
        this.groupId = groupId;
        this.restaurantIdList = restaurantIdList;
        this.status = status;
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

    public MealReservationActivityStatus getStatus() {
        return status;
    }
}
