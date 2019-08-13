package org.multilinguals.enterprise.cmrs.interfaces.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class UpdateUserDetailsDTO {
    @NotEmpty()
    private String id;

    @NotEmpty()
    @Length(min = 2, max = 10)
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
