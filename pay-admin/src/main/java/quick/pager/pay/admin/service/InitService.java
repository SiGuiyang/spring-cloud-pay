package quick.pager.pay.admin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import quick.pager.pay.Constants;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.mapper.common.SystemConfigMapper;
import quick.pager.pay.model.common.SystemConfig;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class InitService implements IService {

    @Autowired
    private SystemConfigMapper systemConfigMapper;
    @Autowired
    private RedisService redisService;

    @PostConstruct
    private void init() {
        // 不重复的configName添加到redis缓存中
        List<SystemConfig> list = systemConfigMapper.selectSystemConfig(null, 1, 0, Constants.DatabaseColumn.id.name());
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(l -> {
                String configName = l.getConfigName().trim();
                redisService.set(configName, l, 30 * 24 * 60 * 60);
            });
        }

        // 将重复的configName添加到redis缓存中
        List<String> configNames = systemConfigMapper.selectDistinctConfig();

        configNames.forEach(configName -> {
            List<SystemConfig> distinct = systemConfigMapper.selectSystemConfig(configName, 1, 1, Constants.DatabaseColumn.id.name());
            ArrayList<SystemConfig> copy = new ArrayList<>(distinct);
            redisService.set(configName.trim(), copy, 30 * 24 * 60 * 60);
        });
    }
    @Override
    public Response doService(BaseDTO dto) {
        init();
        return new Response();
    }
}
