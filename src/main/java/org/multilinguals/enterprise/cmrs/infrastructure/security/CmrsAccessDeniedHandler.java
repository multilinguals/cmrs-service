package org.multilinguals.enterprise.cmrs.infrastructure.security;

import com.alibaba.fastjson.JSON;
import org.multilinguals.enterprise.cmrs.constant.result.CommonResultCode;
import org.multilinguals.enterprise.cmrs.infrastructure.dto.ExceptionResponse;
import org.multilinguals.enterprise.cmrs.infrastructure.i18n.I18Translator;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CmrsAccessDeniedHandler implements AccessDeniedHandler {
    private I18Translator i18Translator;

    CmrsAccessDeniedHandler(I18Translator i18Translator) {
        this.i18Translator = i18Translator;
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        e.printStackTrace();
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.getWriter().write(JSON.toJSONString(new ExceptionResponse(i18Translator.localize(CommonResultCode.FORBIDDEN))));
    }
}
