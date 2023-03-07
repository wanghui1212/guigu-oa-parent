package com.atguigu.auth.controller;


import com.atguigu.auth.service.SysMenuService;
import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysMenu;
import com.atguigu.vo.system.AssginMenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-03-07
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation("获取菜单")
    @GetMapping("findNodes")
    public Result findNodes(){
        List<SysMenu> nodes = sysMenuService.findNodes();
        return Result.ok(nodes);
    }

    @ApiOperation("新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody SysMenu permission){
        boolean save = sysMenuService.save(permission);
        return Result.ok();
    }
    @ApiOperation("修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody SysMenu permission){
        sysMenuService.updateById(permission);
        return Result.ok();
    }
    @ApiOperation("删除菜单")
    @DeleteMapping("remove/{id}")
    public Result removeById(@PathVariable Long id){
        sysMenuService.removeById(id);
        return Result.ok();
    }
    @ApiOperation("根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Result toassign(@PathVariable Long roleId){
        List<SysMenu> list = sysMenuService.findSysMenuByRoleId(roleId);

        return Result.ok(list);
    }
    @ApiOperation("给角色分配权限")
    @PostMapping("doAssign")
    public Result doAssign(@RequestBody AssginMenuVo assginMenuVo){
        sysMenuService.doAssign(assginMenuVo);
        return Result.ok();
    }
}

