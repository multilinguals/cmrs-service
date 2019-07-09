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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getCreatorRealName() {
        return creatorRealName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}