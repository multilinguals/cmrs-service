package org.multilinguals.enterprise.cmrs.infrastructure.exception;

public class AbstractException extends Exception {
    private int messageCode;

    public AbstractException(int messageCode) {
        this.messageCode = messageCode;
    }

    public int getMessageCode() {
        return messageCode;
    }
}