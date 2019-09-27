package org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class AddMembersToGroupDTO {
    @NotEmpty()
    private List<String> memberIdList;

    public AddMembersToGroupDTO() {
    }

    public List<String> getMemberIdList() {
        return memberIdList;
    }

    public void setMemberIdList(List<String> memberIdList) {
        this.memberIdList = memberIdList;
    }
}
