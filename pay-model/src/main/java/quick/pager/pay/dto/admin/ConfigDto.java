package quick.pager.pay.dto.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConfigDto extends BaseDto{
    private static final long serialVersionUID = -7699370371061317882L;

    @ApiModelProperty("配置项名称")
    private String configName;
    @ApiModelProperty("配置项值")
    private String configValue;
    @ApiModelProperty("配置项说明")
    private String description;
    @ApiModelProperty("序号")
    private Integer sequence;
    @ApiModelProperty(value = "版本号")
    private String version;
    @ApiModelProperty(value ="服务状态")
    private Integer serverStatus;

}
