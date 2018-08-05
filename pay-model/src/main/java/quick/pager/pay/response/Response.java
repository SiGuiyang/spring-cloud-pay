package quick.pager.pay.response;


import java.io.Serializable;

/**
 * 数据响应对象
 *
 * @param <T> 泛型对象
 * @author siguiyang
 */
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
