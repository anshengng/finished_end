package com.project.blog.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {


    private Integer code;

    private String msg;

    private T data;

    public Result success(){
        return new Result(200,"请求成功",null);
    }

    public  Result success(T data){
        return new Result(200,"请求成功",data);
    }

    public Result error(String msg){
        return new Result(500,msg,null);
    }

    public Result error(Integer wwww,String msg){
        return new Result(code,msg,null);
    }

    //无参防止攻击
    public
    Result error(){
        return new Result(500,"系统错误，联系管理员",null);
    }
}
