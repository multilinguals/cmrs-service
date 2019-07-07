package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.code.UserResultCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.AbstractException;

public class UserNotExistException extends AbstractException {
    public UserNotExistException() {
        super(UserResultCode.USER_NOT_EXISTED);
    }
}
