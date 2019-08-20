package org.multilinguals.enterprise.cmrs.constant.result;

public interface BizErrorCode {
    // 用户认证失信息有误
    String ACCOUNT_PASSWORD_INVALID = "40101";

    // Token失效
    String INVALID_TOKEN = "40102";

    // 用户不存在
    String USER_NOT_EXISTED = "40401";

    // 角色不存在
    String ROLE_NOT_EXISTED = "40402";

    // 餐厅不存在
    String RESTAURANT_NOT_EXISTED = "40403";

    // 菜单项类型不存在
    String MENU_ITEM_TYPE_NOT_EXISTED = "40404";

    // 菜品不存在
    String DISH_TYPE_NOT_EXISTED = "40405";

    // 口味不存在
    String TASTE_NOT_EXISTED = "40406";

    // 菜单项不存在
    String MENU_ITEM_NOT_EXISTED = "40407";

    // 密码不存在
    String PASSWORD_NOT_EXISTED = "40408";

    // 点参团不存在
    String MR_GROUP_NOT_EXISTED = "40409";

    // 当前菜单项不是单品
    String SINGLE_ITEM_REQUIRED = "41201";

    // 当前菜单项不是套餐
    String SET_ITEM_REQUIRED = "41202";

    // 餐厅和菜单项不匹配
    String REST_NOT_MATCH_MENU_ITEM = "41203";

    // 用户不是点参团团长
    String USER_NOT_MR_GROUP_OWNER = "41204";

    // 用户ID和密码ID不匹配
    String USER_NOT_MATCH_PASSWORD = "41205";

    // 用户身份凭证已经存在
    String SIGNED_UP_ACCOUNT = "41206";

    // 用户不是点餐员
    String USER_NOT_ORDER_TAKER = "41207";
}
