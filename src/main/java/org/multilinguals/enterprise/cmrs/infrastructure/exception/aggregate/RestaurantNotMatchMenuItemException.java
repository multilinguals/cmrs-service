package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;

public class RestaurantNotMatchMenuItemException extends Exception {
    public RestaurantNotMatchMenuItemException() {
        super(ErrorCode.REST_NOT_MATCH_MENU_ITEM);
    }
}
