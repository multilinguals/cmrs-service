package org.multilinguals.enterprise.cmrs.command.aggregate.user.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class UserCreatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private UserId id;

    private String realName;

    private AccountId accountId;

    private RoleId roleId;

    private UserPasswordId userPasswordId;

    public UserCreatedEvent(UserId id, String realName, AccountId accountId, RoleId roleId, UserPasswordId userPasswordId) {
        this.id = id;
        this.realName = realName;
        this.accountId = accountId;
        this.roleId = roleId;
        this.userPasswordId = userPasswordId;
    }

    public UserId getId() {
        return id;
    }

    public String getRealName() {
        return realName;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public RoleId getRoleId() {
        return roleId;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }
}
