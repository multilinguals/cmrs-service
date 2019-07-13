package org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate;

import org.multilinguals.enterprise.cmrs.constant.result.ErrorCode;

import java.util.function.Supplier;

public class MenuItemTypeNotExistException extends Exception {
    public MenuItemTypeNotExistException() {
        super(ErrorCode.MENU_ITEM_TYPE_NOT_EXISTED);
    }
}
