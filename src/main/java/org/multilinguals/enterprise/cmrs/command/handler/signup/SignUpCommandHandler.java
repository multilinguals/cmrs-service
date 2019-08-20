package org.multilinguals.enterprise.cmrs.command.handler.signup;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.Account;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.command.CreateAccountCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.command.CreateUserPasswordCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.Role;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.command.CreateUserCommand;
import org.multilinguals.enterprise.cmrs.command.handler.AbstractCommandHandler;
import org.multilinguals.enterprise.cmrs.constant.aggregate.account.AccountType;
import org.multilinguals.enterprise.cmrs.constant.result.BizErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.BizException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SignUpCommandHandler extends AbstractCommandHandler {
    @Resource
    private Repository<Account> accountAggregateRepository;

    @Resource
    private Repository<Role> roleAggregateRepository;

    @CommandHandler
    public UserId handler(CreateUserWithUsernameCommand command) throws BizException {
        return createNewUserWithUsername(command.getUsername(), command.getRealName(), command.getPassword(), command.getRoleName());
    }

    private UserId createNewUserWithUsername(String username, String realName, String password, String roleName) throws BizException {
        // 验证角色是否存在
        RoleId roleId = RoleId.ofName(roleName);
        this.roleAggregateRepository.load(roleId.getIdentifier());

        // 账号ID对象
        AccountId accountId = new AccountId(username, AccountType.USERNAME);
        UserId userId = new UserId();
        UserPasswordId userPasswordId = new UserPasswordId();


        try {
            accountAggregateRepository.load(accountId.getIdentifier());
            throw new BizException(BizErrorCode.SIGNED_UP_ACCOUNT);
        } catch (AggregateNotFoundException ex) {
            // 创建账号聚合
            this.commandGateway.sendAndWait(new CreateAccountCommand(accountId, userId, userPasswordId));
            // 创建密码聚合
            this.commandGateway.sendAndWait(new CreateUserPasswordCommand(userPasswordId, password, accountId, userId));
            // 创建用户聚合
            this.commandGateway.sendAndWait(new CreateUserCommand(userId, accountId, realName, roleId, userPasswordId));
        }

        return userId;
    }
}
