package org.multilinguals.enterprise.cmrs.command.aggregate.password.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;

public class CreateUserPasswordCommand extends AbstractCommand {
    private String password;

    private AccountId accountId;

    public CreateUserPasswordCommand(String password, AccountId accountId) {
        this.password = password;
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public AccountId getAccountId() {
        return accountId;
    }
}
