package com.project.blog.serviceImpl;

import com.project.blog.entity.SysUser;
import com.project.blog.mapper.SysUserMapper;
import com.project.blog.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 202012900729刘堃
 * @since 2023-07-27
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
