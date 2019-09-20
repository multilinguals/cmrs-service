package org.multilinguals.enterprise.cmrs.query.mrgroup.profile;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class UserMealReservationGroupProfileView {
    @Id
    private String memberId;

    private String groupId;

    private String name;

    private String ownerId;

    private String ownerRealName;

    private Date createdAt;

    private Date updatedAt;

    public UserMealReservationGroupProfileView() {
    }

    public UserMealReservationGroupProfileView(String memberId, String groupId, String name, String ownerId, String ownerRealName, Date createdAt) {
        this.memberId = memberId;
        this.groupId = groupId;
        this.name = name;
        this.ownerId = ownerId;
        this.ownerRealName = ownerRealName;
        this.createdAt = createdAt;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerRealName() {
        return ownerRealName;
    }

    public void setOwnerRealName(String ownerRealName) {
        this.ownerRealName = ownerRealName;
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
