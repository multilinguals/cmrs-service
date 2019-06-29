package org.multilinguals.enterprise.cmrs.command.aggregate.account.event;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class AccountCreatedEvent extends AbstractEvent {
    @AggregateIdentifier
    private AccountId id;

    private UserId userId;

    private UserPasswordId userPasswordId;

    public AccountCreatedEvent(AccountId id, UserId userId, UserPasswordId userPasswordId) {
        this.id = id;
        this.userId = userId;
        this.userPasswordId = userPasswordId;
    }

    public AccountId getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }
}
