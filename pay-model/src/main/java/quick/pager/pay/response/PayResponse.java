package quick.pager.pay.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 支付响应对象
 * @author siguiyang
 */
@Data
public class PayResponse<T> implements Serializable {
    private static final long serialVersionUID = -6120197778709732868L;

    private int code;

    private String msg;

    private T data;

}
