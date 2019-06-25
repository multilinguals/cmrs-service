package org.multilinguals.enterprise.cmrs.command.aggregate.account.event;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;

public class AccountCreatedEvent extends AbstractEvent {
    @AggregateIdentifier
    private AccountId accountId;

    public AccountCreatedEvent(AccountId accountId) {
        this.accountId = accountId;
    }

    public AccountId getAccountId() {
        return accountId;
    }
}
