package org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class SetRolesToMemberDTO {
    @NotEmpty
    private List<String> roleList;

    public SetRolesToMemberDTO() {
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }
}
