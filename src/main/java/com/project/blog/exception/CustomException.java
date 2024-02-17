package com.project.blog.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    /**
     * 自定义异常/异常捕获
     */
    private Integer code;

    public CustomException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public CustomException(String msg) {
        super(msg);
    }
}
