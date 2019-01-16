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
import quick.pager.pay.weixin.dto.WeChatPayConfigDTO;
import quick.pager.pay.weixin.dto.WeChatPayDTO;
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
public class MpPayService implements IService<WeChatPayConfigDTO> {
    @Override
    public Response<WeChatPayConfigDTO> doService(BaseDTO dto) {
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

        Response<WeChatPayConfigDTO> response = new Response<>();
        // 处理微信返回的参数
        if (!StringUtils.isEmpty(result)) {
            Map<String, Object> toMap = XmlUtil.xmlToMap(result);

            WeChatPayConfigDTO payConfigDTO = JSON.parseObject(JSON.toJSONString(toMap), WeChatPayConfigDTO.class);
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


}
