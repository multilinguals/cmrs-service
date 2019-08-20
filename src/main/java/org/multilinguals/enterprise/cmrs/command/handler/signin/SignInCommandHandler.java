package org.multilinguals.enterprise.cmrs.command.handler.signin;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.Account;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.AccountId;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPassword;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.command.aggregate.usersession.UserSessionId;
import org.multilinguals.enterprise.cmrs.command.aggregate.usersession.command.CreateUserSessionCommand;
import org.multilinguals.enterprise.cmrs.command.handler.AbstractCommandHandler;
import org.multilinguals.enterprise.cmrs.constant.result.BizErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.data.Tuple2;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.BizException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SignInCommandHandler extends AbstractCommandHandler {
    @Resource
    private Repository<Account> userIdentityAggregateRepository;

    @Resource
    private Repository<UserPassword> userPasswordRepositoryAggregateRepository;

    @CommandHandler
    public Tuple2<UserSessionId, UserId> handler(SignInWithPasswordCommand command) throws BizException {
        AccountId accountId = new AccountId(command.getIdInAccountType(), command.getAccountType());

        Aggregate<Account> userIdentityAggregate;
        try {
            userIdentityAggregate = userIdentityAggregateRepository.load(accountId.getIdentifier());
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.ACCOUNT_PASSWORD_INVALID);
        }

        UserPasswordId userPasswordId = userIdentityAggregate.invoke(Account::getUserPasswordId);

        if (userPasswordId == null) {
            // 如果用户密码不存在
            throw new BizException(BizErrorCode.ACCOUNT_PASSWORD_INVALID);
        } else {
            UserId accountUserId = userIdentityAggregate.invoke(Account::getUserId);

            // 获取用户密码实例
            Aggregate<UserPassword> userPasswordAggregate = userPasswordRepositoryAggregateRepository.load(userPasswordId.getIdentifier());

            // 判断密码是否正确
            if (!userPasswordAggregate.invoke(userPassword -> userPassword.validPassword(command.getPassword()))) {
                throw new BizException(BizErrorCode.ACCOUNT_PASSWORD_INVALID);
            }

            // 创建一个用户会话
            UserSessionId userSessionId = this.commandGateway.sendAndWait(new CreateUserSessionCommand(accountUserId));

            return new Tuple2<>(userSessionId, accountUserId);
        }
    }
}
