package quick.pager.pay.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.async.DeferredResult;
import quick.pager.pay.app.actor.ActorExecutor;
import quick.pager.pay.app.actor.TestActor;
import quick.pager.pay.app.function.DeferredResultFunction;
import quick.pager.pay.dto.actor.TestDTO;
import quick.pager.pay.response.PayResponse;
import quick.pager.pay.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayAppApplicationTests {

    @Test
    public void contextLoads() {

        TestDTO dto = TestDTO.builder().merchantNo("33333333").merchantNo("jdll3992092032").mobile("100.00").build();


        DeferredResult<Response> deferredResult = ActorExecutor.execute(TestActor.class, "test-actor", dto, (DeferredResultFunction<PayResponse, Response>) (failure, payResponse, result) -> {
            if (ObjectUtils.isEmpty(failure)) {
                Response<String> resp = new Response<>();
                resp.setCode(200);
                resp.setData("success");
                result.setResult(resp);
            } else {
                result.setErrorResult("333333");
            }
            System.out.println("000000000000000"+payResponse.getMerchantNo());


        });

        System.out.println(deferredResult);

    }

}
