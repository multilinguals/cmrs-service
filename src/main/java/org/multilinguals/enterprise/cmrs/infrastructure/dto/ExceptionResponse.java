package org.multilinguals.enterprise.cmrs.infrastructure.dto;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.annotation.Resource;
import java.util.Locale;

public class ExceptionResponse extends AbstractResponse {
    @Resource
    private static ResourceBundleMessageSource messageSource;

    private String message;

    public ExceptionResponse(String code) {
        super(code);

        Locale locale = LocaleContextHolder.getLocale();
        this.message = messageSource.getMessage(String.valueOf(code), null, locale);
    }

    public ExceptionResponse(String code, Object[] args) {
        super(code);

        Locale locale = LocaleContextHolder.getLocale();
        this.message = messageSource.getMessage(String.valueOf(code), args, locale);
    }

    public String getMessage() {
        return message;
    }
}
