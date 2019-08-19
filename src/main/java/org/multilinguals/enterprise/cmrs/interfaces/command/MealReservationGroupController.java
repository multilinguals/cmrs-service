package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command.CreateMealReservationGroupCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command.DeleteMealReservationGroupCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.UserNotMRGroupOwnerException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.CMRSHTTPException;
import org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup.CreateMealReservationGroupDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.common.AggregateCreatedDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class MealReservationGroupController {
    @Resource
    private CommandGateway commandGateway;

    @PostMapping("/create-mr-group")
    @PreAuthorize("hasAnyRole('ROLE_ORDER_TAKER')")
    public AggregateCreatedDTO<String> createMRGroup(@RequestBody CreateMealReservationGroupDTO dto, @RequestAttribute String reqSenderId) {
        MealReservationGroupId mrGroupId = this.commandGateway.sendAndWait(new CreateMealReservationGroupCommand(dto.getName(), new UserId(reqSenderId)));
        return new AggregateCreatedDTO<>(mrGroupId.getIdentifier());
    }

    @PostMapping("/delete-mr-group/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ORDER_TAKER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMRGroup(@PathVariable String id, @RequestAttribute String reqSenderId) {
        try {
            this.commandGateway.sendAndWait(new DeleteMealReservationGroupCommand(new MealReservationGroupId(id), new UserId(reqSenderId)));
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof UserNotMRGroupOwnerException) {
                throw new CMRSHTTPException(HttpStatus.FORBIDDEN.value(), ex.getCause().getMessage());
            } else {
                throw ex;
            }
        }
    }
}
