package quick.pager.pay.response;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据响应对象
 *
 * @param <T> 泛型对象
 * @author siguiyang
 */
@Data
@Builder
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1490755142057414040L;

    /**
     * 响应对象响应吗
     */
    private int code = 200;
    /**
     * 响应提示信息
     */
    private String msg = "操作成功";

    /**
     * 响应数据集总数
     */
    private long total;

    /**
     * 响应返回主体
     */
    private T data;

    public Response() {
    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(int code, String msg, long total, T data) {
        this.code = code;
        this.msg = msg;
        this.total = total;
        this.data = data;
    }
}
