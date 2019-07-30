package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;

public class UserNotMatchPasswordException extends Exception {
    public UserNotMatchPasswordException() {
        super(ErrorCode.UserNotMatchPassword);
    }
}
