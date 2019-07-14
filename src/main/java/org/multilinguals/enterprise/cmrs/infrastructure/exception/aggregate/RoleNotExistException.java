package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;

public class RoleNotExistException extends Exception {
    public RoleNotExistException() {
        super(ErrorCode.ROLE_NOT_EXISTED);
    }
}
