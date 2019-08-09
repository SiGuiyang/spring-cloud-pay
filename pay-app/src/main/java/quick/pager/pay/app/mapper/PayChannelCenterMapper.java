package quick.pager.pay.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import quick.pager.pay.common.mapper.IMapper;
import quick.pager.pay.model.pay.PayChannelCenter;

import java.util.List;

@Mapper
public interface PayChannelCenterMapper extends IMapper<Long, PayChannelCenter> {
    /**
     * 查询支付中心列表
     *
     * @param channelCenterName 支付中心名称
     * @param payType           支付类型
     * @param serverStatus      状态
     */
    List<PayChannelCenter> selectPayChannelCenter(@Param("channelCenterName") String channelCenterName, @Param("payType") String payType, @Param("serverStatus") Integer serverStatus);
}
