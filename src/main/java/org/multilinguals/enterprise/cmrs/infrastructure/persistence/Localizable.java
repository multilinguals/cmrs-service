package org.multilinguals.enterprise.cmrs.infrastructure.persistence;

import org.multilinguals.enterprise.cmrs.infrastructure.i18n.I18Translator;

public interface Localizable {
    void localize(I18Translator i18Translator);
}
