package org.multilinguals.enterprise.cmrs.query.mrgroup.member;

import org.multilinguals.enterprise.cmrs.infrastructure.i18n.I18Translator;
import org.multilinguals.enterprise.cmrs.infrastructure.persistence.Localizable;

import java.util.Date;

public class GroupMemberView implements Localizable {
    private String id;

    private String mrGroupId;

    private String realName;

    private String groupRole;

    private String groupRoleLocalName;

    private Date createdAt;

    private Date updatedAt;


    public GroupMemberView(String id, String mrGroupId, String realName, String groupRole, Date createdAt) {
        this.id = id;
        this.mrGroupId = mrGroupId;
        this.realName = realName;
        this.groupRole = groupRole;
        this.createdAt = createdAt;
    }

    @Override
    public void localize(I18Translator i18Translator) {
        this.groupRoleLocalName = i18Translator.localize("GROUP_ROLE_" + this.groupRole);
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

    public String getGroupRole() {
        return groupRole;
    }

    public void setGroupRole(String groupRole) {
        this.groupRole = groupRole;
    }

    public String getGroupRoleLocalName() {
        return groupRoleLocalName;
    }

    public void setGroupRoleLocalName(String groupRoleLocalName) {
        this.groupRoleLocalName = groupRoleLocalName;
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
