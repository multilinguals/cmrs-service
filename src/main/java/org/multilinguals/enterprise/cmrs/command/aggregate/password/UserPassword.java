package org.multilinguals.enterprise.cmrs.command.aggregate.password;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.command.BindUserToUserPasswordCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.command.UpdateUserPasswordCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.event.UserPasswordBoundUserEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.event.UserPasswordCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.event.UserPasswordUpdatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.constant.result.code.UserPasswordResultCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.UserNotMatchPasswordException;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class UserPassword {
    @AggregateIdentifier
    private UserPasswordId id;

    private String hashValue;

    private UserId userId;

    // 需要使用密码的账号
    private List<AccountId> accountIdList = new ArrayList<>();

    protected UserPassword() {
    }

    public UserPassword(AccountId accountId, String password) {
        apply(new UserPasswordCreatedEvent(new UserPasswordId(), hashInputPassword(password), accountId));
    }

    @CommandHandler
    public void handler(UpdateUserPasswordCommand command) throws UserNotMatchPasswordException {
        if (!command.getUserId().equals(this.userId)) {
            throw new UserNotMatchPasswordException(UserPasswordResultCode.USER_NOT_MATCH_PASSWORD);
        }

        apply(new UserPasswordUpdatedEvent(command.getUserPasswordId(), hashInputPassword(command.getNewUserPassword())));
    }

    @CommandHandler
    public void handler(BindUserToUserPasswordCommand command) {
        apply(new UserPasswordBoundUserEvent(new UserPasswordId(), command.getUserId()));
    }

    @EventSourcingHandler
    public void on(UserPasswordCreatedEvent event) {
        this.id = event.getUserPasswordId();
        this.hashValue = event.getHashValue();
        this.accountIdList.add(event.getAccountId());
    }

    @EventSourcingHandler
    public void on(UserPasswordUpdatedEvent event) {
        this.hashValue = event.getHashValue();
    }

    @EventSourcingHandler
    public void on(UserPasswordBoundUserEvent event) {
        this.userId = event.getUserId();
    }

    private String hashInputPassword(String input) {
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }

    /**
     * 验证密码
     *
     * @param password 用户输入的密码
     * @return 是否有效
     */
    public Boolean validPassword(String password) {
        return this.hashValue.equals(hashInputPassword(password));
    }

    public UserPasswordId getId() {
        return id;
    }

    public String getHashValue() {
        return hashValue;
    }

    public UserId getUserId() {
        return userId;
    }

    public List<AccountId> getAccountIdList() {
        return accountIdList;
    }
}
