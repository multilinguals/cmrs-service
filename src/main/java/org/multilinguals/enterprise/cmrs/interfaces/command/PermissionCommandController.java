package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.multilinguals.enterprise.cmrs.command.aggregate.permission.command.AssociatePermissionToRoleCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.permission.command.CreatePermissionCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class PermissionCommandController {
    @Resource
    private CommandGateway commandGateway;

    @PostMapping("/admin/create-permission")
    public void adminCreatePermission(@RequestBody CreatePermissionCommand command, HttpServletResponse response) {
        this.commandGateway.sendAndWait(command);
    }

    @PostMapping("/admin/associate-permission-to-role")
    public void adminAssociatePermissionToRole(@RequestBody AssociatePermissionToRoleCommand command, HttpServletResponse response) {
        this.commandGateway.sendAndWait(command);
    }
}
