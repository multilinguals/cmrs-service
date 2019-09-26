package org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup;

import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

public class AddMembersToGroupDTO {
    @NotEmpty()
    private Map<UserId, String> newMembers;

    public AddMembersToGroupDTO() {
    }

    public Map<UserId, String> getNewMembers() {
        return newMembers;
    }

    public void setNewMembers(Map<UserId, String> newMembers) {
        this.newMembers = newMembers;
    }
}
