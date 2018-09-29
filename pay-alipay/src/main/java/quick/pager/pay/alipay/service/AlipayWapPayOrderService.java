package quick.pager.pay.alipay.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quick.pager.pay.alipay.dto.AlipayDTO;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.mapper.pay.OrderMapper;
import quick.pager.pay.mapper.pay.PayChannelMapper;
import quick.pager.pay.model.pay.Order;
import quick.pager.pay.model.pay.PayChannel;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

/**
 * 支付宝WAP支付
 *
 * @author siguiyang
 */
@Service
@Slf4j
public class AlipayWapPayOrderService implements IService<String> {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PayChannelMapper payChannelMapper;

    @Override
    public Response<String> doService(BaseDTO dto) {

        AlipayDTO alipayDTO = (AlipayDTO) dto;

        Order order = orderMapper.selectOrderByOrderCode(alipayDTO.getOrderCode());

        PayChannel payChannel = payChannelMapper.selectPayChannelByPayType(order.getPayType());
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", payChannel.getAppId(), payChannel.getSecureKey(), "json", "UTF-8", payChannel.getPubKey(), "RSA2");
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setReturnUrl(order.getReturnUrl());
        alipayRequest.setNotifyUrl(order.getNotifyUrl());//在公共参数中设置回跳和通知地址

        // 组装支付参数
        AlipayTradeWapPayModel alipayTradeWapPayModel = new AlipayTradeWapPayModel();
        alipayTradeWapPayModel.setTotalAmount(order.getPayAmount().toString());
        alipayTradeWapPayModel.setOutTradeNo(order.getOrderCode());
        alipayTradeWapPayModel.setSubject(order.getPayBody());
        alipayTradeWapPayModel.setProductCode("QUICK_WAP_PAY");

        Response<String> response = new Response<>();

        String form;
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            log.info("支付宝wap支付异常");
            return new Response<>(ResponseStatus.ALIPAY_REQUEST_RESPONSE.code, ResponseStatus.ALIPAY_REQUEST_RESPONSE.msg);
        }

        response.setData(form);
        return response;
    }
}
