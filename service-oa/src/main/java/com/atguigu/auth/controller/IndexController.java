package com.atguigu.auth.controller;

import com.atguigu.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("login")
    public Result login() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", "admin-token");
        return Result.ok(map);
    }

    //info
    @GetMapping("info")
    public Result info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "['admin']");
        map.put("introduction", "I am a super administrator");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name", "super admin");
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
