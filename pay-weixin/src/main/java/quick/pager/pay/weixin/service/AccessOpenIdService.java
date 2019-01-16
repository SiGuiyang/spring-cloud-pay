package quick.pager.pay.weixin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.weixin.dto.MpWeChatPayDTO;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

/**
 * 微信授权token服务
 */
@Service
@Slf4j
public class AccessOpenIdService implements IService<MpWeChatPayDTO> {

//    @Autowired
//    private OrderMapper orderMapper;
//    @Autowired
//    private PayChannelMapper payChannelMapper;

    @Override
    public Response<MpWeChatPayDTO> doService(BaseDTO dto) {

        log.info("获取支付的access token 。。。。");

//        AccessTokenDTO accessTokenDTO = (AccessTokenDTO) dto;
//
//        Order order = orderMapper.selectOrderByOrderCode(accessTokenDTO.getOrderCode());
//
//        PayChannel payChannel = payChannelMapper.selectPayChannelByPayType(order.getPayType());
//
//        Map<String, String> params = Maps.newConcurrentMap();
//        params.put("appid", payChannel.getAppId());
//        params.put("secret", payChannel.getSecureKey());
//        params.put("code", accessTokenDTO.getCode());
//        params.put("grant_type", "authorization_code");
//
//        String result = HttpClient.doGet("https://api.weixin.qq.com/sns/oauth2/access_token", params);
//
//        Map<String, String> map = JSON.parseObject(result, Map.class);
//
//        String openid = map.get("openid");
//        Response<MpWeChatPayDTO> response = new Response<>();
//        if (StringUtils.isEmpty(openid)) {
//            log.info("获取openId失败");
//            response.setCode(ResponseStatus.PAY_OPENID_ERROR.code);
//            response.setMsg(ResponseStatus.PAY_OPENID_ERROR.msg);
//            return response;
//        }
//
//        log.info("获取支付 openId = {}", openid);
//
//        MpWeChatPayDTO payDTO = new MpWeChatPayDTO();
//
//        payDTO.setAppId(payChannel.getAppId());
//        payDTO.setSecret(payChannel.getSecureKey());
//        payDTO.setOpenId(openid);
//        payDTO.setMchId(payChannel.getMchId());
//        payDTO.setOrderCode(order.getOrderCode());
//        payDTO.setPayAmount(order.getPayAmount());
//        payDTO.setNotifyUrl(order.getPlatformNotifyUrl());
//        payDTO.setBody(order.getPayBody());
//        payDTO.setClientIp(order.getPayClientId());
//
//        response.setData(payDTO);
//        return response;
        return new Response<>();
    }
}
