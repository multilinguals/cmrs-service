package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;

public class RestaurantNotExistException extends Exception {
    public RestaurantNotExistException() {
        super(ErrorCode.RESTAURANT_NOT_EXISTED);
    }
}
