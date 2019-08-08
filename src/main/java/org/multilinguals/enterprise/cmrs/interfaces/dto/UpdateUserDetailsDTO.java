package org.multilinguals.enterprise.cmrs.interfaces.dto;

import javax.validation.constraints.NotEmpty;

public class UpdateUserDetailsDTO {
    @NotEmpty(message = "user.id.NotEmpty")
    private String id;

    @NotEmpty(message = "user.realName.NotEmpty")
    private String realName;

    public UpdateUserDetailsDTO() {
    }

    public String getId() {
        return id;
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
}
