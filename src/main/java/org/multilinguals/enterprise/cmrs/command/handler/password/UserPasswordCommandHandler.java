package org.multilinguals.enterprise.cmrs.command.handler.password;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Repository;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPassword;
import org.multilinguals.enterprise.cmrs.command.handler.AbstractCommandHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserPasswordCommandHandler extends AbstractCommandHandler {
    @Resource
    private Repository<UserPassword> userPasswordRepository;

    @CommandHandler
    public void handler(UpdateUserPasswordCommand command) {
        userPasswordRepository.load(command.getId().getIdentifier());


    }
}
