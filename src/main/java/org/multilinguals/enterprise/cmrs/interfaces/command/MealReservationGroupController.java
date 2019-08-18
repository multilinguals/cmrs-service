package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command.CreateMealReservationCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.interfaces.dto.common.AggregateCreatedDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.mrgroup.CreateMealReservationGroupDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MealReservationGroupController {
    @Resource
    private CommandGateway commandGateway;

    @PostMapping("/create-mr-group")
    @PreAuthorize("hasAnyRole('ROLE_ORDER_TAKER')")
    public AggregateCreatedDTO<String> createMRGroup(@RequestBody CreateMealReservationGroupDTO dto, @RequestAttribute String reqSenderId) {
        MealReservationGroupId mrGroupId = this.commandGateway.sendAndWait(new CreateMealReservationCommand(dto.getName(), new UserId(reqSenderId)));
        return new AggregateCreatedDTO<>(mrGroupId.getIdentifier());
    }
}
