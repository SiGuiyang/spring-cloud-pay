package quick.pager.pay.alipay.service;

import org.springframework.stereotype.Service;
import quick.pager.pay.dto.BaseDto;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

/**
 * 支付宝支付
 */
@Service
public class AlipayPayOrderService implements IService{
    @Override
    public Response doService(BaseDto dto) {
        return null;
    }
}
