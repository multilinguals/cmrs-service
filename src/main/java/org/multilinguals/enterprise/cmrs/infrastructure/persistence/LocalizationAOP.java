package org.multilinguals.enterprise.cmrs.infrastructure.persistence;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.multilinguals.enterprise.cmrs.infrastructure.i18n.I18Translator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Aspect
@Component
public class LocalizationAOP {
    @Resource
    private I18Translator i18Translator;

    @Pointcut("execution(* org..*Repository.find*(..)) || execution(* org..*Executor.find*(..))")
    public void pointcut() {
    }

    @AfterReturning(value = "pointcut()", returning = "object")
    public void afterReturning(Object object) {
        if (object instanceof Localizable) {
            ((Localizable) object).localize(this.i18Translator);
        } else if (object instanceof List) {
            for (Object item : (List) object) {
                if (item instanceof Localizable) {
                    ((Localizable) item).localize(this.i18Translator);
                }
            }
        }
    }
}
