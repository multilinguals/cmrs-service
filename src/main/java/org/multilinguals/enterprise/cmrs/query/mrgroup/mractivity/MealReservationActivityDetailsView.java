package org.multilinguals.enterprise.cmrs.query.mrgroup.mractivity;

import java.util.Date;
import java.util.List;

public class MealReservationActivityDetailsView {
    private String id;

    private String groupId;

    private List<ActivityRestaurantProfileView> restaurantProfileViews;

    private int status;

    private Date createdAt;

    private Date updatedAt;

    public MealReservationActivityDetailsView() {
    }

    public MealReservationActivityDetailsView(String id, String groupId, List<ActivityRestaurantProfileView> restaurantProfileViews, int status, Date createdAt) {
        this.id = id;
        this.groupId = groupId;
        this.restaurantProfileViews = restaurantProfileViews;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<ActivityRestaurantProfileView> getRestaurantProfileViews() {
        return restaurantProfileViews;
    }

    public void setRestaurantProfileViews(List<ActivityRestaurantProfileView> restaurantProfileViews) {
        this.restaurantProfileViews = restaurantProfileViews;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
