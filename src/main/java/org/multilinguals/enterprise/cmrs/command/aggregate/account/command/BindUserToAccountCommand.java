package org.multilinguals.enterprise.cmrs.command.aggregate.account.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class BindUserToAccountCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private AccountId accountId;

    private UserId userId;

    public BindUserToAccountCommand(AccountId accountId, UserId userId) {
        this.accountId = accountId;
        this.userId = userId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public UserId getUserId() {
        return userId;
    }
}
