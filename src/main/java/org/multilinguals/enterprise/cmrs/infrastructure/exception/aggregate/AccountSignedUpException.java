package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.code.AuthResultCode;

public class AccountSignedUpException extends Exception {
    public AccountSignedUpException() {
        super(AuthResultCode.SIGNED_UP_ACCOUNT);
    }
}
