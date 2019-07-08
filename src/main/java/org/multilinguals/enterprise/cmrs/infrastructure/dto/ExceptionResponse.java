package org.multilinguals.enterprise.cmrs.infrastructure.dto;

public class ExceptionResponse extends AbstractResponse {
    private String message;

    public ExceptionResponse(String code, String message) {
        super(code);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
