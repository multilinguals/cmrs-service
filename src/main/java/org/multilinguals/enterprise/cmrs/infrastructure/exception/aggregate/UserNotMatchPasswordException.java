package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.infrastructure.exception.AbstractException;

public class UserNotMatchPasswordException extends AbstractException {
    public UserNotMatchPasswordException(int messageCode) {
        super(messageCode);
    }
}
