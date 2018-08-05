package quick.pager.pay.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TestDto extends BaseDto{
    private static final long serialVersionUID = 7692288032140013504L;

    private long beginTime;
    private long endTime;
    private String merchantNo;
    private String mobile;
    private String merchantOrderCode;
    private String version;
}
