package com.atguigu.auth.utils;

import com.atguigu.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author wanghui
 * @Description  根据菜单表，构建菜单数据
 * @Create 2023-03-07 16:14
 *
 */

public class MenuHelper {
    /**
     * 使用递归方法建菜单
     * @param sysMenuList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList){
        List<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if (sysMenu.getParentId().longValue()==0)
                trees.add(findChildren(sysMenu,sysMenuList));
        }
        return trees;
    }

    /**
     * 递归查找字节点
     * @param sysMenu
     * @param treeNodes
     * @return
     */
    public static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        sysMenu.setChildren(new ArrayList<SysMenu>());
        //遍历所有菜单数据，判断id和parentId对应关系
        for (SysMenu it : treeNodes) {
            if (sysMenu.getId().longValue()==it.getParentId().longValue()) {
                if(sysMenu.getChildren()==null)
                    sysMenu.setChildren(new ArrayList<>());
                sysMenu.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return sysMenu;
    }
}
