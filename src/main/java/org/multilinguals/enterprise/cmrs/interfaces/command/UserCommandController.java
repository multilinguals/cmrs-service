package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.command.UpdateUserDetailsCommand;
import org.multilinguals.enterprise.cmrs.command.handler.password.UpdateSelfPasswordCommand;
import org.multilinguals.enterprise.cmrs.command.handler.password.UpdateUserPasswordCommand;
import org.multilinguals.enterprise.cmrs.command.handler.signup.CreateAccountCommandByUsername;
import org.multilinguals.enterprise.cmrs.constant.result.code.AuthResultCode;
import org.multilinguals.enterprise.cmrs.dto.aggregate.AggregateCreatedDTO;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.AccountSignedUpException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.CMRSHTTPException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserCommandController {
    @Resource
    private CommandGateway commandGateway;

    @PostMapping("/admin/create-user")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public AggregateCreatedDTO<String> createUser(@RequestBody CreateAccountCommandByUsername command) {
        try {
            UserId userId = commandGateway.sendAndWait(command);
            return new AggregateCreatedDTO<>(userId.getIdentifier());
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof AccountSignedUpException) {
                throw new CMRSHTTPException(HttpServletResponse.SC_CONFLICT, AuthResultCode.SIGNED_UP_ACCOUNT);
            } else {
                throw ex;
            }
        }
    }

    /**
     * @param command 更新用户详情
     */
    @PostMapping("/admin/update-user-details")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public void updateUserDetails(@RequestBody UpdateUserDetailsCommand command, HttpServletResponse response) {
        commandGateway.sendAndWait(command);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @PostMapping("/user/update-self-password")
    @PreAuthorize("isAuthenticated()")
    public void updateSelfPassword(@RequestBody UpdateSelfPasswordCommand command, @RequestAttribute String reqSenderId, HttpServletResponse response) {
        if (!reqSenderId.equals(command.getId().getIdentifier())) {
            throw new CMRSHTTPException(HttpServletResponse.SC_FORBIDDEN, AuthResultCode.FORBIDDEN);
        }

        commandGateway.sendAndWait(command);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @PostMapping("/admin/update-user-password")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public void updateUserPassword(@RequestBody UpdateUserPasswordCommand command, HttpServletResponse response) {
        commandGateway.sendAndWait(command);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
