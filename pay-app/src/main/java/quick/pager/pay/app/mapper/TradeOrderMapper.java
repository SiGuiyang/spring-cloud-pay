package quick.pager.pay.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import quick.pager.pay.common.mapper.IMapper;
import quick.pager.pay.model.pay.TradeOrder;

@Mapper
public interface TradeOrderMapper extends IMapper<Long, TradeOrder> {

}
