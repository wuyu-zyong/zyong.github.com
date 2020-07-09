package com.zyong.exception;

public enum DmExceptionEnum {
    SCRIPT_ROBOT_RETURN_ERROR("1001","脚本返回错误异常"),
    SCRIPT_ROBOT_HWND_NOT_FOUND("1002","未找到相关句柄"),
    SCRIPT_ROBOT_PIC_NOT_FOUND("1003","未找到相关图片");

    private String code;
    private String msg;

    public DmExceptionEnum setCode(String code) {
        this.code = code;
        return this;
    }

    public DmExceptionEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    DmExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

}
