package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command.CreateRestaurantCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.dto.aggregate.AggregateCreatedDTO;
import org.multilinguals.enterprise.cmrs.infrastructure.dto.CommandResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RestaurantCommandController {
    @Resource
    private CommandGateway commandGateway;

    @PostMapping("/admin/create-restaurant")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public CommandResponse<AggregateCreatedDTO<String>> assignRoleToUser(@RequestBody CreateRestaurantCommand command, @RequestAttribute String reqSenderId, HttpServletResponse response) {
        command.setCreatorId(new UserId(reqSenderId));
        RestaurantId restaurantId = commandGateway.sendAndWait(command);
        return new CommandResponse<>(new AggregateCreatedDTO<>(restaurantId.getIdentifier()));
    }
}
