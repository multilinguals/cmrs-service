package org.multilinguals.enterprise.cmrs.query.restaurant;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class RestaurantDetailsView {
    @Id
    private String id;

    private String name;

    private String description;

    private String creatorId;

    private String creatorRealName;

    private Date createdAt;

    private Date updatedAt;

    public RestaurantDetailsView(String id, String name, String description, String creatorId, String creatorRealName, Date createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.creatorRealName = creatorRealName;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorRealName() {
        return creatorRealName;
    }

    public void setCreatorRealName(String creatorRealName) {
        this.creatorRealName = creatorRealName;
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