package org.multilinguals.enterprise.cmrs.constant.result.code;

public interface AuthResultCode {
    // 用户身份凭证已经存在
    String SIGNED_UP_ACCOUNT = "40901";

    // 用户认证失信息有误
    String AUTHORIZE_FAILED = "40101";

    // Token失效
    String INVALID_TOKEN = "40102";

    // 没有权限查看
    String FORBIDDEN = "40301";
}
