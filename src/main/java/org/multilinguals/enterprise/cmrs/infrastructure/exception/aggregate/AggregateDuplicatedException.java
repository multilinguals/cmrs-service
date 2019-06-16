package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.infrastructure.exception.AbstractException;

public class AggregateDuplicatedException extends AbstractException {
    public AggregateDuplicatedException(int messageCode) {
        super(messageCode);
    }
}
