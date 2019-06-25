package org.multilinguals.enterprise.cmrs.command.aggregate.account.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class AccountBoundUserEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private AccountId accountId;

    private UserId userId;

    public AccountBoundUserEvent(AccountId accountId, UserId userId) {
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
