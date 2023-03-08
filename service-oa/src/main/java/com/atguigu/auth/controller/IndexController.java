package com.atguigu.auth.controller;

import com.atguigu.auth.service.SysUserService;
import com.atguigu.common.config.exception.GuiguException;
import com.atguigu.common.jwt.JwtHelper;
import com.atguigu.common.result.Result;
import com.atguigu.common.utils.MD5;
import com.atguigu.model.system.SysUser;
import com.atguigu.vo.system.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author wanghui
 * @Description
 * @Create 2023-03-05 18:54
 */
@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    //login
    /*@PostMapping("login")
    public Result login() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", "admin-token");
        return Result.ok(map);
    }*/
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        SysUser sysUser = sysUserService.getByUsername(loginVo.getUsername());
        if(null == sysUser) {
            throw new GuiguException(201,"用户不存在");
        }
        if(!MD5.encrypt(loginVo.getPassword()).equals(sysUser.getPassword())) {
            throw new GuiguException(201,"密码错误");
        }
        if(sysUser.getStatus().intValue() == 0) {
            throw new GuiguException(201,"用户被禁用");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", JwtHelper.createToken(sysUser.getId(), sysUser.getUsername()));
        return Result.ok(map);
    }

    //info
   /* @GetMapping("info")
    public Result info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("name","admin");
        map.put("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        //返回菜单
        map.put("routers", new ArrayList<>());
        //返回按钮
        map.put("buttons", new ArrayList<>());
        return Result.ok(map);
    }*/
    @ApiOperation(value = "获取用户信息")
    @GetMapping("info")
    public Result info(HttpServletRequest request) {
        /*String username = JwtHelper.getUsername(request.getHeader("token"));
        System.out.println("username:"+username);
        Map<String, Object> map = sysUserService.getUserInfo(username);*/
        String token = request.getHeader("token");
        System.out.println("token = " + token);
        String username = JwtHelper.getUsername(token);
        System.out.println("username = " + username);
        Map<String, Object> map = sysUserService.getUserInfo(username);

        return Result.ok(map);
    }
    //logout
    //{"code":20000,"data":"success"}
    //{"code":200,"message":"成功","data":{"data":"success"}}
    @PostMapping("logout")
    public Result logout() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", "success");
        return Result.ok(map);
    }
}
