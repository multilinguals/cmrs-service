package org.multilinguals.enterprise.cmrs.infrastructure.security;

import com.alibaba.fastjson.JSON;
import org.multilinguals.enterprise.cmrs.constant.result.code.AuthResultCode;
import org.multilinguals.enterprise.cmrs.infrastructure.dto.ExceptionResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        httpServletResponse.getWriter().write(JSON.toJSONString(new ExceptionResponse(AuthResultCode.INVALID_TOKEN, "")));
    }
}
