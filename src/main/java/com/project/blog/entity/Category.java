package com.project.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 栏目表
 * </p>
 *
 * @author 202012900729刘堃
 * @since 2023-07-22
 */
@Getter
@Setter
@TableName("category")
@ApiModel(value = "Category对象", description = "栏目表")
public class Category extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标题")
    @TableField("title")
    @NotBlank(message = "title不能为空")
    private String title;


}
