package com.project.blog.dto;

import lombok.Data;

@Data
public class PageInfo {
    /**
     * @param pageNUm 页码
     * @param pageSize 每页显示的数量
     */

    private Integer pageNum;

    private Integer pageSize;
}
