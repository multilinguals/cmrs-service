package org.multilinguals.enterprise.cmrs.query.mrgroup.member;

import org.multilinguals.enterprise.cmrs.infrastructure.i18n.I18Translator;
import org.multilinguals.enterprise.cmrs.infrastructure.persistence.Localizable;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupMemberView implements Localizable {
    private String id;

    private String mrGroupId;

    private String realName;

    private List<String> groupRoles;

    @Transient
    private Map<String, String> groupRoleLocalNames;

    private Date createdAt;

    private Date updatedAt;

    public GroupMemberView() {
    }

    public GroupMemberView(List<String> groupRoles) {
        this.groupRoles = groupRoles;
    }

    public GroupMemberView(String id, String mrGroupId, String realName, List<String> groupRoles, Date createdAt) {
        this.id = id;
        this.mrGroupId = mrGroupId;
        this.realName = realName;
        this.groupRoles = groupRoles;
        this.createdAt = createdAt;
    }

    @Override
    public void localize(I18Translator i18Translator) {
        this.groupRoleLocalNames = new HashMap<>();
        for (String role : this.groupRoles) {
            this.groupRoleLocalNames.put(role, i18Translator.localize("GROUP_ROLE_" + role));
        }
    }

    public void addGroupRole(String roleName) {
        this.groupRoles.add(roleName);
    }

    public void removeGroupRole(String roleName) {
        this.groupRoles.remove(roleName);
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

    public List<String> getGroupRoles() {
        return groupRoles;
    }

    public void setGroupRoles(List<String> groupRoles) {
        this.groupRoles = groupRoles;
    }

    public Map<String, String> getGroupRoleLocalNames() {
        return groupRoleLocalNames;
    }

    public void setGroupRoleLocalNames(Map<String, String> groupRoleLocalNames) {
        this.groupRoleLocalNames = groupRoleLocalNames;
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
