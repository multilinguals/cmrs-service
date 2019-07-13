package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;

public class TasteNotExistException extends Exception {
    public TasteNotExistException() {
        super(ErrorCode.TASTE_NOT_EXISTED);
    }
}
