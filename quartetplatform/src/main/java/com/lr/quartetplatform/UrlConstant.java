package com.lr.quartetplatform;

public class UrlConstant {
    public static final String BASE_URL = "https://nztser.shienkeji.com/api/";
    public static final String IMAGE_BASE_URL = "https://nztser.shienkeji.com";

    public static final String BANNER = BASE_URL + "index/banner";
    public static final String HOME_INFO = BASE_URL + "index/home_info";
    public static final String CUSTOMIZATION = BASE_URL + "index/customization";
    // 商品列表
    public static final String GOODS_LIST = BASE_URL + "shop/goodsList";
    // 上传图片
    public static final String COMMON_UPLOAD = BASE_URL + "common/upload";
    // 定制
    public static final String USER_CUSTOMIZATION_CREATE = BASE_URL + "user/customizationCreate";
    // 发送短信
    public static final String INDEX_SENDSMS = BASE_URL + "index/sendSms";
    // 注册
    public static final String USER_REGISTER = BASE_URL + "user/register";
    // 协议
    public static final String USER_REGISTRATIONINFO = BASE_URL + "user/registrationInfo";
    // 用户登录
    public static final String USER_LOGIN = BASE_URL + "user/login";


    public static final int TEST_TIME = 10;
    public static final int RELEASE_TIME = 60;

    public static final int LOGIN = 1;
}
