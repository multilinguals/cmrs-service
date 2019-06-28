package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.command.CreateRoleCommand;
import org.multilinguals.enterprise.cmrs.command.handler.preprocess.InitializeUserDataCommand;
import org.multilinguals.enterprise.cmrs.command.handler.signup.CreateUserWithUsernameCommand;
import org.multilinguals.enterprise.cmrs.constant.aggregate.role.DefaultRoleName;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.AccountSignedUpException;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * 应用启动时，进行业务数据的预处理
 * 初始化用户数据命令 {@link InitializeUserDataCommand}，被处理器{@link org.multilinguals.enterprise.cmrs.command.handler.signup.SignUpCommandHandler}
 * 接收并处理
 */
@Component
public class PreBizDataProcessListener implements ApplicationListener<ApplicationStartedEvent> {
    @Resource
    private CommandGateway commandGateway;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        // 当Spring Boot已经启动后，发出初始化用户数据命令
        // 检查业务需要的角色，没有创建时，需要创建
        // 超级管理员
        this.createRole(DefaultRoleName.SUPER_ADMIN);

        // 用户管理员
        this.createRole(DefaultRoleName.USER_ADMIN);

        // 餐厅管理员
        this.createRole(DefaultRoleName.REST_ADMIN);

        // 职员
        this.createRole(DefaultRoleName.CLERK);

        // 点餐员
        this.createRole(DefaultRoleName.ORDER_TAKER);

        // 创建超级管理员
        CreateUserWithUsernameCommand createUserWithUsernameCommand = new CreateUserWithUsernameCommand(
                "admin",
                "超级管理员",
                DigestUtils.md5DigestAsHex("admin123".getBytes()),
                DefaultRoleName.SUPER_ADMIN);
        try {
            commandGateway.sendAndWait(createUserWithUsernameCommand);
        } catch (CommandExecutionException ex) {
            if (!(ex.getCause() instanceof AccountSignedUpException)) {
                throw ex;
            }

            //TODO 替换成log4j
            System.out.println("超级管理员已创建");
        }
    }

    private void createRole(String roleName) {
        try {
            this.commandGateway.sendAndWait(new CreateRoleCommand(roleName));
        } catch (org.axonframework.modelling.command.ConcurrencyException ex) {
            //TODO 替换成log4j
            System.out.println("角色" + roleName + "已创建");
        }
    }
}
