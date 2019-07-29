package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.command.aggregate.usersession.UserSessionId;
import org.multilinguals.enterprise.cmrs.command.aggregate.usersession.command.DeleteUserSessionCommand;
import org.multilinguals.enterprise.cmrs.command.handler.signin.SignInWithPasswordCommand;
import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;
import org.multilinguals.enterprise.cmrs.dto.authorization.UserSignInDTO;
import org.multilinguals.enterprise.cmrs.infrastructure.data.Tuple2;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.UserPasswordInvalidException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.CMRSHTTPException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthorizationCommandController {
    @Resource
    private CommandGateway commandGateway;

    /**
     * 使用密码登录
     *
     * @param command 密码登录命令
     */
    @PostMapping("/sign-in-with-password")
    @PreAuthorize("permitAll()")
    public UserSignInDTO handle(@RequestBody SignInWithPasswordCommand command) {
        try {
            Tuple2<UserSessionId, UserId> result = commandGateway.sendAndWait(command);
            return new UserSignInDTO(result.getT1().getIdentifier(), result.getT2().getIdentifier());
        } catch (AggregateNotFoundException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_UNAUTHORIZED, ErrorCode.ACCOUNT_PASSWORD_INVALID);
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof UserPasswordInvalidException) {
                throw new CMRSHTTPException(HttpServletResponse.SC_UNAUTHORIZED, ex.getCause().getMessage());
            } else {
                throw ex;
            }
        }
    }

    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public void handle(@RequestAttribute("sessionId") String sessionId, HttpServletResponse response) {
        try {
            commandGateway.sendAndWait(new DeleteUserSessionCommand(new UserSessionId(sessionId)));
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (AggregateNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }
}
