package quick.pager.pay.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import quick.pager.pay.Constants;

import java.io.Serializable;

/**
 * 数据传输对象基类
 *
 * @author siguiyang
 */
@Data
public class BaseDto extends DTO {
    private static final long serialVersionUID = -2797845568007827367L;

    @ApiModelProperty(value = "Id")
    private Long id;
    @ApiModelProperty(value = "服务状态")
    private Integer serverStatus;
    @ApiModelProperty(value = "起始时间")
    private long beginTime;
    @ApiModelProperty(value = "结束时间")
    private long endTime;
    @ApiModelProperty(value = "一页的大小")
    private Integer pageSize;
    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    private Constants.Operation operation;
}
