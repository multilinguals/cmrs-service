package org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup;

import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class CreateMealReservationActivityDTO {
    @NotEmpty
    private List<RestaurantId> restaurantIdList;

    @NotNull
    private Date startedAt;

    @NotNull
    @Future
    private Date endAt;

    public CreateMealReservationActivityDTO() {
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
