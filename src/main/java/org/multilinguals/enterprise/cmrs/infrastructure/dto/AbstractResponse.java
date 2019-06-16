package org.multilinguals.enterprise.cmrs.infrastructure.dto;


import org.multilinguals.enterprise.cmrs.constant.CommonResultCode;

public class AbstractResponse {
    private int code;

    AbstractResponse() {
        this.code = CommonResultCode.SUCCESS;
    }

    AbstractResponse(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
