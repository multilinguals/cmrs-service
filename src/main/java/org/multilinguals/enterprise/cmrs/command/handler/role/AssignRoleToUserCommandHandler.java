package org.multilinguals.enterprise.cmrs.command.handler.role;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.Role;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.User;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.command.BindRoleToUserCommand;
import org.multilinguals.enterprise.cmrs.command.handler.AbstractCommandHandler;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.RoleNotExistException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.UserNotExistException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AssignRoleToUserCommandHandler extends AbstractCommandHandler {
    @Resource
    private Repository<Role> roleAggregateRepository;

    @Resource
    private Repository<User> userAggregateRepository;

    @CommandHandler
    public void handler(AssignRoleToUserCommand command) throws RoleNotExistException, UserNotExistException {
        try {
            // 获取用户聚合根
            Aggregate<User> userAggregate = userAggregateRepository.load(command.getUserId().getIdentifier());

            try {
                // 获取角色聚合根
                Aggregate<Role> roleAggregate = roleAggregateRepository.load(command.getRoleId().getIdentifier());

                // 如果角色不存在，才会添加
                if (!userAggregate.invoke((user) -> user.hasRole(command.getRoleId()))) {
                    RoleId roleId = roleAggregate.invoke(Role::getId);

                    commandGateway.sendAndWait(new BindRoleToUserCommand(command.getUserId(), roleId));
                }
            } catch (AggregateNotFoundException ex) {
                throw new RoleNotExistException();
            }
        } catch (AggregateNotFoundException ex) {
            throw new UserNotExistException();
        }
    }
}
