package org.multilinguals.enterprise.cmrs.interfaces.dto.common;

public class ExceptionResponse {
    private String messageType;

    public ExceptionResponse(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
