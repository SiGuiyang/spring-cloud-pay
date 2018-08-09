package quick.pager.pay.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class VO implements Serializable {
    private static final long serialVersionUID = -794393367823849536L;

    private String beginTime;

    private String endTime;
}