package quick.pager.pay.mapper.pay;

import org.apache.ibatis.annotations.Param;
import quick.pager.pay.mapper.IMapper;
import quick.pager.pay.model.pay.PayChannel;

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

    /**
     * 根据支付方式获取当前支付可用的支付渠道配置
     *
     * @param payType 支付方式
     */
    PayChannel selectPayChannelByPayType(@Param("payType") String payType);

    /**
     * 根据appid 获取支付渠道
     *
     * @param appId appId
     */
    PayChannel selectPayChannelByAppId(@Param("appId") String appId);
}