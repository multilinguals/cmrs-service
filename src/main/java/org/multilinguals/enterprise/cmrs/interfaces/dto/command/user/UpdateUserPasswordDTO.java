package org.multilinguals.enterprise.cmrs.interfaces.dto.command.user;

import javax.validation.constraints.NotEmpty;

public class UpdateUserPasswordDTO {
    @NotEmpty()
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
