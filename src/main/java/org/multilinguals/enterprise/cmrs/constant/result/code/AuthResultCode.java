package org.multilinguals.enterprise.cmrs.constant.result.code;

public interface AuthResultCode {
    // 用户身份凭证已经存在
    int SIGNED_UP_ACCOUNT = 40901;

    // 用户认证失败
    int AUTHORIZE_FAILED = 40101;

    // 没有权限查看
    int FORBIDDEN = 40301;
}
