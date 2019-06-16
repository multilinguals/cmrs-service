package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.code.AuthResultCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.AbstractException;

public class UserPasswordInvalidException extends AbstractException {
    public UserPasswordInvalidException() {
        super(AuthResultCode.AUTHORIZE_FAILED);
    }
}
