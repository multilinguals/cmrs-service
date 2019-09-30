package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup;

import org.axonframework.modelling.command.EntityId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import java.util.List;

public class GroupMember {
    @EntityId
    private GroupMemberId id;

    private UserId userId;

    private List<String> groupRoles;

    public GroupMember(GroupMemberId id, UserId userId, List<String> groupRoles) {
        this.id = id;
        this.userId = userId;
        this.groupRoles = groupRoles;
    }

    public GroupMemberId getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public List<String> getGroupRoles() {
        return groupRoles;
    }

    public void setGroupRoles(List<String> groupRoles) {
        this.groupRoles = groupRoles;
    }
}
