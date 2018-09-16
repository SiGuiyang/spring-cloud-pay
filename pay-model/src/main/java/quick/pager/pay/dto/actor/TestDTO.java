package quick.pager.pay.dto.actor;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BaseDTO;

/**
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class TestDTO extends BaseDTO {
    private static final long serialVersionUID = 7692288032140013504L;

    private long beginTime;
    private long endTime;
    private String merchantNo;
    private String mobile;
    private String merchantOrderCode;
    private String version;

    public TestDTO() {
    }

    public TestDTO(long beginTime, long endTime, String merchantNo, String mobile, String merchantOrderCode, String version) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.merchantNo = merchantNo;
        this.mobile = mobile;
        this.merchantOrderCode = merchantOrderCode;
        this.version = version;
    }
}
