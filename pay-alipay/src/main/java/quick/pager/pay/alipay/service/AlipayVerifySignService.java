package quick.pager.pay.alipay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quick.pager.pay.alipay.dto.VerifySignDTO;
import quick.pager.pay.dto.BaseDTO;
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

    @Override
    public Response doService(BaseDTO dto) {
        VerifySignDTO verifySignDTO = (VerifySignDTO) dto;

//        SortedMap<String, String> postMap = new TreeMap<>();
//        postMap.put("orderCode", verifySignDTO.getOrderCode());
//        postMap.put("timestamp", verifySignDTO.getTimestamp());
//        postMap.put("nonceStr", verifySignDTO.getNonceStr());
//        postMap.put("sign_type", verifySignDTO.getSignType());

        return new Response();
    }
}
