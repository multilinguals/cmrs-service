package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.code.UserPasswordResultCode;

public class UserNotMatchPasswordException extends Exception {
    public UserNotMatchPasswordException() {
        super(UserPasswordResultCode.USER_NOT_MATCH_PASSWORD);
    }
}
