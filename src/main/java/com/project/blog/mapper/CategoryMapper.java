package com.project.blog.mapper;

import com.project.blog.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 栏目表 Mapper 接口
 * </p>
 *
 * @author 202012900729刘堃
 * @since 2023-07-22
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
