package quick.pager.pay.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import quick.pager.pay.Constants;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.dto.BaseDto;
import quick.pager.pay.dto.admin.ChannelDto;
import quick.pager.pay.mapper.pay.PayChannelMapper;
import quick.pager.pay.model.PayChannel;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ChannelService implements IService {

    @Autowired
    private PayChannelMapper payChannelMapper;

    @Override
    public Response doService(BaseDto dto) {
        ChannelDto channelDto = (ChannelDto) dto;

        Constants.Operation operation = channelDto.getOperation();
        Response response = null;

        switch (operation) {
            case list:
                response = channelList(channelDto);
                break;
            case select:
                response = channelInfo(channelDto.getId());
                break;
            case update:
                response = channelModify(channelDto);
                break;
            case delete:
                response = deleteChannel(channelDto.getId());
                break;

        }
        return response;
    }


    /**
     * 支付配置列表
     */
    private Response channelList(ChannelDto dto) {

        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());

        List<PayChannel> payChannels = payChannelMapper.selectPayChannel(dto.getChannelName(), dto.getPayChannelCenterId());

        PageInfo<PayChannel> pageInfo = new PageInfo<>(payChannels);

        Response<List<PayChannel>> resp = new Response<>();

        resp.setData(pageInfo.getList());
        resp.setTotal(pageInfo.getTotal());

        return resp;
    }

    /**
     * 渠道配置信息
     *
     * @param id 渠道Id
     */
    private Response channelInfo(Long id) {
        PayChannel payChannel = payChannelMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(payChannel)) {
            return new Response(ResponseStatus.PARAMETER_ERROR.code, ResponseStatus.PARAMETER_ERROR.msg);
        }

        Response<PayChannel> response = new Response<>();
        response.setData(payChannel);
        return response;
    }

    /**
     * 新增或者修改渠道
     */
    private Response channelModify(ChannelDto dto) {
        // 添加
        if (ObjectUtils.isEmpty(dto.getId())) {
            PayChannel payChannel = new PayChannel();
            payChannel.setAppId(dto.getAppId());
            payChannel.setOpenId(dto.getOpenId());
            payChannel.setMchId(dto.getMchId());
            payChannel.setPayChannelCenterId(dto.getPayChannelCenterId());
            payChannel.setChannelName(dto.getChannelName());
            payChannel.setCurrentEnable(dto.getCurrentEnable());
            payChannel.setCurrentNode(dto.getCurrentNode());
            payChannel.setDeduction(new BigDecimal(dto.getDeduction()));
            payChannel.setRate(new BigDecimal(dto.getRate()));
            payChannel.setSecureKey(dto.getSecureKey());
            payChannelMapper.insertSelective(payChannel);
        } else { // 修改
            PayChannel payChannel = payChannelMapper.selectByPrimaryKey(dto.getId());
            if (ObjectUtils.isEmpty(payChannel)) {
                return new Response(ResponseStatus.PARAMETER_ERROR.code, ResponseStatus.PARAMETER_ERROR.msg);
            }
            payChannel.setAppId(dto.getAppId());
            payChannel.setOpenId(dto.getOpenId());
            payChannel.setMchId(dto.getMchId());
            payChannel.setPayChannelCenterId(dto.getPayChannelCenterId());
            payChannel.setChannelName(dto.getChannelName());
            payChannel.setCurrentEnable(dto.getCurrentEnable());
            payChannel.setCurrentNode(dto.getCurrentNode());
            payChannel.setDeduction(new BigDecimal(dto.getDeduction()));
            payChannel.setRate(new BigDecimal(dto.getRate()));
            payChannel.setSecureKey(dto.getSecureKey());
            payChannel.setUpdateTime(new Date());
            payChannelMapper.updateByPrimaryKeySelective(payChannel);
        }
        return new Response();
    }

    /**
     * 删除渠道
     *
     * @param id 主键
     */
    private Response deleteChannel(Long id) {
        PayChannel payChannel = payChannelMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(payChannel)) {
            return new Response(ResponseStatus.PARAMETER_ERROR.code, ResponseStatus.PARAMETER_ERROR.msg);
        }

        payChannel.setDeleteStatus(Boolean.TRUE);
        payChannelMapper.updateByPrimaryKeySelective(payChannel);
        return new Response();
    }
}
