package com.project.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.blog.common.Result;
import com.project.blog.dto.UserLoginDTO;
import com.project.blog.dto.UserRegisterDTO;
import com.project.blog.entity.SysUser;
import com.project.blog.enums.RoleType;
import com.project.blog.utils.JwtUtils;
import com.project.blog.utils.Utils;
import com.project.blog.vo.SysUserLoginVO;
import com.project.blog.vo.SysUserVo;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author 202012900729刘堃
 * @since 2023-06-27
 */
@Api(value = "用户模块", tags = "用户模块")
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @GetMapping("/default")
    public String test(){
        return "test";
    }


    @PostMapping("/login")
    public Result login(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, userLoginDTO.getUsername())
//                .eq(SysUser::getPassword, userLoginDTO.getPassword())
                .last("limit 1");
        //user.setPassword(SecureUtil.md5(user.getPassword()+ salt ));
        SysUser userInfo = userService.getOne(wrapper);
        if (userInfo != null) {
            String salt = userInfo.getSalt();

            if (!SecureUtil.md5(userLoginDTO.getPassword() + salt).equals(userInfo.getPassword())) {
                return new Result<>().error("请检查用户名密码是否正确");
            }

//            LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
//            //查询用户角色
//            queryWrapper.eq(SysRole::getRole,userInfo.getRoleType())
//                    //只查询一条数据
//                    .last("limit 1");
//
//            SysRole sysRole = sysRoleService.getOne(queryWrapper);
//            List<SysMenu> menuList = new ArrayList<>();
//            if(sysRole!=null){
//                LambdaQueryWrapper<SysRoleMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//                lambdaQueryWrapper.eq(SysRoleMenu::getRoleId,sysRole.getId());
//                List<SysRoleMenu> roleMenuList = sysRoleMenuService.list(lambdaQueryWrapper);
//                roleMenuList.forEach(item->{
//
//                    LambdaQueryWrapper<SysMenu> sysRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
//                    //查询用户角色
//                    sysRoleLambdaQueryWrapper.eq(SysMenu::getId,item.getMenuId())
//                            //只查询一条数据
//                            .eq(SysMenu::getStatus, StatusType.Yes)
//                            .last("limit 1");
//                    if(sysMenuService.getOne(sysRoleLambdaQueryWrapper)!=null){
//                        menuList.add(sysMenuService.getOne(sysRoleLambdaQueryWrapper));
//                    }
//                });
//            }
            //生成jwt
            String token = JwtUtils.generateToken(userInfo);

            SysUserLoginVO sysUserLoginVO = new SysUserLoginVO();
            BeanUtil.copyProperties(userInfo, sysUserLoginVO);
            sysUserLoginVO.setToken(token);
//            sysUserLoginVO.setMenuList(menuList);
            return new Result<>().success(sysUserLoginVO);
        } else {
            //new Result<>().error
            return new Result<>().error("请检查用户名密码是否正确");
//            throw new CustomException("请检查用户名密码是否正确");
        }


    }


    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDTO userRegisterDTO) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, userRegisterDTO.getUsername())
                .last("limit 1");
        //有值 提示用户名已重复
        SysUser userInfo = userService.getOne(wrapper);

        if (userInfo != null) {
            return new Result<>().error("用户名称重复");
        }
        SysUser user = new SysUser();
        BeanUtil.copyProperties(userRegisterDTO, user);

        String salt = Utils.salt();
        user.setSalt(salt);
        user.setPassword(SecureUtil.md5(user.getPassword() + salt));
        //TODO 用户注册需将权限设置为普通用户
        user.setRoleType(RoleType.ROLE_USER);

        userService.saveOrUpdate(user);//注册
        return new Result<>().success();
    }
}
