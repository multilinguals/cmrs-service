package org.multilinguals.enterprise.cmrs.infrastructure.exception;


import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.multilinguals.enterprise.cmrs.constant.http.HeaderFields;
import org.multilinguals.enterprise.cmrs.constant.result.CommonResultCode;
import org.multilinguals.enterprise.cmrs.infrastructure.dto.BizExceptionResponse;
import org.multilinguals.enterprise.cmrs.infrastructure.dto.FieldExceptionResponse;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.CMRSHTTPException;
import org.multilinguals.enterprise.cmrs.infrastructure.i18n.I18Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@ControllerAdvice
public class CMRSExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private I18Translator i18Translator;

    @ExceptionHandler(CMRSHTTPException.class)
    @ResponseBody
    public BizExceptionResponse handleHTTPException(HttpServletResponse response, CMRSHTTPException ex) {
        response.setStatus(ex.getStatusCode());
        response.addHeader(HeaderFields.BIZ_CODE, ex.getMessageCode());
        return new BizExceptionResponse(i18Translator.localize(ex.getMessageCode()));
    }

    @ExceptionHandler(JSR303ViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BizExceptionResponse handleJSR303ViolationException(HttpServletResponse response) {
        response.addHeader(HeaderFields.BIZ_CODE, CommonResultCode.INVALID_COMMAND_PARAMS);
        return new BizExceptionResponse(i18Translator.localize(CommonResultCode.INVALID_COMMAND_PARAMS));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public BizExceptionResponse handleAccessDeniedException(HttpServletResponse response) {
        response.addHeader(HeaderFields.BIZ_CODE, CommonResultCode.FORBIDDEN);
        return new BizExceptionResponse(i18Translator.localize(CommonResultCode.FORBIDDEN));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public FieldExceptionResponse handleMethodArgumentNotValidException(HttpServletResponse response, MethodArgumentNotValidException ex) {
        FieldExceptionResponse fieldExceptionResponse = new FieldExceptionResponse();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            fieldExceptionResponse.addMessage(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return fieldExceptionResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BizExceptionResponse handleException(HttpServletResponse response, Exception ex) {
        // TODO 输入到日志里
        ex.printStackTrace();
        response.addHeader(HeaderFields.BIZ_CODE, CommonResultCode.UNKNOWN_EXCEPTION);
        return new BizExceptionResponse(i18Translator.localize(CommonResultCode.UNKNOWN_EXCEPTION));
    }
}
