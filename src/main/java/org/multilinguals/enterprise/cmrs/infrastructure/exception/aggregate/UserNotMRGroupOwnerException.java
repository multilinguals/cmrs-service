package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;

public class UserNotMRGroupOwnerException extends Exception {
    public UserNotMRGroupOwnerException() {
        super(ErrorCode.USER_NOT_MR_GROUP_OWNER);
    }
}