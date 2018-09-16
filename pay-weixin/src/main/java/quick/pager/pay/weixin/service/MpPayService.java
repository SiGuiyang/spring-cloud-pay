package quick.pager.pay.weixin.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.XmlUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import quick.pager.pay.Constants;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.common.utils.HttpClient;
import quick.pager.pay.common.utils.SignUtils;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.dto.pay.PayConfigDTO;
import quick.pager.pay.dto.pay.WeChatPayDTO;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 公众号支付服务
 */
@Service
@Slf4j
public class MpPayService implements IService<PayConfigDTO> {
    @Override
    public Response<PayConfigDTO> doService(BaseDTO dto) {
        WeChatPayDTO weChatPayDTO = (WeChatPayDTO) dto;
        log.info("进入公众号支付 orderCode = {}", weChatPayDTO.getOutTradeNo());
        log.info("公众号入参 params = {}", JSON.toJSONString(weChatPayDTO));

        SortedMap<String, String> payMap = new TreeMap<>();

        payMap.put("appid", weChatPayDTO.getAppid());
        payMap.put("mch_id", weChatPayDTO.getMchId());
        payMap.put("nonce_str", RandomUtil.simpleUUID());
        payMap.put("sign_type", Constants.SignType.MD5.name());
        payMap.put("body", weChatPayDTO.getBody());
        payMap.put("out_trade_no", weChatPayDTO.getOutTradeNo());
        payMap.put("total_fee", weChatPayDTO.getTotalFee());
        payMap.put("spbill_create_ip", weChatPayDTO.getSpbillCreateIp());
        payMap.put("notify_url", weChatPayDTO.getNotifyUrl());
        payMap.put("trade_type", "JSAPI");
        payMap.put("openid", weChatPayDTO.getOpenid());

        String sign = SignUtils.getSign(Constants.SignType.MD5.name(), weChatPayDTO.getSecret(), payMap);
        payMap.put("sign", sign);

        String result = HttpClient.doPostXml(Constants.PayURL.WEIXIN_PAY_URL, payMap);

        log.info("微信支付返回的结果 result = {}", result);

        Response<PayConfigDTO> response = new Response<>();
        // 处理微信返回的参数
        if (!StringUtils.isEmpty(result)) {
            Map<String, Object> toMap = XmlUtil.xmlToMap(result);

            PayConfigDTO payConfigDTO = JSON.parseObject(JSON.toJSONString(toMap), PayConfigDTO.class);
            String return_code = payConfigDTO.getReturn_code();

            if (return_code.equalsIgnoreCase(Constants.SUCCESS)) {
                log.info("微信通讯成功");
                String result_code = payConfigDTO.getResult_code();

                if (result_code.equalsIgnoreCase(Constants.SUCCESS)) {
                    log.info("微信支付成功！");
                    response.setData(payConfigDTO);

                } else {
                    log.info("微信支付失败");
                    response.setCode(ResponseStatus.PAY_WECHAT_FAIL.code);
                    response.setMsg(ResponseStatus.PAY_WECHAT_FAIL.msg);
                }

            } else {
                log.info("微信通讯失败");
                response.setCode(ResponseStatus.PAY_WECHAT_COMMUNICATION.code);
                response.setMsg(ResponseStatus.PAY_WECHAT_COMMUNICATION.msg);
            }

        }
        return response;
    }

    public static void main(String[] args) {

//        MpPayService service = new MpPayService();
//        WeChatPayDTO dto = new WeChatPayDTO();
//        dto.setSpbillCreateIp("3223232");
//        dto.setBody("e323mkds");
//        dto.setNotifyUrl("23fsdfsdfsdf");
//        dto.setBody("234fdsfdfs");
//        dto.setMchId("90kjdd");
//        dto.setSignType("MD5");
//        dto.setOpenid("3232fsdfsdfsdfs32");
//        dto.setTotalFee("2323");
//        dto.setOutTradeNo("323e2fdsfsdf");
//        dto.setSecret("edfwfdsfsdfsdfsdf");
//        dto.setAppid("323dsfsdfsd");
//
//
//        service.doService(dto);

        String params = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>\n" +
                "   <openid><![CDATA[oUpF8uMuAJO_M2pxb1Q9zNjWeS6o]]></openid>\n" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>\n" +
                "   <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "</xml>";
        Map<String, Object> toMap = XmlUtil.xmlToMap(params);

        System.out.println(toMap);

    }
}
