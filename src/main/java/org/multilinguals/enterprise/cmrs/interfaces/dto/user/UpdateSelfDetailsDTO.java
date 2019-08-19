package org.multilinguals.enterprise.cmrs.interfaces.dto.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class UpdateSelfDetailsDTO {
    @NotEmpty()
    @Length(min = 2, max = 10)
    private String realName;

    public UpdateSelfDetailsDTO() {
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
