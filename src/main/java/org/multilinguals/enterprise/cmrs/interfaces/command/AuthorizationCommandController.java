package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.command.aggregate.usersession.UserSessionId;
import org.multilinguals.enterprise.cmrs.command.handler.signin.SignInWithPasswordCommand;
import org.multilinguals.enterprise.cmrs.command.handler.signup.SignUpUsernameAccountCommand;
import org.multilinguals.enterprise.cmrs.constant.result.code.AuthResultCode;
import org.multilinguals.enterprise.cmrs.infrastructure.data.Tuple2;
import org.multilinguals.enterprise.cmrs.infrastructure.dto.CommandResponse;
import org.multilinguals.enterprise.cmrs.infrastructure.dto.user.UserSignInDTO;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.AccountSignedUpException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.UserPasswordInvalidException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.CMRSHTTPException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthorizationCommandController {
    @Resource
    private CommandGateway commandGateway;

    @PostMapping("/user/sign-up-username")
    public void signUpUserName(@RequestBody SignUpUsernameAccountCommand command, HttpServletResponse response) {
        try {
            commandGateway.sendAndWait(command);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof AccountSignedUpException) {
                throw new CMRSHTTPException(HttpServletResponse.SC_CONFLICT, AuthResultCode.SIGNED_UP_ACCOUNT);
            } else {
                throw ex;
            }
        }
    }

    /**
     * 使用密码登录
     *
     * @param command 密码登录命令
     */
    @PostMapping("/user/sign-in-with-password")
    public CommandResponse<UserSignInDTO> signIn(@RequestBody SignInWithPasswordCommand command) {
        try {
            Tuple2<UserSessionId, UserId> result = commandGateway.sendAndWait(command);
            return new CommandResponse<>(new UserSignInDTO(result.getT1().getIdentifier(), result.getT2().getIdentifier()));
        } catch (AggregateNotFoundException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_UNAUTHORIZED, AuthResultCode.AUTHORIZE_FAILED);
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof UserPasswordInvalidException) {
                throw new CMRSHTTPException(HttpServletResponse.SC_UNAUTHORIZED, AuthResultCode.AUTHORIZE_FAILED);
            } else {
                throw ex;
            }
        }
    }
}
