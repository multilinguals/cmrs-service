package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.code.AuthResultCode;

public class UserNotMatchPasswordException extends Exception {
    public UserNotMatchPasswordException() {
        super(AuthResultCode.ACCOUNT_PASSWORD_INVALID);
    }
}
