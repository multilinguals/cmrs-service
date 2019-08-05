package org.multilinguals.enterprise.cmrs.interfaces.dto;

import javax.validation.constraints.NotNull;

public class SignInWithPasswordDTO {
    @NotNull(message = "{account.id.NotNull}")
    private String idInAccountType;

    @NotNull(message = "{account.type.NotNull}")
    private Integer accountType;

    @NotNull
    private String password;

    public SignInWithPasswordDTO(@NotNull String idInAccountType, @NotNull Integer accountType, @NotNull String password) {
        this.idInAccountType = idInAccountType;
        this.accountType = accountType;
        this.password = password;
    }

    public String getIdInAccountType() {
        return idInAccountType;
    }

    public void setIdInAccountType(String idInAccountType) {
        this.idInAccountType = idInAccountType;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
