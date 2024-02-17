package com.project.blog.Config;

import com.project.blog.common.Result;
import com.project.blog.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public Result CustomerExceptionHandler(CustomException e) {
        log.error("错误原因为：" + e.getMessage());
        return new Result<>().error(e.getCode(), e.getMessage());
    }
}
