package org.multilinguals.enterprise.cmrs.query.mrgroup.mractivity.order;

import java.math.BigDecimal;
import java.util.Date;

public class MealReservationOderDetailsView {
    private String id;

    private String activityId;

    private BigDecimal totalPrice;

    private Date createdAt;

    private Date updatedAt;

    public MealReservationOderDetailsView(String id, String activityId, BigDecimal totalPrice) {
        this.id = id;
        this.activityId = activityId;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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
