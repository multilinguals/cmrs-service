package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.command.UpdateUserPasswordCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.command.RemoveRoleFromUserCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.command.UpdateUserDetailsCommand;
import org.multilinguals.enterprise.cmrs.command.handler.role.AssignRoleToUserCommand;
import org.multilinguals.enterprise.cmrs.command.handler.signup.CreateClerkWithUsernameCommand;
import org.multilinguals.enterprise.cmrs.constant.result.CommonResultCode;
import org.multilinguals.enterprise.cmrs.constant.result.code.AuthResultCode;
import org.multilinguals.enterprise.cmrs.constant.result.code.UserResultCode;
import org.multilinguals.enterprise.cmrs.dto.aggregate.AggregateCreatedDTO;
import org.multilinguals.enterprise.cmrs.infrastructure.dto.CommandResponse;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.AccountSignedUpException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.RoleNotExistException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.UserNotExistException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.UserNotMatchPasswordException;
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

    @PostMapping("/admin/create-clerk")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public CommandResponse<AggregateCreatedDTO<String>> createUser(@RequestBody CreateClerkWithUsernameCommand command) {
        try {
            UserId userId = commandGateway.sendAndWait(command);
            return new CommandResponse<>(new AggregateCreatedDTO<>(userId.getIdentifier()));
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

    @PostMapping("/admin/update-user-password")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public void updateUserPassword(@RequestBody UpdateUserPasswordCommand command, HttpServletResponse response) {
        try {
            commandGateway.sendAndWait(command);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (AggregateNotFoundException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_NOT_FOUND, CommonResultCode.NOT_FOUND);
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof UserNotMatchPasswordException) {
                throw new CMRSHTTPException(HttpServletResponse.SC_BAD_REQUEST, ex.getCause().getMessage());
            } else {
                throw new CMRSHTTPException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, CommonResultCode.UNKNOWN_EXCEPTION);
            }
        }
    }

    @PostMapping("/admin/update-self-password")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public void updateSelfPassword(@RequestBody UpdateUserPasswordCommand command, @RequestAttribute String reqSenderId, HttpServletResponse response) {
        try {
            command.setUserId(new UserId(reqSenderId));
            commandGateway.sendAndWait(command);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (AggregateNotFoundException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_NOT_FOUND, CommonResultCode.NOT_FOUND);
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof UserNotMatchPasswordException) {
                throw new CMRSHTTPException(HttpServletResponse.SC_BAD_REQUEST, ex.getCause().getMessage());
            } else {
                throw new CMRSHTTPException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, CommonResultCode.UNKNOWN_EXCEPTION);
            }
        }
    }

    @PostMapping("/admin/assign-role-to-user")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public void assignRoleToUser(@RequestBody AssignRoleToUserCommand command, HttpServletResponse response) {
        try {
            commandGateway.sendAndWait(command);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof RoleNotExistException || ex.getCause() instanceof UserNotExistException) {
                throw new CMRSHTTPException(HttpServletResponse.SC_BAD_REQUEST, ex.getCause().getMessage());
            }
        }
    }

    @PostMapping("/admin/remove-role-from-user")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public void assignRoleToUser(@RequestBody RemoveRoleFromUserCommand command, HttpServletResponse response) {
        try {
            commandGateway.sendAndWait(command);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (AggregateNotFoundException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_NOT_FOUND, UserResultCode.USER_NOT_EXISTED);
        }
    }
}
