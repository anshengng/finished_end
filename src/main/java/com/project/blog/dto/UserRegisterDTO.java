package com.project.blog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterDTO {
    //ctrl+alt+enter 格式化代码
//    @Length(min = 6,max = 50,message = "用户名长度需要在[6和50]之间")
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
//    @Length(min = 8,max = 100,message = "密码需要在[8和100]之间")
    @ApiModelProperty("密码")
    private String password;


}