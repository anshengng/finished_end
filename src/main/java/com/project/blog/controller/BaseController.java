package com.project.blog.controller;

import com.project.blog.serviceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @Autowired
    SysUserServiceImpl userService;

    @Autowired
    CategoryServiceImpl categoryService;
}
