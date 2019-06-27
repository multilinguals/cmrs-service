package org.multilinguals.enterprise.cmrs.command.aggregate.account.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;

public class CreateAccountCommandWithPassword extends AbstractCommand {
    private AccountId accountId;

    private String password;

    public CreateAccountCommandWithPassword(AccountId accountId, String password) {
        this.accountId = accountId;
        this.password = password;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public String getPassword() {
        return password;
    }
}