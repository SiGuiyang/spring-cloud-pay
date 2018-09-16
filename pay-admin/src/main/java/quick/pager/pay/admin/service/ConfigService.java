package quick.pager.pay.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import quick.pager.pay.Constants;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.dto.admin.ConfigDTO;
import quick.pager.pay.mapper.common.SystemConfigMapper;
import quick.pager.pay.model.common.SystemConfig;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

import java.util.Date;
import java.util.List;

/**
 * 系统配置项
 */
@Service
@Slf4j
public class ConfigService implements IService {

    @Autowired
    private SystemConfigMapper systemConfigMapper;
    @Autowired
    private InitService initService;

    @Override
    public Response doService(BaseDTO dto) {

        ConfigDTO config = (ConfigDTO) dto;

        Response response = null;
        Constants.Operation operation = config.getOperation();
        switch (operation) {
            case list:
                response = queryConfigList(config);
                break;
            case cache:
                response = modifyConfigRedisCache();
                break;
            case select:
                response = queryConfigInfo(dto.getId());
                break;
            case update:
                response = updateConfig(config);
                break;
        }
        return response;
    }


    /**
     * 查询配置项列表
     */
    private Response queryConfigList(ConfigDTO dto) {

        Response<List<SystemConfig>> response = new Response<>();
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<SystemConfig> configList = systemConfigMapper.selectSystemConfig(dto.getConfigName(), null, null, Constants.DatabaseColumn.sequence.name());

        PageInfo<SystemConfig> pageInfo = new PageInfo<>(configList);
        response.setData(pageInfo.getList());
        response.setTotal(pageInfo.getTotal());

        return response;
    }

    /**
     * 查看单独一个配置
     */
    private Response<SystemConfig> queryConfigInfo(Long configId) {
        SystemConfig systemConfig = systemConfigMapper.selectByPrimaryKey(configId);
        Response<SystemConfig> resp = new Response<>();
        resp.setData(systemConfig);
        return resp;
    }

    /**
     * 更新配置
     */
    private Response updateConfig(ConfigDTO dto) {

        SystemConfig systemConfig = systemConfigMapper.selectByPrimaryKey(dto.getId());
        if (ObjectUtils.isEmpty(systemConfig)) {
            return new Response(ResponseStatus.PARAMETER_ERROR.code, ResponseStatus.PARAMETER_ERROR.msg);
        }

        SystemConfig updateSystemConfig = new SystemConfig();
        updateSystemConfig.setId(dto.getId());
        updateSystemConfig.setConfigName(dto.getConfigName());
        updateSystemConfig.setConfigValue(dto.getConfigValue());
        updateSystemConfig.setDescription(dto.getDescription());
        updateSystemConfig.setSequence(dto.getSequence());
        updateSystemConfig.setServerStatus(dto.getServerStatus());
        updateSystemConfig.setVersion(dto.getVersion());
        updateSystemConfig.setUpdateTime(new Date());
        systemConfigMapper.updateByPrimaryKeySelective(updateSystemConfig);
        return new Response();
    }

    /**
     * 更新配置缓存
     */
    private Response modifyConfigRedisCache() {

        return initService.doService(null);
    }
}
