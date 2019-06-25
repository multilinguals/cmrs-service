package org.multilinguals.enterprise.cmrs.command.handler.preprocess;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.Role;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.command.CreateRoleCommand;
import org.multilinguals.enterprise.cmrs.command.handler.AbstractCommandHandler;
import org.multilinguals.enterprise.cmrs.command.handler.signup.CreateAccountCommandByUsername;
import org.multilinguals.enterprise.cmrs.constant.aggregate.role.DefaultRoleName;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.AccountSignedUpException;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Component
public class PreProcessCommandHandler extends AbstractCommandHandler {
    @Resource
    private Repository<Role> roleAggregateRepository;

    @CommandHandler
    public void handler(InitializeUserDataCommand command) {
        // 检查业务需要的角色，没有创建时，需要创建
        // 超级管理员
        this.checkAndCreateRole(DefaultRoleName.SUPER_ADMIN);

        // 用户管理员
        this.checkAndCreateRole(DefaultRoleName.USER_ADMIN);

        // 餐厅管理员
        this.checkAndCreateRole(DefaultRoleName.REST_ADMIN);

        // 职员
        this.checkAndCreateRole(DefaultRoleName.CLERK);

        // 点餐员
        this.checkAndCreateRole(DefaultRoleName.ORDER_TAKER);

        // 创建超级管理员
        CreateAccountCommandByUsername createAccountCommandByUsername = new CreateAccountCommandByUsername(
                "admin",
                "超级管理员",
                DigestUtils.md5DigestAsHex("admin123".getBytes()),
                DefaultRoleName.SUPER_ADMIN);
        try {
            commandGateway.sendAndWait(createAccountCommandByUsername);
        } catch (CommandExecutionException ex) {
            if (!(ex.getCause() instanceof AccountSignedUpException)) {
                throw ex;
            }
        }
    }

    private void checkAndCreateRole(String roleName) {
        RoleId roleId = new RoleId(roleName);
        try {
            this.roleAggregateRepository.load(roleId.getIdentifier());
        } catch (AggregateNotFoundException ex) {
            this.commandGateway.sendAndWait(new CreateRoleCommand(roleName));
        }
    }
}
