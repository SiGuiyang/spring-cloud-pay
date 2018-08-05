package quick.pager.pay.alipay.service;

import org.springframework.stereotype.Service;
import quick.pager.pay.dto.BaseDto;
import quick.pager.pay.dto.TestDto;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

/**
 * 测试服务
 * @author siguiyang
 */
@Service
public class TestService implements IService {
    @Override
    public Response doService(BaseDto dto) {
        TestDto testDto = (TestDto) dto;
        Response<TestDto> response = new Response<>();
        response.setData(testDto);
        return response;
    }
}
