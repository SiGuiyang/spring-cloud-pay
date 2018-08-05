package quick.pager.pay.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.pager.pay.admin.service.OrderService;
import quick.pager.pay.Constants;
import quick.pager.pay.dto.admin.OrderDto;
import quick.pager.pay.request.admin.OrderRequest;
import quick.pager.pay.response.Response;


@RestController
@RequestMapping(Constants.ADMIN_MODULE)
@Api(description = "订单管理")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("订单列表")
    @PostMapping("/order/list")
    public Response orderList(OrderRequest request) {
        OrderDto dto = new OrderDto();
        dto.setOperation(Constants.Operation.list);

        return orderService.doService(dto);
    }
}
