package org.multilinguals.enterprise.cmrs.interfaces.dto.common;

import org.multilinguals.enterprise.cmrs.interfaces.constant.MessageType;

import java.util.HashMap;
import java.util.Map;

public class FieldExceptionResponse extends ExceptionResponse {
    private Map<String, String> messages = new HashMap<>();

    public FieldExceptionResponse() {
        super(MessageType.FIELD);
    }

    public FieldExceptionResponse(Map<String, String> messages) {
        super(MessageType.FIELD);
        this.messages = messages;
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }

    public void addMessage(String field, String message) {
        this.messages.put(field, message);
    }
}