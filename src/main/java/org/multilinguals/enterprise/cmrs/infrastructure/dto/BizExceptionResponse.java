package org.multilinguals.enterprise.cmrs.infrastructure.dto;

import org.multilinguals.enterprise.cmrs.infrastructure.dto.constant.MessageType;

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
