package org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup;

import org.hibernate.validator.constraints.Range;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;

import javax.validation.constraints.Future;
import java.util.Date;
import java.util.List;

public class UpdateMealReservationActivityDTO {
    private List<RestaurantId> restaurantIdList;

    private Date startedAt;

    @Future
    private Date endAt;

    public UpdateMealReservationActivityDTO() {
    }

    public List<RestaurantId> getRestaurantIdList() {
        return restaurantIdList;
    }

    public void setRestaurantIdList(List<RestaurantId> restaurantIdList) {
        this.restaurantIdList = restaurantIdList;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }
}
