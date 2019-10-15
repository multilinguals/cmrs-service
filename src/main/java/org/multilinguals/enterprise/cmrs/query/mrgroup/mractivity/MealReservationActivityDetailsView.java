package org.multilinguals.enterprise.cmrs.query.mrgroup.mractivity;

import java.util.Date;

public class MealReservationActivityDetailsView {
    private String id;

    private String groupId;

    private int status;

    private Date startedAt;

    private Date endAt;

    private Date createdAt;

    private Date updatedAt;

    public MealReservationActivityDetailsView() {
    }

    public MealReservationActivityDetailsView(String id, String groupId, int status, Date startedAt, Date endAt, Date createdAt) {
        this.id = id;
        this.groupId = groupId;
        this.status = status;
        this.startedAt = startedAt;
        this.endAt = endAt;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
