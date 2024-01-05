package cn.dhbin.isme.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 业务响应码
 *
 * @author dhb
 */
@Getter
@RequiredArgsConstructor
public enum BizResponseCode {

    /**
     * 正常响应码
     */
    OK(0, "OK"),
    ERR_400(400, "参数错误"),

    ERR_10001(10001, "用户已存在"),
    ERR_10002(10002, "用户名或密码错误"),
    ERR_10003(10003, "验证码错误"),
    ERR_10004(10004, "密码验证失败"),


    ERR_11003(11003, "无权限，请联系管理员申请权限"),
    ERR_11004(11004, "越权操作"),
    ERR_11005(11005, "您目前暂无此角色或已被禁用，请联系管理员"),
    ERR_11006(11006, "非法操作"),


    ERR_30001(30001, "预览环境不支持此操作");

    private final int code;

    private final String msg;

}
