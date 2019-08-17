package org.multilinguals.enterprise.cmrs.interfaces.dto.user;

import javax.validation.constraints.NotEmpty;

public class UpdateSelfPasswordDTO {
    @NotEmpty()
    private String userPasswordId;

    @NotEmpty()
    private String newUserPassword;

    public UpdateSelfPasswordDTO() {
    }

    public String getUserPasswordId() {
        return userPasswordId;
    }

    public void setUserPasswordId(String userPasswordId) {
        this.userPasswordId = userPasswordId;
    }

    public String getNewUserPassword() {
        return newUserPassword;
    }

    public void setNewUserPassword(String newUserPassword) {
        this.newUserPassword = newUserPassword;
    }
}
