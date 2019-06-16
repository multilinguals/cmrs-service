package org.multilinguals.enterprise.cmrs.command.aggregate.user.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;

import javax.validation.constraints.NotNull;

public class CreateUserCommand extends AbstractCommand {
    @NotNull
    private AccountId accountId;

    @NotNull
    private RoleId roleId;

    private UserPasswordId userPasswordId;

    public CreateUserCommand(@NotNull AccountId accountId, @NotNull RoleId roleId, UserPasswordId userPasswordId) {
        this.accountId = accountId;
        this.roleId = roleId;
        this.userPasswordId = userPasswordId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public RoleId getRoleId() {
        return roleId;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }
}