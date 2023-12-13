package cn.dhbin.isme.common.response;

import cn.dhbin.isme.common.exception.BizException;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 统一响应body
 *
 * @author dhb
 */
@Data
@Accessors(chain = true)
public class R<T> {

    /**
     * 数据
     */
    private T data;

    /**
     * 响应码
     */
    private int code;

    /**
     * 描述
     */
    private String message;

    /**
     * 无数据的成功响应
     *
     * @param <T> 类型
     * @return 响应体
     */
    public static <T> R<T> ok() {
        return ok(null);
    }

    /**
     * 成功的响应码，附带数据
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return 响应体
     */
    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setData(data);
        r.setCode(BizResponseCode.OK.getCode());
        r.setMessage(BizResponseCode.OK.getMsg());
        return r;
    }

    /**
     * 构建业务异常的响应
     *
     * @param exception 业务异常
     * @param <T>       类型
     * @return 响应体
     */
    public static <T> R<T> build(BizException exception) {
        R<T> r = new R<>();
        r.setCode(exception.getCode().getCode());
        r.setData(null);
        r.setMessage(exception.getMessage());
        return r;
    }

}
