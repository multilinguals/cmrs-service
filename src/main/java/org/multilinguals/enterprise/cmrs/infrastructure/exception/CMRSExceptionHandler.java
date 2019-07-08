package org.multilinguals.enterprise.cmrs.infrastructure.exception;


import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.multilinguals.enterprise.cmrs.constant.CommonResultCode;
import org.multilinguals.enterprise.cmrs.infrastructure.dto.ExceptionResponse;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.CMRSHTTPException;
import org.multilinguals.enterprise.cmrs.infrastructure.i18n.I18Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CMRSExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private I18Translator i18Translator;

    @ExceptionHandler(CMRSHTTPException.class)
    @ResponseBody
    public ExceptionResponse handleHTTPException(HttpServletResponse response, CMRSHTTPException ex) {
        response.setStatus(ex.getStatusCode());
        return new ExceptionResponse(ex.getMessageCode(), i18Translator.localize(ex.getMessageCode()));
    }

    @ExceptionHandler(JSR303ViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleJSR303ViolationException(HttpServletResponse response, JSR303ViolationException ex) {
        return new ExceptionResponse(CommonResultCode.INVALID_COMMAND_PARAMS, i18Translator.localize(CommonResultCode.INVALID_COMMAND_PARAMS));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ExceptionResponse handleAccessDeniedException(HttpServletResponse response, AccessDeniedException ex) {
        return new ExceptionResponse(CommonResultCode.FORBIDDEN, i18Translator.localize(CommonResultCode.FORBIDDEN));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionResponse handleException(HttpServletRequest request, Exception ex) {
        // TODO 输入到日志里
        ex.printStackTrace();

        return new ExceptionResponse(CommonResultCode.UNKNOWN_EXCEPTION, i18Translator.localize(CommonResultCode.UNKNOWN_EXCEPTION));
    }
}
