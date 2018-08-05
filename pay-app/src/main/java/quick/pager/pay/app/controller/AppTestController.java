package quick.pager.pay.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.pager.pay.app.service.TestClient;
import quick.pager.pay.request.Request;

@RestController
public class AppTestController {
    @Autowired
    private TestClient testClient;

    @PostMapping("/hello")
    public Object hello(Request request){
        request.setMobile("1234567890098");
        return testClient.test(request);
    }

    @PostMapping("/hello/{name}")
    public Object doHello(@PathVariable("name") String name){
        return testClient.doTest(name);
    }
}
