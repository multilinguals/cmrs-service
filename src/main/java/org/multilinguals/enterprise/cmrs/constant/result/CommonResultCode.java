package org.multilinguals.enterprise.cmrs.constant.result;

public interface CommonResultCode {
    // 处理成功
    String SUCCESS = "20000";

    // 参数有误
    String INVALID_COMMAND_PARAMS = "40000";

    // 无权限访问
    String FORBIDDEN = "40300";

    // 服务未知错误
    String UNKNOWN_EXCEPTION = "50000";

    // 操作对象不存在
    String NOT_FOUND = "40400";
}
