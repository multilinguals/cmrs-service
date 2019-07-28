package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;

public class SetMenuItemRequiredException extends Exception {
    public SetMenuItemRequiredException() {
        super(ErrorCode.SET_ITEM_REQUIRED);
    }
}
