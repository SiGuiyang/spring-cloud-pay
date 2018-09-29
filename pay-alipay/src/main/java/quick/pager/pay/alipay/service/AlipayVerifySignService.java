package quick.pager.pay.alipay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import quick.pager.pay.Constants;
import quick.pager.pay.alipay.dto.VerifySignDTO;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.common.utils.SignUtils;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.mapper.merchant.MerchantMapper;
import quick.pager.pay.mapper.pay.OrderMapper;
import quick.pager.pay.model.merchant.Merchant;
import quick.pager.pay.model.pay.Order;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 验证签名
 *
 * @author siguiyang
 */
@Service
@Slf4j
public class AlipayVerifySignService implements IService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public Response doService(BaseDTO dto) {
        VerifySignDTO verifySignDTO = (VerifySignDTO) dto;

        SortedMap<String, String> postMap = new TreeMap<>();
        postMap.put("orderCode", verifySignDTO.getOrderCode());
        postMap.put("timestamp", verifySignDTO.getTimestamp());
        postMap.put("nonceStr", verifySignDTO.getNonceStr());
        postMap.put("sign_type", verifySignDTO.getSignType());

        Order order = orderMapper.selectOrderByOrderCode(verifySignDTO.getOrderCode());

        if (ObjectUtils.isEmpty(order)) {
            log.info("订单号异常 orderCode = {}", verifySignDTO.getOrderCode());
            return new Response(ResponseStatus.UNKNOWN_PAY_MERCHANT_ORDER_CODE.code, ResponseStatus.UNKNOWN_PAY_MERCHANT_ORDER_CODE.msg);
        }

        Merchant merchant = merchantMapper.selectMerchantByMerchantNo(order.getMerchantNo());

        String sign = SignUtils.getSign(Constants.SignType.MD5.name(), merchant.getPrivateKey(), postMap);

        // 验证签名
        if (!sign.equals(verifySignDTO.getSign())) {
            log.info("验证签名不成确 orderCode = {}", verifySignDTO.getOrderCode());
            return new Response(ResponseStatus.UNKNOWN_SIGN.code, ResponseStatus.UNKNOWN_SIGN.msg);
        }

        log.info("验证签名成功 merchantOrderNo = {}", verifySignDTO.getOrderCode());

        return new Response();
    }
}
