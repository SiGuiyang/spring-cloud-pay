package quick.pager.pay.alipay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quick.pager.pay.dto.BaseDTO;
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

    @Override
    public Response<String> doService(BaseDTO dto) {

        return new Response<>();
    }
}
