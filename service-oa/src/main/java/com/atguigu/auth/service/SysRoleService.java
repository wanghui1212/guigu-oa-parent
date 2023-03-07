package com.atguigu.auth.service;

import com.atguigu.model.system.SysRole;
import com.atguigu.vo.system.AssginRoleVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @version 1.0
 * @Author wanghui
 * @Description
 * @Create 2023-03-02 15:50
 */

public interface SysRoleService extends IService<SysRole> {
    //根据用户获取角色数据
    Map<String, Object> findRoleByAdminId(Long userId);

    //根据用户分配角色
    void doAssign(AssginRoleVo assginRoleVo);
}
