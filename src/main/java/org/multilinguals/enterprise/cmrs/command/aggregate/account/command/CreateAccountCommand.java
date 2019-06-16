package org.multilinguals.enterprise.cmrs.command.aggregate.account.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class CreateAccountCommand extends AbstractCommand {
    private AccountId accountId;

    public CreateAccountCommand(AccountId accountId) {
        this.accountId = accountId;
    }

    public AccountId getAccountId() {
        return accountId;
    }
}