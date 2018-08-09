package quick.pager.pay.admin.service;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quick.pager.pay.Constants;
import quick.pager.pay.dto.BaseDto;
import quick.pager.pay.dto.admin.OrderDto;
import quick.pager.pay.mapper.order.OrderMapper;
import quick.pager.pay.model.form.Order;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;
import quick.pager.pay.vo.OrderVO;

import java.util.List;

@Service
@Slf4j
public class OrderService implements IService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Response doService(BaseDto dto) {

        OrderDto orderDto = (OrderDto) dto;

        Constants.Operation operation = orderDto.getOperation();
        Response response = null;
        switch (operation) {
            case list:
                response = queryOrderList(orderDto);
                break;

        }
        return response;
    }

    /**
     * 订单列表
     */
    private Response queryOrderList(OrderDto dto) {
        OrderVO vo = new OrderVO();
        vo.setMerchantNo(dto.getMerchantNo());
        vo.setMerchantOrderCode(dto.getMerchantOrderCode());
        vo.setNotificationStatus(dto.getNotificationStatus());
        vo.setOrderCode(dto.getOrderCode());
        vo.setPayStatus(dto.getPayStatus());
        vo.setPayType(dto.getPayType());
        vo.setBeginTime(DateUtil.formatDateTime(DateUtil.date(dto.getBeginTime())));
        vo.setEndTime(DateUtil.formatDateTime(DateUtil.date(dto.getEndTime())));

        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());

        List<Order> orderList = orderMapper.selectOrders(vo);

        PageInfo<Order> pageInfo = new PageInfo<>(orderList);

        Response<List<Order>> resp = new Response<>();
        resp.setTotal(pageInfo.getTotal());
        resp.setData(pageInfo.getList());

        return resp;
    }
}
