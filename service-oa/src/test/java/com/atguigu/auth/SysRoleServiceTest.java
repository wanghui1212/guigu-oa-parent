package com.atguigu.auth;

import com.atguigu.auth.service.SysRoleService;
import com.atguigu.model.system.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @version 1.0
 * @Author wanghui
 * @Description
 * @Create 2023-03-02 15:55
 */
@SpringBootTest
public class SysRoleServiceTest {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void testSelectList(){
        System.out.println(" select all methods test");
        List<SysRole> list = sysRoleService.list();
        list.forEach(System.out::println);
    }
    @Test
    public void testInsert(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员");
        sysRole.setRoleCode("role");
        sysRole.setDescription("角色管理员");

        boolean save = sysRoleService.save(sysRole);
        System.out.println(save);
        System.out.println(sysRole.getId());
    }
    @Test
    public void testUpdateById(){
        SysRole sysRole = new SysRole();
        sysRole.setId(11l);
        sysRole.setRoleName("宠物管理员");
        sysRole.setRoleCode("animal");
        sysRole.setDescription("宠物管理员");
        boolean b = sysRoleService.updateById(sysRole);
        System.out.println(b);
    }
    @Test
    public void testRemoveById(){

        boolean b = sysRoleService.removeById(11l);
        System.out.println(b);
    }
    @Test
    public void testSelect1(){

        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("description","游客");
        List<SysRole> list = sysRoleService.list(queryWrapper);
        list.forEach(System.out::println);
    }
    @Test
    public void testSelect2(){
       /* LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRole::getDescription,"游客");

        List<SysRole> list = sysRoleService.list(queryWrapper);
        list.forEach(System.out::println);*/
        int a = 10/0;
    }

}
