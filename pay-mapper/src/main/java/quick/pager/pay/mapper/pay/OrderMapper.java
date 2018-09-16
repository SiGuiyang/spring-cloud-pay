package quick.pager.pay.mapper.pay;

import org.apache.ibatis.annotations.Param;
import quick.pager.pay.mapper.IMapper;
import quick.pager.pay.model.pay.Order;
import quick.pager.pay.vo.admin.OrderVO;

import java.util.List;

public interface OrderMapper extends IMapper<Long, Order> {

    /**
     * 查询订单
     */
    List<Order> selectOrders(OrderVO vo);

    /**
     * 通过订单号查询订单
     *
     * @param orderCode 订单号
     */
    Order selectOrderByOrderCode(@Param("orderCode") String orderCode);
}
