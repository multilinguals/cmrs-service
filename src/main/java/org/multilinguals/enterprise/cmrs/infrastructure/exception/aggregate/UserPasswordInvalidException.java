package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;

public class UserPasswordInvalidException extends Exception {
    public UserPasswordInvalidException() {
        super(ErrorCode.ACCOUNT_PASSWORD_INVALID);
    }
}
