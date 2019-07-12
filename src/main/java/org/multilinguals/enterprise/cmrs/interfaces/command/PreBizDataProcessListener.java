package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.command.CreateDishTypeCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.command.CreateRoleCommand;
import org.multilinguals.enterprise.cmrs.command.handler.signup.CreateUserWithUsernameCommand;
import org.multilinguals.enterprise.cmrs.constant.aggregate.dishtype.DefaultDishType;
import org.multilinguals.enterprise.cmrs.constant.aggregate.role.DefaultRoleName;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.AccountSignedUpException;
import org.multilinguals.enterprise.cmrs.query.dishtype.DishTypeView;
import org.multilinguals.enterprise.cmrs.query.dishtype.DishTypeViewRepository;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * 应用启动时，进行业务数据的预处理
 */
@Component
public class PreBizDataProcessListener implements ApplicationListener<ApplicationStartedEvent> {
    @Resource
    private CommandGateway commandGateway;

    @Resource
    private DishTypeViewRepository dishTypeViewRepository;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        // 检查业务需要的角色，没有创建时，需要创建
        this.createRole(DefaultRoleName.SUPER_ADMIN);
        this.createRole(DefaultRoleName.USER_ADMIN);
        this.createRole(DefaultRoleName.REST_ADMIN);
        this.createRole(DefaultRoleName.CLERK);
        this.createRole(DefaultRoleName.ORDER_TAKER);

        // 创建超级管理员
        try {
            CreateUserWithUsernameCommand createUserWithUsernameCommand = new CreateUserWithUsernameCommand(
                    "admin",
                    "超级管理员",
                    DigestUtils.md5DigestAsHex("admin123".getBytes()),
                    DefaultRoleName.SUPER_ADMIN);
            commandGateway.sendAndWait(createUserWithUsernameCommand);
        } catch (CommandExecutionException ex) {
            if (!(ex.getCause() instanceof AccountSignedUpException)) {
                throw ex;
            }

            //TODO 替换成log4j
            System.out.println("超级管理员已创建");
        }

        // 预置菜谱相关数据
        this.createDishType(DefaultDishType.DISH);
        this.createDishType(DefaultDishType.DRINKS);
        this.createDishType(DefaultDishType.PASTA);
        this.createDishType(DefaultDishType.RICE);
        this.createDishType(DefaultDishType.SOUP);
    }

    private void createRole(String name) {
        try {
            this.commandGateway.sendAndWait(new CreateRoleCommand(name));
        } catch (org.axonframework.modelling.command.ConcurrencyException ex) {
            //TODO 替换成log4j
            System.out.println("角色" + name + "已创建");
        }
    }

    private void createDishType(String name) {
        DishTypeView dishTypeView = this.dishTypeViewRepository.findOne(Example.of(new DishTypeView(null, name, null))).orElse(null);
        if (dishTypeView == null) {
            this.commandGateway.sendAndWait(new CreateDishTypeCommand(name));
        }
    }
}
