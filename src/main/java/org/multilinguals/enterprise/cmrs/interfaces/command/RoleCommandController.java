package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.command.CreateRoleCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RoleCommandController {
    @Resource
    private CommandGateway commandGateway;

    @PostMapping("/admin/create-role")
    public void adminCreateRole(@RequestBody CreateRoleCommand command, HttpServletResponse response) {
        this.commandGateway.sendAndWait(command);
    }
}
