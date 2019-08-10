package quick.pager.pay.app.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import quick.pager.pay.app.exception.PayException;
import quick.pager.pay.response.Response;

/**
 * 统一异常处理
 *
 * @author siguiyang
 */
@RestControllerAdvice
public class PayControllerAdvice {


    /**
     * 验证参数发生异常处理
     */
    @ExceptionHandler(value = PayException.class)
    public Response<String> doException(Exception e) {
        PayException payException = (PayException) e;
        return new Response<>(payException.getCode(), payException.getMessage());
    }
}
