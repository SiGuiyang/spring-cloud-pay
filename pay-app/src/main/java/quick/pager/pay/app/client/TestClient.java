package quick.pager.pay.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import quick.pager.pay.request.Request;
import quick.pager.pay.response.Response;

@FeignClient(value="${feign.client.node.alipay}")
public interface TestClient {

    @RequestMapping(value = "/spring/test",method = RequestMethod.POST)
    Response test(Request request);

    @RequestMapping(value = "/doTest",method = RequestMethod.POST)
    Response doTest(@RequestParam("name") String name);
}
