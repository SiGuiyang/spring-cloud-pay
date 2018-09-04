package quick.pager.pay.response;


import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 数据响应对象
 *
 * @param <T> 泛型对象
 * @author siguiyang
 */
@Data
@Builder
@RequiredArgsConstructor
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1490755142057414040L;

    /**
     * 响应对象响应吗
     */
    @NonNull
    private int code = 200;
    /**
     * 响应提示信息
     */
    @NonNull
    private String msg = "操作成功";

    /**
     * 响应数据集总数
     */
    private long total;

    /**
     * 响应返回主体
     */
    private T data;

}
