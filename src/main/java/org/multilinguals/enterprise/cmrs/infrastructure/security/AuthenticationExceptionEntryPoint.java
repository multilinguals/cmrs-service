package org.multilinguals.enterprise.cmrs.infrastructure.security;

import com.alibaba.fastjson.JSON;
import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.dto.ExceptionResponse;
import org.multilinguals.enterprise.cmrs.infrastructure.i18n.I18Translator;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationExceptionEntryPoint implements AuthenticationEntryPoint {
    private I18Translator i18Translator;

    AuthenticationExceptionEntryPoint(I18Translator i18Translator) {
        this.i18Translator = i18Translator;
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        //TODO
        e.printStackTrace();
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getWriter().write(JSON.toJSONString(new ExceptionResponse(i18Translator.localize(ErrorCode.INVALID_TOKEN))));
    }
}
