package org.multilinguals.enterprise.cmrs.infrastructure.i18n;

import org.multilinguals.enterprise.cmrs.interfaces.dto.common.CMRSPage;
import org.multilinguals.enterprise.cmrs.infrastructure.persistence.Localizable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.util.List;

@ControllerAdvice(basePackages = "org.multilinguals.enterprise.cmrs.interfaces.query")
public class I18nWrapperAdvice implements ResponseBodyAdvice<Object> {
    @Resource
    private I18Translator i18Translator;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Localizable) {
            ((Localizable) body).localize(this.i18Translator);
        } else if (body instanceof CMRSPage) {
            for (Object item : ((CMRSPage) body).getContent()) {
                if (item instanceof Localizable) {
                    ((Localizable) item).localize(this.i18Translator);
                }
            }

        } else if (body instanceof List) {
            for (Object item : ((List) body)) {
                if (item instanceof Localizable) {
                    ((Localizable) item).localize(this.i18Translator);
                }
            }
        }

        return body;
    }
}
