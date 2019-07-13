package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;

public class DishTypeNotExistException extends Exception {
    public DishTypeNotExistException() {
        super(ErrorCode.DISH_TYPE_NOT_EXISTED);
    }
}
