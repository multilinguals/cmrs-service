package org.multilinguals.enterprise.cmrs.command.aggregate.user.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotNull;

public class CreateUserCommand extends AbstractCommand {
    private UserId userId;

    private AccountId accountId;

    private String realName;

    private RoleId roleId;

    private UserPasswordId userPasswordId;

    public CreateUserCommand(@NotNull UserId userId, @NotNull AccountId accountId, String realName, RoleId roleId, UserPasswordId userPasswordId) {
        this.userId = userId;
        this.accountId = accountId;
        this.realName = realName;
        this.roleId = roleId;
        this.userPasswordId = userPasswordId;
    }

    public UserId getUserId() {
        return userId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public String getRealName() {
        return realName;
    }

    public RoleId getRoleId() {
        return roleId;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }
}