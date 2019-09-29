package org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class AddMembersToGroupDTO {
    @NotEmpty()
    private List<String> userIdList;

    public AddMembersToGroupDTO() {
    }

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }
}
