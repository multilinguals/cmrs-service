package org.multilinguals.enterprise.cmrs.infrastructure.security;

import com.alibaba.fastjson.JSON;
import org.multilinguals.enterprise.cmrs.constant.http.HeaderFields;
import org.multilinguals.enterprise.cmrs.constant.result.BizErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.i18n.I18Translator;
import org.multilinguals.enterprise.cmrs.interfaces.dto.common.BizExceptionResponse;
import org.springframework.http.HttpStatus;
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
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.addHeader(HeaderFields.BIZ_ERR_CODE, BizErrorCode.NO_PERMISSION);
        httpServletResponse.addHeader("Content-Type", "application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(new BizExceptionResponse(i18Translator.localize(BizErrorCode.NO_PERMISSION))));
    }
}
