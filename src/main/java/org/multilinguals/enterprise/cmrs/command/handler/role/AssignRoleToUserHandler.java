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
public class AssignRoleToUserHandler extends AbstractCommandHandler {
    @Resource
    private Repository<Role> roleAggregateRepository;

    @Resource
    private Repository<User> userAggregateRepository;

    @CommandHandler
    public void handler(AssignRoleToUserCommand command) throws RoleNotExistException, UserNotExistException {
        try {
            Aggregate<User> userAggregate = userAggregateRepository.load(command.getUserId().getIdentifier());

            try {
                Aggregate<Role> roleAggregate = roleAggregateRepository.load(new RoleId(command.getRoleName()).getIdentifier());
                RoleId roleId = roleAggregate.invoke(Role::getId);

                // 如果角色不存在，才会添加
                if (!userAggregate.invoke((user) -> user.hasRole(roleId))) {
                    String roleName = roleAggregate.invoke(Role::getName);

                    commandGateway.sendAndWait(new BindRoleToUserCommand(command.getUserId(), roleId, roleName));
                }
            } catch (AggregateNotFoundException ex) {
                throw new RoleNotExistException();
            }
        } catch (AggregateNotFoundException ex) {
            throw new UserNotExistException();
        }
    }
}
