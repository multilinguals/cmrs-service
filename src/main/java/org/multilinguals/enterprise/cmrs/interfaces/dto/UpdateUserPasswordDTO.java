package org.multilinguals.enterprise.cmrs.interfaces.dto;

import javax.validation.constraints.NotEmpty;

public class UpdateUserPasswordDTO {
    @NotEmpty(message = "password.value.NotEmpty")
    private String newUserPassword;

    public UpdateUserPasswordDTO() {
    }

    public String getNewUserPassword() {
        return newUserPassword;
    }

    public void setNewUserPassword(String newUserPassword) {
        this.newUserPassword = newUserPassword;
    }
}
