package org.multilinguals.enterprise.cmrs.query.mrgroup;

import java.util.Date;

public class GroupOrderTakerView {
    private String id;

    private String mrGroupId;

    private String realName;

    private Date createdAt;

    private Date updatedAt;

    public GroupOrderTakerView(String id, String mrGroupId, String realName, Date createdAt) {
        this.id = id;
        this.mrGroupId = mrGroupId;
        this.realName = realName;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getMrGroupId() {
        return mrGroupId;
    }

    public void setMrGroupId(String mrGroupId) {
        this.mrGroupId = mrGroupId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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
