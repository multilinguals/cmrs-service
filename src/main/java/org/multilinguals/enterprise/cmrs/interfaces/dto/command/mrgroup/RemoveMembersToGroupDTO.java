package org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class RemoveMembersToGroupDTO {
    @NotEmpty()
    private List<String> memberIdList;

    public RemoveMembersToGroupDTO() {
    }

    public List<String> getMemberIdList() {
        return memberIdList;
    }

    public void setMemberIdList(List<String> memberIdList) {
        this.memberIdList = memberIdList;
    }
}
