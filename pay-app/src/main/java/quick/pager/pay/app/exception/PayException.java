package quick.pager.pay.app.exception;

/**
 * 支付异常处理机制
 */
public class PayException extends RuntimeException {
    private static final long serialVersionUID = -7342180260196629642L;

    private int code;

    private String message;

    public PayException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
