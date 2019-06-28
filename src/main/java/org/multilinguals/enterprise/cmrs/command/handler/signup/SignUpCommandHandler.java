package org.multilinguals.enterprise.cmrs.command.handler.signup;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.Account;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.command.BindUserToAccountCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.command.CreateAccountCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPassword;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.command.BindUserToUserPasswordCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.Role;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.command.CreateUserCommand;
import org.multilinguals.enterprise.cmrs.command.handler.AbstractCommandHandler;
import org.multilinguals.enterprise.cmrs.constant.aggregate.account.AccountType;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.AccountSignedUpException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SignUpCommandHandler extends AbstractCommandHandler {
    @Resource
    private Repository<Account> accountAggregateRepository;

    @Resource
    private Repository<Role> roleAggregateRepository;

    /**
     * 使用账号和密码创建用户
     *
     * @param command 使用账号和密码创建用户命令
     * @throws Exception
     */
    @CommandHandler
    public UserId handler(CreateUserWithUsernameCommand command) throws Exception {
        return createNewUserWithUsername(command.getUsername(), command.getRealName(), command.getPassword(), command.getRoleName());
    }

    private UserId createNewUserWithUsername(String username, String realName, String password, String roleName) throws Exception {
        // 验证角色是否存在
        RoleId roleId = new RoleId(roleName);
        this.roleAggregateRepository.load(roleId.getIdentifier());

        // 账号ID对象
        AccountId accountId = new AccountId(username, AccountType.USERNAME);

        try {
            this.accountAggregateRepository.load(accountId.getIdentifier());
            throw new AccountSignedUpException();
        } catch (AggregateNotFoundException ex) {
            org.axonframework.modelling.command.Aggregate<UserPassword> userPasswordAggregate = AggregateLifecycle.createNew(
                    UserPassword.class,
                    () -> new UserPassword(accountId, password)
            );

            UserPasswordId userPasswordId = userPasswordAggregate.invoke(UserPassword::getId);

            this.commandGateway.sendAndWait(new CreateAccountCommand(accountId, null, userPasswordId));

            return createUser(realName, userPasswordId, roleId, accountId);
        }
    }

    private UserId createUser(String realName, UserPasswordId userPasswordId, RoleId roleId, AccountId accountId) {
        UserId userId = this.commandGateway.sendAndWait(new CreateUserCommand(accountId, realName, roleId, userPasswordId));
        this.commandGateway.sendAndWait(new BindUserToAccountCommand(userId, accountId));
        this.commandGateway.sendAndWait(new BindUserToUserPasswordCommand(userId, userPasswordId));
        return userId;
    }
}
