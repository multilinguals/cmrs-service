package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.code.RoleResultCode;

public class RoleNotExistException extends Exception {
    public RoleNotExistException() {
        super(RoleResultCode.ROLE_NOT_EXISTED);
    }
}
