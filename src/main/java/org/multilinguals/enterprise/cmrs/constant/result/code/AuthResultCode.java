package org.multilinguals.enterprise.cmrs.constant.result.code;

public interface AuthResultCode {
    // 用户身份凭证已经存在
    String SIGNED_UP_ACCOUNT = "40901";

    // 用户认证失信息有误
    String ACCOUNT_PASSWORD_INVALID = "40101";

    // Token失效
    String INVALID_TOKEN = "40102";
}
