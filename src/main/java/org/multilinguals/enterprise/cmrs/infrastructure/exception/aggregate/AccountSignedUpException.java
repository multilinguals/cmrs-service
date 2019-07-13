package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;

public class AccountSignedUpException extends Exception {
    public AccountSignedUpException() {
        super(ErrorCode.SIGNED_UP_ACCOUNT);
    }
}
