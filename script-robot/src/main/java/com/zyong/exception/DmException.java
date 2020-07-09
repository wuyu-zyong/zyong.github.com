package com.zyong.exception;

import lombok.extern.log4j.Log4j;

import java.io.Serializable;
public class DmException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 8165506983713975882L;
    private DmExceptionEnum exceptionEnum;
    public DmException(DmExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
        System.out.println(exceptionEnum.getCode() + "," + exceptionEnum.getMsg());
    }

    public DmExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    //用来输出异常信息和状态码
    public void printException(DmException e) {
        DmExceptionEnum exceptionEnum = e.getExceptionEnum();
        System.out.println(exceptionEnum.getCode() + "," + exceptionEnum.getMsg());
    }
}
