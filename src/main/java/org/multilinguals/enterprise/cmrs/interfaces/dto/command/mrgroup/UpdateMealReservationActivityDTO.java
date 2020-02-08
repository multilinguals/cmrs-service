package org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup;

import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;

import java.util.List;

public class UpdateMealReservationActivityDTO {
    private List<RestaurantId> restaurantIdList;

    public UpdateMealReservationActivityDTO() {
    }

    public List<RestaurantId> getRestaurantIdList() {
        return restaurantIdList;
    }

    public void setRestaurantIdList(List<RestaurantId> restaurantIdList) {
        this.restaurantIdList = restaurantIdList;
    }
}