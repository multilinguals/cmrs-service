package org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup;

import org.hibernate.validator.constraints.Range;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;

import java.util.List;

public class UpdateMealReservationActivityDTO {
    private List<RestaurantId> restaurantIdList;

    @Range(min = 1000000000L, max = 9999999999L)
    private Long startedAt;

    @Range(min = 1000000000L, max = 9999999999L)
    private Long endAt;

    public UpdateMealReservationActivityDTO() {
    }

    public List<RestaurantId> getRestaurantIdList() {
        return restaurantIdList;
    }

    public void setRestaurantIdList(List<RestaurantId> restaurantIdList) {
        this.restaurantIdList = restaurantIdList;
    }

    public Long getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Long startedAt) {
        this.startedAt = startedAt;
    }

    public Long getEndAt() {
        return endAt;
    }

    public void setEndAt(Long endAt) {
        this.endAt = endAt;
    }
}
