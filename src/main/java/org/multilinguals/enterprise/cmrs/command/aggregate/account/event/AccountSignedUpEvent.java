package org.multilinguals.enterprise.cmrs.command.aggregate.account.event;

import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;

public class AccountSignedUpEvent extends AbstractEvent {
    @AggregateIdentifier
    private AccountId accountId;

    public AccountSignedUpEvent(AccountId accountId) {
        this.accountId = accountId;
    }

    public AccountId getAccountId() {
        return accountId;
    }
}
