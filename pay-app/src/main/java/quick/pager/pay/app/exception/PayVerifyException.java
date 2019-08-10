package quick.pager.pay.app.exception;

public class PayVerifyException extends PayException {
    private static final long serialVersionUID = 8653705683939962476L;

    public PayVerifyException(int code, String message) {
        super(code, message);
    }
}
