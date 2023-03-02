package com.atguigu.auth;

import com.atguigu.auth.mapper.SysRoleMapper;
import com.atguigu.model.system.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author wanghui
 * @Description
 * @Create 2023-03-01 21:22
 */
@SpringBootTest
public class TestMp01 {

    //注入
    @Autowired
    private SysRoleMapper mapper;

    @Test
    public void getAll() {
        //查询所有记录
        List<SysRole> roleList = mapper.selectList(null);

        System.out.println(roleList);

        for (SysRole sysRole : roleList) {
            System.out.println(sysRole);
        }
        roleList.forEach(System.out::println);
    }

    @Test
    public void add() {
        SysRole role = new SysRole();
        role.setRoleName("游客");
        role.setRoleCode("visit");
        role.setDescription("游客");

        int insert = mapper.insert(role);
        System.out.println(insert);
        System.out.println(role);
    }

    @Test
    public void testUpdateById(){
        SysRole sysRole = new SysRole();
        sysRole.setId(1l);
        sysRole.setRoleName("角色管理员1");

        int result = mapper.updateById(sysRole);
        System.out.println(result);

    }

    @Test
    public void testDeleteById(){

        int result = mapper.deleteById(9l);
        System.out.println(result);
    }
    @Test
    public void testDeleteBatchIds(){


        int result = mapper.deleteBatchIds(Arrays.asList(1, 2, 9));
        System.out.println(result);

    }
    @Test
    public void testSelect1(){
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_code","visit");

        List<SysRole> roleList = mapper.selectList(queryWrapper);
        roleList.forEach(System.out::println);
    }
    @Test
    public void testSelect2(){
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();

        LambdaQueryWrapper<SysRole> visit = queryWrapper.eq(SysRole::getRoleCode, "visit");

        List<SysRole> roleList = mapper.selectList(queryWrapper);
        roleList.forEach(System.out::println);
    }
}
