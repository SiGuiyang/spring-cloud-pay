package quick.pager.pay.mapper.pay;

import org.apache.ibatis.annotations.Param;
import quick.pager.pay.mapper.IMapper;
import quick.pager.pay.model.PayChannel;

import java.util.List;

public interface PayChannelMapper extends IMapper<Long, PayChannel> {

    /**
     * 查看支付配置
     */
    List<PayChannel> selectPayChannel(@Param("channelName") String channelName, @Param("payChannelCenterId") Long payChannelCenterId);

    /**
     * 根据支付中心的Id和支付方式查询支付渠道
     */
    PayChannel selectPayChannelEnable(@Param("payChannelCenterId") Long payChannelCenterId, @Param("payType") String payType);
}