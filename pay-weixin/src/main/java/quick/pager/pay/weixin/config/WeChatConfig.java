package quick.pager.pay.weixin.config;

import lombok.Data;

@Data
public class WeChatConfig {
    /**
     * appId
     */
    private String appId;
    /**
     * 商户Id 商户号
     */
    private String mchId;
    /**
     * 商户密钥
     */
    private String secureKey;

}
