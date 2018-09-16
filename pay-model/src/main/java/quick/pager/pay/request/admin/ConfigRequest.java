package quick.pager.pay.request.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import quick.pager.pay.request.pay.Request;

@Data
public class ConfigRequest extends Request{
    private static final long serialVersionUID = -7620720672426494284L;

    @ApiModelProperty("配置项名称")
    private String configName;
    @ApiModelProperty("配置项值")
    private String configValue;
    @ApiModelProperty("配置项说明")
    private String description;
    @ApiModelProperty("序号")
    private Integer sequence;
}
