package org.multilinguals.enterprise.cmrs.infrastructure.i18n;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class I18Translator {
    private static ResourceBundleMessageSource messageSource;

    public I18Translator(ResourceBundleMessageSource messageSource) {
        I18Translator.messageSource = messageSource;
    }

    public String localize(String messageCode) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(messageCode, null, locale);
    }
}
