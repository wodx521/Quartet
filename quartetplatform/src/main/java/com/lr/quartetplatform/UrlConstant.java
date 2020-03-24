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
    // 修改昵称或头像
    public static final String USER_PROFILE = BASE_URL + "user/profile";
    // 用户信息
    public static final String USER_INDEX = BASE_URL + "user/index";
    // 重置密码
    public static final String USER_RESETPWD = BASE_URL + "user/resetpwd";
    // 我的足迹
    public static final String SHOP_MYGOODSLIST = BASE_URL + "shop/myGoodsList";
    // 入驻类型
    public static final String INDEX_SUPPLIE_TYPE = BASE_URL + "index/supplie_type";
    // 入驻提交
    public static final String INDEX_SETTLEDSUPPLIERS = BASE_URL + "user/settledSuppliers";
    // 单类型商品列表
    public static final String SHOP_GOODSLIST = BASE_URL + "shop/goodsList";
    // 过滤条件
    public static final String INDEX_GOODSFILTER = BASE_URL + "index/goodsFilter";
    // 商品详情
    public static final String SHOP_GOODSDETAIL = BASE_URL + "shop/goodsDetail";
    // 经理人
    public static final String SHOP_BPS = BASE_URL + "shop/bps";
    // 预约服务
    public static final String USER_FORWARD = BASE_URL + "user/forward";
    // 新闻地址
    public static final String NEWS_BANNER = "https://unidemo.dcloud.net.cn/api/banner/36kr";
    // 新闻列表
    public static final String NEWS_LIST = "https://unidemo.dcloud.net.cn/api/news";
    // 新闻详情
    public static final String NEWS_DETAIL = "https://unidemo.dcloud.net.cn/api/news/36kr/";


    public static final String CACHE_CONSTANT = "userInfo";

    public static final String CACHE_USER = "user";

    public static final int LOGIN = 1;
    public static final int TEST_TIME = 10;
    public static final int RELEASE_TIME = 60;


}
