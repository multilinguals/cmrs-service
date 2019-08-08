package org.multilinguals.enterprise.cmrs.interfaces.dto;

import javax.validation.constraints.NotEmpty;

public class SignUpByUsernameDTO {
    @NotEmpty(message = "{user.username.NotEmpty}")
    private String username;

    @NotEmpty(message = "{user.realName.NotEmpty}")
    private String realName;

    @NotEmpty(message = "{password.value.NotEmpty}")
    private String password;

    public SignUpByUsernameDTO(String username, String realName, String password) {
        this.username = username;
        this.realName = realName;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}