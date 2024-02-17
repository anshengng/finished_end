package com.project.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.blog.common.Result;
import com.project.blog.dto.CategoryDTO;
import com.project.blog.entity.Category;
import com.project.blog.vo.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 * 栏目表 前端控制器
 * </p>
 *
 * @author 202012900729刘堃
 * @since 2023-07-22
 */

/**
 * <p>
 * 栏目表 前端控制器
 * </p>
 *
 * @author 程序员逐梦
 * @since 2023-03-03
 */
@Api(value = "栏目模块", tags = "栏目模块")
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @GetMapping("/list")
    public Result<Category> list() {
        return new Result<>().success(categoryService.list());
    }

    //分页查询测试
    @PostMapping("/page")
    public Result<PageVo<Category>> findPage(@RequestBody CategoryDTO categoryDTO) {
        //将数据降序排列
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper();
//        wrapper.orderByDesc(Category::getId);

        //模糊查询
        if(categoryDTO.getTitle() !=null && !"".equals(categoryDTO.getTitle())){
            wrapper.like(Category::getTitle,categoryDTO.getTitle());
        }

        Page<Category> page = categoryService.page(
                new Page<>(
                        categoryDTO.getPageNum(),
                        categoryDTO.getPageSize()
                ),
                wrapper
        );
        return new Result<>().success(new PageVo<>(page));
    }

    //修改
    @ApiOperation(value = "数据保存或更新",notes = "数据保存或更新")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody Category category) {
        categoryService.saveOrUpdate(category);
        return new Result<>().success();
    }

    //删除
    @ApiOperation(value = "根据id批量删除",notes = "根据id批量删除")
    @PostMapping("/del")
    public Result delUser(@RequestBody List<Integer> ids) {
        categoryService.removeByIds(ids);
        return new Result<>().success();
    }

}