package org.multilinguals.enterprise.cmrs.command.aggregate.password.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class UserPasswordCreatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private UserPasswordId userPasswordId;

    private String hashValue;

    private AccountId accountId;

    private UserId userId;

    public UserPasswordCreatedEvent(UserPasswordId userPasswordId, String hashValue, AccountId accountId, UserId userId) {
        this.userPasswordId = userPasswordId;
        this.hashValue = hashValue;
        this.accountId = accountId;
        this.userId = userId;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }

    public String getHashValue() {
        return hashValue;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public UserId getUserId() {
        return userId;
    }
}
