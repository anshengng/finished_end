package com.project.blog.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

@Data
public class PageVo<T> {
    List<T> list;
    long total;

    public PageVo(Page<T> page){
        this.setList(page.getRecords());
        this.setTotal(page.getTotal());
    }
}
