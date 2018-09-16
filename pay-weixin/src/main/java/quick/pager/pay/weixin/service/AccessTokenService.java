package quick.pager.pay.weixin.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.common.utils.HttpClient;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.dto.pay.AccessTokenDTO;
import quick.pager.pay.dto.pay.MpPayDTO;
import quick.pager.pay.mapper.pay.OrderMapper;
import quick.pager.pay.mapper.pay.PayChannelMapper;
import quick.pager.pay.model.pay.Order;
import quick.pager.pay.model.pay.PayChannel;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

import java.util.Map;

/**
 * 微信授权token服务
 */
@Service
@Slf4j
public class AccessTokenService implements IService<MpPayDTO> {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PayChannelMapper payChannelMapper;

    @Override
    public Response<MpPayDTO> doService(BaseDTO dto) {

        log.info("获取支付的access token 。。。。");

        AccessTokenDTO accessTokenDTO = (AccessTokenDTO) dto;

        Order order = orderMapper.selectOrderByOrderCode(accessTokenDTO.getOrderCode());

        PayChannel payChannel = payChannelMapper.selectPayChannelByPayType(order.getPayType());

        Map<String, String> params = Maps.newConcurrentMap();
        params.put("appid", payChannel.getAppId());
        params.put("secret", payChannel.getSecureKey());
        params.put("code", accessTokenDTO.getCode());
        params.put("grant_type", "authorization_code");

        String result = HttpClient.doGet("https://api.weixin.qq.com/sns/oauth2/access_token", params);

        Map<String, String> map = JSON.parseObject(result, Map.class);

        String openid = map.get("openid");
        Response<MpPayDTO> response = new Response<>();
        if (null == openid) {
            log.info("获取openId失败");
            response.setCode(ResponseStatus.PAY_OPENID_ERROR.code);
            response.setMsg(ResponseStatus.PAY_OPENID_ERROR.msg);
            return response;
        }

        log.info("获取支付 openId = {}", openid);

        MpPayDTO payDTO = new MpPayDTO();

        payDTO.setAppId(payChannel.getAppId());
        payDTO.setSecret(payChannel.getSecureKey());
        payDTO.setOpenId(openid);
        payDTO.setMchId(payChannel.getMchId());
        payDTO.setOrderCode(order.getOrderCode());
        payDTO.setPayAmount(order.getPayAmount());
        payDTO.setNotifyUrl(order.getPlatformNotifyUrl());
        payDTO.setBody(order.getPayBody());
        payDTO.setClientIp(order.getPayClientId());

        response.setData(payDTO);
        return response;
    }
}
