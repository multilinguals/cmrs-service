package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.command.UpdateUserPasswordCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.command.SetRolesToUserCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.command.UpdateUserDetailsCommand;
import org.multilinguals.enterprise.cmrs.command.handler.signup.CreateUserWithUsernameCommand;
import org.multilinguals.enterprise.cmrs.constant.aggregate.role.DefaultRoleName;
import org.multilinguals.enterprise.cmrs.constant.result.CommonResultCode;
import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.AccountSignedUpException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.RoleNotExistException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.UserNotExistException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.UserNotMatchPasswordException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.CMRSHTTPException;
import org.multilinguals.enterprise.cmrs.interfaces.dto.UpdateSelfPasswordDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.UpdateUserPasswordDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.SignUpByUsernameDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.UpdateUserDetailsDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.common.AggregateCreatedDTO;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public AggregateCreatedDTO<String> createUser(@RequestBody SignUpByUsernameDTO dto) {
        try {
            UserId userId = commandGateway.sendAndWait(new CreateUserWithUsernameCommand(
                    dto.getUsername(), dto.getRealName(), dto.getPassword(), DefaultRoleName.CLERK)
            );
            return new AggregateCreatedDTO<>(userId.getIdentifier());
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof AccountSignedUpException) {
                throw new CMRSHTTPException(HttpStatus.CONFLICT.value(), ErrorCode.SIGNED_UP_ACCOUNT);
            } else {
                throw ex;
            }
        }
    }

    @PostMapping("/update-details-of-user/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserDetails(@PathVariable String userId, @RequestBody UpdateUserDetailsDTO dto) {
        try {
            commandGateway.sendAndWait(new UpdateUserDetailsCommand(new UserId(userId), dto.getRealName()));
        } catch (AggregateNotFoundException ex) {
            throw new CMRSHTTPException(HttpStatus.NOT_FOUND.value(), CommonResultCode.NOT_FOUND);
        }
    }

    @PostMapping("/update-user/{userId}/password/{passwordId}")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserPassword(@PathVariable String userId, @PathVariable String passwordId, @RequestBody UpdateUserPasswordDTO dto) {
        try {
            commandGateway.sendAndWait(new UpdateUserPasswordCommand(new UserPasswordId(passwordId), new UserId(userId), dto.getNewUserPassword()));
        } catch (AggregateNotFoundException ex) {
            throw new CMRSHTTPException(HttpStatus.NOT_FOUND.value(), CommonResultCode.NOT_FOUND);
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof UserNotMatchPasswordException) {
                throw new CMRSHTTPException(HttpStatus.BAD_REQUEST.value(), ex.getCause().getMessage());
            } else {
                throw new CMRSHTTPException(HttpStatus.INTERNAL_SERVER_ERROR.value(), CommonResultCode.UNKNOWN_EXCEPTION);
            }
        }
    }

    @PostMapping("/update-self-password")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSelfPassword(@RequestBody UpdateSelfPasswordDTO dto, @RequestAttribute String reqSenderId) {
        try {
            commandGateway.sendAndWait(
                    new UpdateUserPasswordCommand(
                            new UserPasswordId(dto.getUserPasswordId()),
                            new UserId(reqSenderId),
                            dto.getNewUserPassword()
                    )
            );
        } catch (AggregateNotFoundException ex) {
            throw new CMRSHTTPException(HttpStatus.NOT_FOUND.value(), CommonResultCode.NOT_FOUND);
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof UserNotMatchPasswordException) {
                throw new CMRSHTTPException(HttpStatus.BAD_REQUEST.value(), ex.getCause().getMessage());
            } else {
                throw ex;
            }
        }
    }

    @PostMapping("/set-roles-to-user/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignRoleToUser(@RequestBody SetRolesToUserCommand command, @PathVariable String userId, HttpServletResponse response) {
        try {
            this.userDetailsViewRepository.findById(userId).orElseThrow(UserNotExistException::new);
            command.setUserId(new UserId(userId));
            commandGateway.sendAndWait(command);
        } catch (UserNotExistException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof RoleNotExistException || ex.getCause() instanceof UserNotExistException) {
                throw new CMRSHTTPException(HttpServletResponse.SC_BAD_REQUEST, ex.getCause().getMessage());
            } else {
                throw ex;
            }
        }
    }
}
