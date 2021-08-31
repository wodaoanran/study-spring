package com.aop.exception;

/**
 * @Author pwd
 * @Description:
 * @Date 2021/1/30
 * @Version 1.0
 */
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * 异常状态码
     */
    private Integer errorCode;

    public SystemException(Integer errorCode, String description) {
        super(description);
        this.errorCode = errorCode;
    }

    public SystemException(Integer errorCode, String description, Throwable cause) {
        super(description, cause);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}

