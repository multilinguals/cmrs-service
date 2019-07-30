package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.command.UpdateUserPasswordCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.command.SetRolesToUserCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.command.UpdateUserDetailsCommand;
import org.multilinguals.enterprise.cmrs.command.handler.signup.CreateClerkWithUsernameCommand;
import org.multilinguals.enterprise.cmrs.constant.result.CommonResultCode;
import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;
import org.multilinguals.enterprise.cmrs.dto.aggregate.AggregateCreatedDTO;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.AccountSignedUpException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.RoleNotExistException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.UserNotExistException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.UserNotMatchPasswordException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.CMRSHTTPException;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserCommandController {
    @Resource
    private CommandGateway commandGateway;

    private UserDetailsViewRepository userDetailsViewRepository;

    @Autowired
    public UserCommandController(UserDetailsViewRepository userDetailsViewRepository) {
        this.userDetailsViewRepository = userDetailsViewRepository;
    }

    @PostMapping("/create-clerk")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public AggregateCreatedDTO<String> createUser(@RequestBody CreateClerkWithUsernameCommand command) {
        try {
            UserId userId = commandGateway.sendAndWait(command);
            return new AggregateCreatedDTO<>(userId.getIdentifier());
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof AccountSignedUpException) {
                throw new CMRSHTTPException(HttpServletResponse.SC_CONFLICT, ErrorCode.SIGNED_UP_ACCOUNT);
            } else {
                throw ex;
            }
        }
    }

    /**
     * @param command 更新用户详情
     */
    @PostMapping("/update-details-of-user/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public void updateUserDetails(@PathVariable String userId, @RequestBody UpdateUserDetailsCommand command, HttpServletResponse response) {
        command.setId(new UserId(userId));
        commandGateway.sendAndWait(command);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @PostMapping("/update-user/{userId}/password/{passwordId}")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public void updateUserPassword(@PathVariable String userId, @PathVariable String passwordId, @RequestBody UpdateUserPasswordCommand command, HttpServletResponse response) {
        try {
            command.setUserId(new UserId(userId));
            command.setUserPasswordId(new UserPasswordId(passwordId));
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

    @PostMapping("/update-self-password")
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

    @PostMapping("/set-roles-to-user/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public void assignRoleToUser(@RequestBody SetRolesToUserCommand command, @PathVariable String userId, HttpServletResponse response) {
        try {
            this.userDetailsViewRepository.findById(userId).orElseThrow(UserNotExistException::new);
            command.setUserId(new UserId(userId));
            commandGateway.sendAndWait(command);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (UserNotExistException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof RoleNotExistException || ex.getCause() instanceof UserNotExistException) {
                throw new CMRSHTTPException(HttpServletResponse.SC_BAD_REQUEST, ex.getCause().getMessage());
            }
        }
    }
}
