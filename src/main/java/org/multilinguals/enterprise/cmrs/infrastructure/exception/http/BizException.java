package org.multilinguals.enterprise.cmrs.infrastructure.exception.http;

public class BizException extends Exception {
    public BizException(int value, String userNotExisted) {
    }

    public BizException(String message) {
        super(message);
    }
}
