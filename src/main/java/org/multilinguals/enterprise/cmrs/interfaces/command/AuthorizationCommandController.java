package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.command.aggregate.usersession.UserSessionId;
import org.multilinguals.enterprise.cmrs.command.aggregate.usersession.command.DeleteUserSessionCommand;
import org.multilinguals.enterprise.cmrs.command.handler.signin.SignInWithPasswordCommand;
import org.multilinguals.enterprise.cmrs.constant.aggregate.account.AccountType;
import org.multilinguals.enterprise.cmrs.infrastructure.data.Tuple2;
import org.multilinguals.enterprise.cmrs.interfaces.dto.command.authorization.SignInWithPasswordDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.command.authorization.UserSignInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthorizationCommandController {
    @Resource
    private CommandGateway commandGateway;

    /**
     * 使用密码登录
     */
    @PostMapping("/sign-in-with-password")
    @PreAuthorize("permitAll()")
    public UserSignInDTO handle(@RequestBody @Validated SignInWithPasswordDTO dto) {
        Tuple2<UserSessionId, UserId> result = commandGateway.sendAndWait(new SignInWithPasswordCommand(
                dto.getIdInAccountType(),
                AccountType.ofValue(dto.getAccountType()),
                dto.getPassword()));
        return new UserSignInDTO(result.getT1().getIdentifier(), result.getT2().getIdentifier());
    }

    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handle(@RequestAttribute("sessionId") String sessionId, HttpServletResponse response) {
        try {
            commandGateway.sendAndWait(new DeleteUserSessionCommand(new UserSessionId(sessionId)));
        } catch (AggregateNotFoundException ex) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
        }
    }
}
