package org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class AddMembersToGroupDTO {
    @NotEmpty()
    private List<String> newMembers;

    public AddMembersToGroupDTO() {
    }

    public List<String> getNewMembers() {
        return newMembers;
    }

    public void setNewMembers(List<String> newMembers) {
        this.newMembers = newMembers;
    }
}
