package com.atguigu.auth.controller;

import com.atguigu.auth.service.SysRoleService;
import com.atguigu.common.config.exception.GuiguException;
import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.vo.system.AssginRoleVo;
import com.atguigu.vo.system.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author wanghui
 * @Description
 * @Create 2023-03-02 16:51
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService service;

    @ApiOperation(value = "获取角色列表")
    @GetMapping("findAll")
    public Result<List<SysRole>> findAll() {
        List<SysRole> list = service.list();

        //模拟异常效果
        try {
            int i = 10 / 0;
        } catch (Exception e) {

            throw new GuiguException(20001, "执行了自定义异常处理...");
        }
        return Result.ok(list);
    }

    //条件分页查询
//page 当前页  limit 每页显示记录数
//SysRoleQueryVo 条件对象
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result pageQueryRole(@PathVariable Long page,
                                @PathVariable Long limit,
                                SysRoleQueryVo sysRoleQueryVo) {
        //调用service的方法实现
        //1 创建Page对象，传递分页相关参数
        //page 当前页  limit 每页显示记录数
        Page<SysRole> pageParam = new Page<>(page, limit);

        //2 封装条件，判断条件是否为空，不为空进行封装
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if (!StringUtils.isEmpty(roleName)) {
            //封装 like模糊查询
            wrapper.like(SysRole::getRoleName, roleName);
        }

        //3 调用方法实现
        Page<SysRole> pageModel = service.page(pageParam, wrapper);
        return Result.ok(pageModel);
    }

    @ApiOperation("根据id查询")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SysRole role = service.getById(id);
        return Result.ok(role);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("save")
    public Result save(@RequestBody @Validated SysRole role) {
        boolean is_success = service.save(role);
        if (is_success)
            return Result.ok();
        else
            return Result.fail();
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("update")
    public Result updateById(@RequestBody SysRole role) {
        boolean is_success = service.updateById(role);

        if (is_success)
            return Result.ok();
        else
            return Result.fail();
    }

    @ApiOperation(value = "根据id删除角色")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean is_success = service.removeById(id);

        if (is_success)
            return Result.ok();
        else
            return Result.fail();
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        boolean is_success = service.removeByIds(idList);

        if (is_success)
            return Result.ok();
        else
            return Result.fail();
    }
    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId){
        Map<String,Object> roleMap = service.findRoleByAdminId(userId);
        return Result.ok(roleMap);
    }
    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("doAssign")
    public Result toAssign(@RequestBody AssginRoleVo assginRoleVo){
        service.doAssign(assginRoleVo);
        return Result.ok();
    }
}
