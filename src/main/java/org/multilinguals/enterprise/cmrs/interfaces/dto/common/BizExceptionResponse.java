package org.multilinguals.enterprise.cmrs.interfaces.dto.common;

import org.multilinguals.enterprise.cmrs.interfaces.constant.MessageType;

public class BizExceptionResponse extends ExceptionResponse {
    private String message;

    public BizExceptionResponse(String message) {
        super(MessageType.BiZ);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
