package org.multilinguals.enterprise.cmrs.command.handler.password;

import org.axonframework.commandhandling.CommandHandler;
import org.multilinguals.enterprise.cmrs.command.handler.AbstractCommandHandler;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordCommandHandler extends AbstractCommandHandler {
    @CommandHandler
    public void handler(UpdateUserPasswordCommand command) {

    }
}
