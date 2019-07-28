package org.multilinguals.enterprise.cmrs.constant.result;

public interface ErrorCode {
    // 用户认证失信息有误
    String ACCOUNT_PASSWORD_INVALID = "40101";

    // Token失效
    String INVALID_TOKEN = "40102";

    // 用户不存在
    String USER_NOT_EXISTED = "40001";

    // 角色不存在
    String ROLE_NOT_EXISTED = "40002";

    // 餐厅不存在
    String RESTAURANT_NOT_EXISTED = "40003";

    // 订单项类型不存在
    String MENU_ITEM_TYPE_NOT_EXISTED = "40004";

    // 菜品不存在
    String DISH_TYPE_NOT_EXISTED = "40005";

    // 口味不存在
    String TASTE_NOT_EXISTED = "40006";

    // 菜单项不存在
    String MENU_ITEM_NOT_EXISTED = "40007";

    // 餐厅和菜单项不匹配
    String REST_NOT_MATCH_MENU_ITEM = "40008";

    // 当前菜单项不是单品
    String SINGLE_ITEM_REQUIRED = "40009";

    // 当前菜单项不是套餐
    String SET_ITEM_REQUIRED = "40010";

    // 用户身份凭证已经存在
    String SIGNED_UP_ACCOUNT = "40901";
}
