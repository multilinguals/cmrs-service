package org.multilinguals.enterprise.cmrs.command.aggregate.account;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.command.BindUserPasswordToAccountCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.command.BindUserToAccountCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.command.CreateAccountCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.event.AccountBoundUserEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.event.AccountBoundUserPasswordEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.event.AccountCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Account {
    @AggregateIdentifier
    private AccountId id;

    private UserId userId;

    private UserPasswordId userPasswordId;

    /**
     * 对于可溯源的事件，需要提供一个无参构造函数，
     * axon框架会在使用过往事件回溯之前，使用这个构造函数进行对象实例化
     */
    protected Account() {
    }

    @CommandHandler
    public Account(CreateAccountCommand command) {
        apply(new AccountCreatedEvent(command.getId(), command.getUserId(), command.getUserPasswordId()));
    }

    @CommandHandler
    public void handler(BindUserToAccountCommand command) {
        apply(new AccountBoundUserEvent(command.getAccountId(), command.getUserId()));
    }

    @CommandHandler
    public void handler(BindUserPasswordToAccountCommand command) {
        apply(new AccountBoundUserPasswordEvent(command.getAccountId(), command.getUserPasswordId()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.id = event.getId();
        this.userPasswordId = event.getUserPasswordId();
    }

    @EventSourcingHandler
    public void on(AccountBoundUserEvent event) {
        this.userId = event.getUserId();
    }

    @EventSourcingHandler
    public void on(AccountBoundUserPasswordEvent event) {
        this.userPasswordId = event.getUserPasswordId();
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
