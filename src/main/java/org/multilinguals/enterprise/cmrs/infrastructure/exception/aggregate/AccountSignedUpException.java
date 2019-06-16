package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.code.AuthResultCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.AbstractException;

public class AccountSignedUpException extends AbstractException {
    public AccountSignedUpException() {
        super(AuthResultCode.SIGNED_UP_ACCOUNT);
    }
}
