package org.multilinguals.enterprise.cmrs.command.handler.mrgroup;

import org.axonframework.commandhandling.CommandHandler;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command.DeleteMealReservationGroupCommand;
import org.springframework.stereotype.Component;

@Component
public class MRGroupCommandHandler {
    @CommandHandler
    public void handle(DeleteMealReservationGroupCommand command) {

    }
}
