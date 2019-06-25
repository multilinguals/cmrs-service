package org.multilinguals.enterprise.cmrs.command.aggregate.account.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;

public class AccountBoundUserPasswordEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private AccountId accountId;

    private UserPasswordId userPasswordId;

    public AccountBoundUserPasswordEvent(AccountId accountId, UserPasswordId userPasswordId) {
        this.accountId = accountId;
        this.userPasswordId = userPasswordId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }
}
