package org.multilinguals.enterprise.cmrs.infrastructure.dto;


import org.multilinguals.enterprise.cmrs.constant.result.CommonResultCode;

public class AbstractResponse {
    private String code;

    AbstractResponse() {
        this.code = CommonResultCode.SUCCESS;
    }

    AbstractResponse(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
