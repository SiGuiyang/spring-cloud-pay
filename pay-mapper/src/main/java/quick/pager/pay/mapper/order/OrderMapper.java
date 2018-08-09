package quick.pager.pay.mapper.order;

import quick.pager.pay.mapper.IMapper;
import quick.pager.pay.model.form.Order;
import quick.pager.pay.vo.OrderVO;

import java.util.List;

public interface OrderMapper extends IMapper<Long, Order> {

    /**
     * 查询订单
     */
    List<Order> selectOrders(OrderVO vo);
}
