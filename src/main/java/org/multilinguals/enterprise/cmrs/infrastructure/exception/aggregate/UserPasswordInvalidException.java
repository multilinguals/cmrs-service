package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.code.AuthResultCode;

public class UserPasswordInvalidException extends Exception {
    public UserPasswordInvalidException() {
        super(AuthResultCode.AUTHORIZE_FAILED);
    }
}
