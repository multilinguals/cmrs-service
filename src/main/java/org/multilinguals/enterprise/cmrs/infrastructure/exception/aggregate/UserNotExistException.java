package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.code.UserResultCode;

public class UserNotExistException extends Exception {
    public UserNotExistException() {
        super(UserResultCode.USER_NOT_EXISTED);
    }
}
