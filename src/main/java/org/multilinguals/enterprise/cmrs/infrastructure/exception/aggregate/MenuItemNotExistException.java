package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;

public class MenuItemNotExistException extends Exception {
    public MenuItemNotExistException() {
        super(ErrorCode.MENU_ITEM_NOT_EXISTED);
    }
}
