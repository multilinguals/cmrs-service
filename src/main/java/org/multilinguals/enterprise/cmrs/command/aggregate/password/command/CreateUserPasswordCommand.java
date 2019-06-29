package org.multilinguals.enterprise.cmrs.command.aggregate.password.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class CreateUserPasswordCommand extends AbstractCommand {
    private UserPasswordId userPasswordId;

    private String password;

    private AccountId accountId;

    private UserId userId;

    public CreateUserPasswordCommand(UserPasswordId userPasswordId, String password, AccountId accountId, UserId userId) {
        this.userPasswordId = userPasswordId;
        this.password = password;
        this.accountId = accountId;
        this.userId = userId;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }

    public String getPassword() {
        return password;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public UserId getUserId() {
        return userId;
    }
}
