package quick.pager.pay.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import quick.pager.pay.common.mapper.IMapper;
import quick.pager.pay.model.pay.Order;

import java.util.List;

@Mapper
public interface OrderMapper extends IMapper<Long, Order> {

    /**
     * 通过订单号查询订单
     *
     * @param orderCode 订单号
     */
    Order selectOrderByOrderCode(@Param("orderCode") String orderCode);
}
