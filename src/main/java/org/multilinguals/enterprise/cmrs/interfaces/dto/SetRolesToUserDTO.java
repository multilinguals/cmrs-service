package org.multilinguals.enterprise.cmrs.interfaces.dto;

import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class SetRolesToUserDTO {
    @NotEmpty()
    private List<RoleId> roleIdList;

    public SetRolesToUserDTO() {
    }

    public List<RoleId> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<RoleId> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
