package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPasswordId;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.command.UpdateUserPasswordCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.command.SetRolesToUserCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.command.UpdateUserDetailsCommand;
import org.multilinguals.enterprise.cmrs.command.handler.signup.CreateUserWithUsernameCommand;
import org.multilinguals.enterprise.cmrs.constant.aggregate.role.DefaultRoleName;
import org.multilinguals.enterprise.cmrs.constant.result.BizErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.BizException;
import org.multilinguals.enterprise.cmrs.interfaces.dto.command.authorization.SignUpByUsernameDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.command.user.*;
import org.multilinguals.enterprise.cmrs.interfaces.dto.common.AggregateCreatedDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserCommandController {
    @Resource
    private CommandGateway commandGateway;

    @PostMapping("/create-clerk")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public AggregateCreatedDTO<String> createUser(@RequestBody @Validated SignUpByUsernameDTO dto) {
        UserId userId = commandGateway.sendAndWait(
                new CreateUserWithUsernameCommand(
                        dto.getUsername(), dto.getRealName(), dto.getPassword(), DefaultRoleName.CLERK
                )
        );
        return new AggregateCreatedDTO<>(userId.getIdentifier());
    }

    @PostMapping("/update-details-of-user/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserDetails(@PathVariable String userId, @RequestBody @Validated UpdateUserDetailsDTO dto) throws BizException {
        try {
            commandGateway.sendAndWait(new UpdateUserDetailsCommand(new UserId(userId), dto.getRealName()));
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.USER_NOT_EXISTED);
        }
    }

    @PostMapping("/update-user/{userId}/password/{passwordId}")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserPassword(@PathVariable String userId, @PathVariable String passwordId, @RequestBody @Validated UpdateUserPasswordDTO dto) throws BizException {
        try {
            commandGateway.sendAndWait(new UpdateUserPasswordCommand(new UserPasswordId(passwordId), new UserId(userId), dto.getNewUserPassword()));
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.PASSWORD_NOT_EXISTED);
        }
    }

    @PostMapping("/update-self-details")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSelfDetails(@RequestBody @Validated UpdateSelfDetailsDTO dto, @RequestAttribute String reqSenderId) throws BizException {
        try {
            commandGateway.sendAndWait(new UpdateUserDetailsCommand(new UserId(reqSenderId), dto.getRealName()));
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.USER_NOT_EXISTED);
        }
    }

    @PostMapping("/update-self-password")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSelfPassword(@RequestBody @Validated UpdateSelfPasswordDTO dto, @RequestAttribute String reqSenderId) throws BizException {
        try {
            commandGateway.sendAndWait(
                    new UpdateUserPasswordCommand(
                            new UserPasswordId(dto.getUserPasswordId()),
                            new UserId(reqSenderId),
                            dto.getNewUserPassword()
                    )
            );
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.USER_NOT_EXISTED);
        }
    }

    @PostMapping("/set-roles-to-user/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void assignRoleToUser(@RequestBody @Validated SetRolesToUserDTO dto, @PathVariable String userId) throws BizException {
        try {
            commandGateway.sendAndWait(new SetRolesToUserCommand(new UserId(userId), dto.getRoleIdList()));
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.USER_NOT_EXISTED);
        }
    }
}
