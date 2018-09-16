package quick.pager.pay.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import quick.pager.pay.Constants;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.dto.admin.ChannelCenterDto;
import quick.pager.pay.mapper.pay.PayChannelCenterMapper;
import quick.pager.pay.mapper.user.UserMapper;
import quick.pager.pay.model.pay.PayChannelCenter;
import quick.pager.pay.model.user.User;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ChannelCenterService implements IService {

    @Autowired
    private PayChannelCenterMapper payChannelCenterMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public Response doService(BaseDTO dto) {
        ChannelCenterDto channelCenterDto = (ChannelCenterDto) dto;
        Constants.Operation operation = channelCenterDto.getOperation();

        switch (operation) {
            case list:
                return channelCenterList(channelCenterDto);
            case select:
                return channelCenterInfo(channelCenterDto.getId());
            case delete:
                return deleteChannelCenter(channelCenterDto.getId());
            case update:
                return modifyChannelCenter(channelCenterDto);

        }

        return null;
    }

    /**
     * 支付中心列表
     */
    private Response channelCenterList(ChannelCenterDto dto) {

        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<PayChannelCenter> payChannelCenters = payChannelCenterMapper.selectPayChannelCenter(dto.getChannelCenterName(), dto.getPayType(), dto.getServerStatus());

        PageInfo<PayChannelCenter> pageInfo = new PageInfo<>(payChannelCenters);

        Response<List<PayChannelCenter>> resp = new Response<>();
        resp.setTotal(pageInfo.getTotal());
        resp.setData(pageInfo.getList());

        return resp;
    }

    /**
     * 支付中心详情
     */
    public Response channelCenterInfo(Long id) {
        Response<PayChannelCenter> response = new Response<>();

        PayChannelCenter payChannelCenter = payChannelCenterMapper.selectByPrimaryKey(id);
        if (!ObjectUtils.isEmpty(payChannelCenter)) {
            response.setData(payChannelCenter);
        } else {
            response.setCode(ResponseStatus.UNKNOWN_CHANNEL_CENTER.code);
            response.setMsg(ResponseStatus.UNKNOWN_CHANNEL_CENTER.msg);
        }

        return response;
    }

    /**
     * 删除支付中心
     */
    private Response deleteChannelCenter(Long id) {
        PayChannelCenter payChannelCenter = payChannelCenterMapper.selectByPrimaryKey(id);

        if (ObjectUtils.isEmpty(payChannelCenter)) {
            return new Response(ResponseStatus.UNKNOWN_CHANNEL_CENTER.code, ResponseStatus.UNKNOWN_CHANNEL_CENTER.msg);
        }
        payChannelCenter.setDeleteStatus(true);
        payChannelCenter.setUpdateTime(new Date());
        payChannelCenterMapper.updateByPrimaryKeySelective(payChannelCenter);
        return new Response();
    }

    /**
     * 修改支付中心配置
     */
    public Response modifyChannelCenter(ChannelCenterDto dto) {
        String token = dto.getToken();

        String userId = redisService.get(token);

        if (StringUtils.isEmpty(userId)) {
            return new Response(ResponseStatus.LOGIN_TOKEN_EXPIRE.code, ResponseStatus.LOGIN_TOKEN_EXPIRE.msg);
        }

        User user = userMapper.selectByPrimaryKey(Long.parseLong(userId));

        if (ObjectUtils.isEmpty(user)) {
            return new Response(ResponseStatus.USER_NOT_EXISTS.code, ResponseStatus.USER_NOT_EXISTS.msg);
        }

        // 新增
        if (ObjectUtils.isEmpty(dto.getId())) {
            PayChannelCenter payChannelCenter = new PayChannelCenter();
            payChannelCenter.setChannelCenterName(dto.getChannelCenterName());
            payChannelCenter.setPayType(dto.getPayType());
            payChannelCenter.setServerStatus(dto.getServerStatus());
            payChannelCenter.setCreateUserName(user.getUsername());
            payChannelCenter.setUpdateUserName(user.getUsername());
            payChannelCenterMapper.insertSelective(payChannelCenter);
        } else { //修改
            PayChannelCenter payChannelCenter = payChannelCenterMapper.selectByPrimaryKey(dto.getId());
            if (ObjectUtils.isEmpty(payChannelCenter)) {
                return new Response(ResponseStatus.PARAMETER_ERROR.code, ResponseStatus.PARAMETER_ERROR.msg);
            }
            payChannelCenter.setServerStatus(dto.getServerStatus());
            payChannelCenter.setPayType(dto.getPayType());
            payChannelCenter.setChannelCenterName(dto.getChannelCenterName());
            payChannelCenter.setUpdateUserName(user.getUsername());
            payChannelCenter.setUpdateTime(new Date());
            payChannelCenterMapper.updateByPrimaryKeySelective(payChannelCenter);
        }
        return new Response();
    }
}
