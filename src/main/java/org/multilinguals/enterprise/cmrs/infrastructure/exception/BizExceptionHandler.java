package org.multilinguals.enterprise.cmrs.infrastructure.exception;


import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.multilinguals.enterprise.cmrs.constant.http.HeaderFields;
import org.multilinguals.enterprise.cmrs.constant.result.BizErrorCode;
import org.multilinguals.enterprise.cmrs.constant.result.CommonErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.BizException;
import org.multilinguals.enterprise.cmrs.infrastructure.i18n.I18Translator;
import org.multilinguals.enterprise.cmrs.interfaces.dto.common.BizExceptionResponse;
import org.multilinguals.enterprise.cmrs.interfaces.dto.common.FieldExceptionResponse;
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
public class BizExceptionHandler {
    @Resource
    private I18Translator i18Translator;

    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BizExceptionResponse handleHTTPException(HttpServletResponse response, BizException ex) {
        response.addHeader(HeaderFields.BIZ_ERR_CODE, ex.getMessage());
        return new BizExceptionResponse(i18Translator.localize(ex.getMessage()));
    }

    @ExceptionHandler(JSR303ViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BizExceptionResponse handleJSR303ViolationException(HttpServletResponse response) {
        response.addHeader(HeaderFields.BIZ_ERR_CODE, CommonErrorCode.INVALID_COMMAND_PARAMS);
        return new BizExceptionResponse(i18Translator.localize(CommonErrorCode.INVALID_COMMAND_PARAMS));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BizExceptionResponse handleAccessDeniedException(HttpServletResponse response) {
        response.addHeader(HeaderFields.BIZ_ERR_CODE, BizErrorCode.NO_PERMISSION);
        return new BizExceptionResponse(i18Translator.localize(BizErrorCode.NO_PERMISSION));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public FieldExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldExceptionResponse fieldExceptionResponse = new FieldExceptionResponse();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            fieldExceptionResponse.addMessage(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return fieldExceptionResponse;
    }

    @ExceptionHandler(CommandExecutionException.class)
    @ResponseBody
    public BizExceptionResponse handleCommandExecutionException(CommandExecutionException ex, HttpServletResponse response) {
        if (ex.getCause() instanceof BizException) {
            BizException bizEx = (BizException) ex.getCause();
            response.addHeader(HeaderFields.BIZ_ERR_CODE, bizEx.getMessage());
            return new BizExceptionResponse(i18Translator.localize(bizEx.getMessage()));
        } else {
            // TODO 输入到日志里
            ex.printStackTrace();
            return null;
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public void handleException(Exception ex) {
        // TODO 输入到日志里
        ex.printStackTrace();
    }
}
