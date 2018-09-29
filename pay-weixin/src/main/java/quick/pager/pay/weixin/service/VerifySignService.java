package quick.pager.pay.weixin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import quick.pager.pay.Constants;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.common.utils.SignUtils;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.weixin.dto.WeChatVerifyDTOWeChat;
import quick.pager.pay.mapper.merchant.MerchantMapper;
import quick.pager.pay.mapper.pay.OrderMapper;
import quick.pager.pay.model.merchant.Merchant;
import quick.pager.pay.model.pay.Order;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;
import quick.pager.pay.vo.pay.WeiXinSignVO;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 验证签名服务
 */
@Service
@Slf4j
public class VerifySignService implements IService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public Response doService(BaseDTO dto) {
        WeChatVerifyDTOWeChat weChatVerifyDTO = (WeChatVerifyDTOWeChat) dto;
        log.info("商户开始验签 merchantOrderNo = {}", weChatVerifyDTO.getOrderCode());

        Response<WeiXinSignVO> response = new Response<>();

        SortedMap<String, String> postMap = new TreeMap<>();
        postMap.put("orderCode", weChatVerifyDTO.getOrderCode());
        postMap.put("timestamp", weChatVerifyDTO.getTimestamp());
        postMap.put("nonceStr", weChatVerifyDTO.getNonceStr());

        if (!StringUtils.isEmpty(weChatVerifyDTO.getAppId())){
            postMap.put("appId", weChatVerifyDTO.getAppId());

        }

        if (!StringUtils.isEmpty(weChatVerifyDTO.getMchId())){
            postMap.put("mchId", weChatVerifyDTO.getMchId());
        }

        if (!StringUtils.isEmpty(weChatVerifyDTO.getNotifyUrl())) {
            postMap.put("notifyUrl", weChatVerifyDTO.getNotifyUrl());
        }
        if (!StringUtils.isEmpty(weChatVerifyDTO.getScope())) {
            postMap.put("scope", weChatVerifyDTO.getScope());
        }

        Order order = orderMapper.selectOrderByOrderCode(weChatVerifyDTO.getOrderCode());
        if (ObjectUtils.isEmpty(order)) {
            log.info("订单号异常 orderCode = {}", weChatVerifyDTO.getOrderCode());
            response.setCode(ResponseStatus.UNKNOWN_PAY_MERCHANT_ORDER_CODE.code);
            response.setMsg(ResponseStatus.UNKNOWN_PAY_MERCHANT_ORDER_CODE.msg);
            return response;
        }

        Merchant merchant = merchantMapper.selectMerchantByMerchantNo(order.getMerchantNo());

        String sign = SignUtils.getSign(Constants.SignType.MD5.name(), merchant.getPrivateKey(), postMap);

        // 验证签名
        if (!sign.equals(weChatVerifyDTO.getSign())) {
            log.info("验证签名不成确 orderCode = {}", weChatVerifyDTO.getOrderCode());
            response.setCode(ResponseStatus.UNKNOWN_SIGN.code);
            response.setMsg(ResponseStatus.UNKNOWN_SIGN.msg);
            return response;
        }

        log.info("验证签名成功 merchantOrderNo = {}", weChatVerifyDTO.getOrderCode());
        return response;
    }


    public static void main(String[] args) {
        SortedMap<String, String> postMap = new TreeMap<>();
        postMap.put("appid", "wxd930ea5d5a258f4f");
        postMap.put("mch_id", "10000100");
        postMap.put("device_info", "1000");
        postMap.put("body", "test");
        postMap.put("nonce_str", "ibuaiVcKdpRxkhJA");


        String sign = SignUtils.getSign(Constants.SignType.MD5.name(), "192006250b4c09247ec02edce69f6a2d", postMap).toUpperCase();

        System.out.println(sign.equals("9A0A8659F005D6984697E2CA0A9CF3B7"));


//        String join = Joiner.on("&").withKeyValueSeparator("=").join(postMap);
//        System.out.println("===========" + join);
//        StringBuilder builder = new StringBuilder(join);
//        String result = getSign("192006250b4c09247ec02edce69f6a2d", builder, Constants.SignType.MD5.name()).toUpperCase();
//        System.out.println(result);
//        System.out.println(result.equals("9A0A8659F005D6984697E2CA0A9CF3B7"));
    }
}
