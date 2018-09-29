package quick.pager.pay.weixin.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.XmlUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quick.pager.pay.Constants;
import quick.pager.pay.common.utils.HttpClient;
import quick.pager.pay.common.utils.SignUtils;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.dto.pay.WeChatRefundDTO;
import quick.pager.pay.dto.pay.WeChatRefundResponseDTO;
import quick.pager.pay.mapper.pay.OrderMapper;
import quick.pager.pay.mapper.pay.PayChannelMapper;
import quick.pager.pay.model.pay.Order;
import quick.pager.pay.model.pay.PayChannel;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 退款服务
 */
@Service
@Slf4j
public class WeChatRefundService implements IService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PayChannelMapper payChannelMapper;

    @Override
    public Response doService(BaseDTO dto) {
        WeChatRefundDTO weChatRefundDTO = (WeChatRefundDTO) dto;
        log.info("退款服务入参 params = {}", JSON.toJSONString(weChatRefundDTO));

        Order order = orderMapper.selectOrderByOrderCode(weChatRefundDTO.getOrderCode());
        PayChannel payChannel = payChannelMapper.selectPayChannelByPayType(order.getPayType());

        SortedMap<String, String> postmap = new TreeMap<>();

        postmap.put("appid", payChannel.getAppId());
        postmap.put("mch_id", payChannel.getMchId());
        postmap.put("nonce_str", RandomUtil.simpleUUID());
        postmap.put("out_refund_no", order.getOrderCode());
        postmap.put("out_trade_no", order.getOrderCode());
        postmap.put("refund_fee", order.getRefundAmount().toString());
        postmap.put("total_fee", order.getPayAmount().toString());
        postmap.put("transaction_id", order.getTradeCode());

        String sign = SignUtils.getSign(Constants.SignType.MD5.name(), payChannel.getSecureKey(), postmap);

        postmap.put("sign", sign);


        String result = HttpClient.doPostXml(Constants.PayURL.WEIXIN_APPLY_REFUND_URL, postmap);

        log.info("微信退款结果 result = {}", result);

        Map<String, Object> toMap = XmlUtil.xmlToMap(result);

        WeChatRefundResponseDTO weChatRefundResponseDTO = JSON.parseObject(JSON.toJSONString(toMap), WeChatRefundResponseDTO.class);
        // 微信通讯成功
        if (Constants.SUCCESS.equalsIgnoreCase(weChatRefundResponseDTO.getReturn_code())) {

            if (Constants.SUCCESS.equalsIgnoreCase(weChatRefundResponseDTO.getResult_code())) {
                log.info("微信退款成功 orderCode = {}", weChatRefundDTO.getOrderCode());
            }

        } else {
            log.info("微信通讯失败 orderCode = {}", weChatRefundDTO.getOrderCode());

        }

        return null;
    }
}
