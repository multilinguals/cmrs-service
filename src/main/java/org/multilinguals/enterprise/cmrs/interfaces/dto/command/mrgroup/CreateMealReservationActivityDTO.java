package org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup;

import org.hibernate.validator.constraints.Length;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CreateMealReservationActivityDTO {
    @NotNull
    private MealReservationGroupId groupId;

    @NotEmpty
    private List<RestaurantId> restaurantIdList;

    @NotEmpty
    @Length(min = 10, max = 10)
    private String startedAt;

    @NotEmpty
    @Length(min = 10, max = 10)
    private String endAt;

    public CreateMealReservationActivityDTO() {
    }

    public MealReservationGroupId getGroupId() {
        return groupId;
    }

    public void setGroupId(MealReservationGroupId groupId) {
        this.groupId = groupId;
    }

    public List<RestaurantId> getRestaurantIdList() {
        return restaurantIdList;
    }

    public void setRestaurantIdList(List<RestaurantId> restaurantIdList) {
        this.restaurantIdList = restaurantIdList;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }
}
