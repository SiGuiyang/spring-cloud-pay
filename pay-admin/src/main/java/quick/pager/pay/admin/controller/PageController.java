package quick.pager.pay.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import quick.pager.pay.request.pay.Request;

@Controller
@Api(description = "页面控制器")
public class PageController {

    @ApiOperation("首页跳转页面地址")
    @RequestMapping("/")
    public String home(Request request) {
        return "pages/index";
    }

}
