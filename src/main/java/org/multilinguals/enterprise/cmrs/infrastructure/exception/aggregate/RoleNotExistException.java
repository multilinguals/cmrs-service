package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.code.RoleResultCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.AbstractException;

public class RoleNotExistException extends AbstractException {
    public RoleNotExistException() {
        super(RoleResultCode.ROLE_NOT_EXISTED);
    }
}
