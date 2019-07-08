package org.multilinguals.enterprise.cmrs.infrastructure.exception.http;

import javax.xml.ws.http.HTTPException;

public class CMRSHTTPException extends HTTPException {
    private String messageCode;

    public CMRSHTTPException(int statusCode, String messageCode) {
        super(statusCode);
        this.messageCode = messageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }
}
