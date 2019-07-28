package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;

public class SingleMenuItemRequiredException extends Exception {
    public SingleMenuItemRequiredException() {
        super(ErrorCode.SINGLE_ITEM_REQUIRED);
    }
}
