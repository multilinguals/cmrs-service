package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;

public class UserNotExistException extends Exception {
    public UserNotExistException() {
        super(ErrorCode.USER_NOT_EXISTED);
    }
}
