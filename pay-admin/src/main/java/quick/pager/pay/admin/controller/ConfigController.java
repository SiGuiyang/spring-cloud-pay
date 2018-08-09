package quick.pager.pay.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.pager.pay.admin.service.ConfigService;
import quick.pager.pay.Constants;
import quick.pager.pay.dto.admin.ConfigDto;
import quick.pager.pay.request.admin.ConfigRequest;
import quick.pager.pay.response.Response;


@RestController
@RequestMapping(Constants.ADMIN_MODULE)
@Api(description = "配置管理")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @ApiModelProperty("配置项列表")
    @PostMapping("/config/list")
    public Response configList(ConfigRequest request) {
        ConfigDto dto = new ConfigDto();
        dto.setBeginTime(request.getBeginTime());
        dto.setEndTime(request.getEndTime());
        dto.setConfigName(request.getConfigName());
        dto.setConfigValue(request.getConfigValue());
        dto.setDescription(request.getDescription());
        dto.setSequence(request.getSequence());
        dto.setOperation(Constants.Operation.list);
        return configService.doService(dto);
    }

    @ApiOperation("单个配置项信息")
    @PostMapping("/config/info")
    public Response configInfo(ConfigRequest request) {
        ConfigDto dto = new ConfigDto();
        dto.setId(request.getId());
        dto.setOperation(Constants.Operation.select);
        return configService.doService(dto);
    }

    @ApiModelProperty("修改配置项")
    @PostMapping("/config/modify")
    public Response modifyConfig(ConfigRequest request) {
        ConfigDto dto = new ConfigDto();
        dto.setConfigName(request.getConfigName());
        dto.setConfigValue(request.getConfigValue());
        dto.setDescription(request.getDescription());
        dto.setSequence(request.getSequence());
        dto.setOperation(Constants.Operation.update);
        return configService.doService(dto);
    }

    @ApiOperation("更新缓存")
    @PostMapping("/config/cache")
    public Response modifyConfigRedisCache(ConfigRequest request) {
        ConfigDto dto = new ConfigDto();
        dto.setConfigName(request.getConfigName());
        dto.setConfigValue(request.getConfigValue());
        dto.setDescription(request.getDescription());
        dto.setSequence(request.getSequence());
        dto.setOperation(Constants.Operation.cache);
        return configService.doService(dto);
    }

}
