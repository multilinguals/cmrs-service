package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup;

import org.axonframework.modelling.command.EntityId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import java.util.List;

public class GroupMember {
    @EntityId
    private UserId id;

    private List<String> groupRoles;

    public GroupMember(UserId id, List<String> groupRoles) {
        this.id = id;
        this.groupRoles = groupRoles;
    }

    public UserId getId() {
        return id;
    }

    public void setId(UserId id) {
        this.id = id;
    }

    public List<String> getGroupRoles() {
        return groupRoles;
    }

    public void setGroupRoles(List<String> groupRoles) {
        this.groupRoles = groupRoles;
    }
}
