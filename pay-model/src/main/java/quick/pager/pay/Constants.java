package quick.pager.pay;

/**
 * 系统配置的常量
 */
public interface Constants {
    /**
     * 管理员平台
     */
    String ADMIN_MODULE = "/admin";
    /**
     * 支付模块
     */
    String PAY_MODULE = "/pay";
    /**
     * UTF-8编码
     */
    String ENCODE = "UTF-8";
    // 微信支付前缀
    String WECHAT_PREFIX = "WECHAT";

    enum DatabaseColumn {
        id, sequence, server_status, create_time
    }

    /**
     * 操作模式
     */
    enum Operation{
        cache, //缓存
        list, // 列表
        select, // 查询
        delete, // 删除
        update //更新
    }

    /**
     * 支付查询地址
     */
    interface PayURL {
        /**
         * 统一支付地址
         */
        String WEIXIN_PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        /**
         * 微信查询订单地址
         */
        String WEIXIN_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
        /**
         * 微信关闭订单地址
         */
        String WEIXIN_CLOSE_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
        /**
         * 微信申请退款地址
         */
        String WEIXIN_APPLY_REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
        /**
         * 微信查询退款地址
         */
        String WEIXIN_QUERY_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
        /**
         * 微信下载对账单
         */
        String WEIXIN_DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
        /**
         * 微信下载资金账单
         */
        String WEIXIN_DOWNLOAD_FUND_FLOW_URL = "https://api.mch.weixin.qq.com/pay/downloadfundflow";
        /**
         * 交易保障
         */
        String WEIXIN_REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
        /**
         * 支付宝统一支付请求地址
         */
        String ALIPAY_URL = "https://openapi.alipay.com/gateway.do";
    }

    interface Keys {
        // 角色代码
        String ADMIN_ROLE_CODE = "admin_role_code";
        // 支付渠道的支付方式
        String PAY_CENTER_PAY_TYPE = "pay_center_pay_type";
        // 银行代码
        String BANK_CODE = "bank_code";

        String ALIPAY_NOTIFY_URL = "";

        String WEIXIN_NOTIFY_URL = "";
        // 当前登陆管理平台的redis key
        String ADMIN_LOGIN_USER = "admin_login_user_";
    }

    /**
     * 通用字符串
     */
    interface Common {
        String COMMON_ZERO = "0";
        String COMMON_ONE = "1";
        String COMMON_TWO = "2";
        String COMMON_THREE = "3";
        String COMMON_FOUR = "4";
        String COMMON_FIVE = "5";
        String COMMON_SIX = "6";
        String COMMON_SEVEN = "7";
        String COMMON_EIGHT = "8";
        String COMMON_NINE = "9";
    }

    interface PayStatus {
        int created = 0;

    }

    interface NotificationStatus {
        int publish = 0;
    }


    interface TradeType {
        String H5_TRADE_TYPE = "";
        String APP_TRADE_TYPE = "";

        String JSAPI_TRADE_TYPE = "";

        String NATIVE_TRADE_TYPE = "";
    }

    /**
     * 签名加密方式
     */
    enum SignType {
        MD5, AES, RSA2, HmacSHA256;
    }

    /**
     * 支付方式
     */
    interface PayType {
        String WECHAT_H5 = "WECHAT_H5"; // 微信H5支付
        String WECHAT_APP = "WECHAT_APP"; // 微信APP支付
        String WECHAT_PUBLIC_NUMBER = "WECHAT_PUBLIC_NUMBER"; // 微信公众号支付
        String WECHAT_SCAN_QR = "WECHAT_SCAN_QR"; // 微信扫码支付
        String ALIPAY_H5 = "ALIPAY_H5"; // 支付宝H5（Wap）支付
        String AlIPAY_APP = "AlIPAY_APP"; // 支付宝APP支付
        String ALIPAY_WEB = "ALIPAY_WEB"; // 支付宝网站支付
        String ALIPAY_SCAN_QR = "ALIPAY_SCAN_QR"; //支付宝扫码支付
    }

}
