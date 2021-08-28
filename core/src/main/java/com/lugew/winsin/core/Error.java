package com.lugew.winsin.core;

/**
 * 错误
 *
 * @author 夏露桂
 * @since 2021/1/21 10:06
 */
public enum Error {
    INTERNAL_SERVER_ERROR("internal.server.error", "服务器内部错误"),
    USER_NOT_FOUND("user.not.found", "用户不存在"),
    UPLOAD_FAILED("upload.failed", "上传失败"),
    DELETE_FAILED("delete.failed", "删除失败"),
    NOT_FOUND("not.found", "未找到"),

    VALIDATE_FAILED("validate.failed", "校验失败"),
    ;

    private String code;
    private final String value;

    Error(String code, String value) {
        this.code = code;
        this.value = value;
    }

    Error(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }
}
