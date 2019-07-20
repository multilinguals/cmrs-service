package org.multilinguals.enterprise.cmrs.infrastructure.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "通用响应返回对象")
public class CommandResponse<T> extends AbstractResponse {
    private T data;

    public CommandResponse(T data) {
        this.data = data;
    }

    public CommandResponse(String code, T data) {
        super(code);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}