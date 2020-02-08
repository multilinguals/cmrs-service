package org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;

import java.util.List;

public class MealReservationActivityUpdatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationActivityId id;

    private List<RestaurantId> restaurantIdList;

    public MealReservationActivityUpdatedEvent(MealReservationActivityId id, List<RestaurantId> restaurantIdList) {
        this.id = id;
        this.restaurantIdList = restaurantIdList;
    }

    public MealReservationActivityId getId() {
        return id;
    }

    public List<RestaurantId> getRestaurantIdList() {
        return restaurantIdList;
    }
}
