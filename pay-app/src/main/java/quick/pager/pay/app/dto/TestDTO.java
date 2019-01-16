package quick.pager.pay.app.dto;

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

    private String merchantNo;
    private String mobile;
    private String merchantOrderCode;
    private String version;

    public TestDTO() {
    }

    public TestDTO(String merchantNo, String mobile, String merchantOrderCode, String version) {
        this.merchantNo = merchantNo;
        this.mobile = mobile;
        this.merchantOrderCode = merchantOrderCode;
        this.version = version;
    }
}
