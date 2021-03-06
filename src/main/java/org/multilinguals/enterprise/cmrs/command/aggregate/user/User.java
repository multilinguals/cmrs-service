package org.multilinguals.enterprise.cmrs.command.aggregate.user;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.command.CreateUserCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.command.SetRolesToUserCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.command.UpdateUserDetailsCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.event.RolesSetToUserEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.event.UserCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.event.UserDetailsUpdatedEvent;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class User {
    @AggregateIdentifier
    private UserId id;

    private String realName;

    private List<AccountId> accountIdList = new ArrayList<>();

    private List<RoleId> roleIdList = new ArrayList<>();

    private UserPasswordId userPasswordId;

    protected User() {
    }

    @CommandHandler
    public User(CreateUserCommand command) {
        apply(new UserCreatedEvent(command.getUserId(), command.getRealName(), command.getAccountId(), command.getRoleId(), command.getUserPasswordId()));
    }

    @CommandHandler
    public void handle(UpdateUserDetailsCommand command) {
        //TODO 如果是超级管理员，需要抛出异常

        apply(new UserDetailsUpdatedEvent(command.getId(), command.getRealName()));
    }

    @CommandHandler
    public void handle(SetRolesToUserCommand command) {
        apply(new RolesSetToUserEvent(command.getUserId(), command.getRoleIdList()));
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        this.id = event.getId();

        this.realName = event.getRealName();

        this.accountIdList.add(event.getAccountId());

        this.roleIdList.add(event.getRoleId());

        this.userPasswordId = event.getUserPasswordId();
    }

    @EventSourcingHandler
    public void on(UserDetailsUpdatedEvent event) {
        this.id = event.getId();

        if (event.getRealName() != null) {
            this.realName = event.getRealName();
        }
    }

    @EventSourcingHandler
    public void on(RolesSetToUserEvent event) {
        this.roleIdList.clear();
        this.roleIdList = event.getRoleIdList();
    }

    public UserId getId() {
        return id;
    }

    public String getRealName() {
        return realName;
    }

    public List<AccountId> getAccountIdList() {
        return accountIdList;
    }

    public List<RoleId> getRoleIdList() {
        return roleIdList;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }
}
