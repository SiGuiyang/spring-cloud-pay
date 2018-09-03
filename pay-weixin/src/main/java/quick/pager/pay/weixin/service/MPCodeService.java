package quick.pager.pay.weixin.service;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInRedisConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import quick.pager.pay.dto.BaseDto;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

/**
 * 授权code服务
 */
@Service
@Slf4j
public class MPCodeService implements IService{

    @Autowired
    private RedisTemplate template;

    @Override
    public Response doService(BaseDto dto) {

        return null;
    }

    /**
     * 获取微信公众号调用服务
     */
    public WxMpService getWxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();

//        WxMpConfigStorage wxMpInMemoryConfigStorage = new WxMpInRedisConfigStorage();

        return  wxMpService;
    }
}
