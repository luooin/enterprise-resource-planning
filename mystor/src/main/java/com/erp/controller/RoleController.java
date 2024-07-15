package com.erp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erp.entity.Role;
import com.erp.entity.RoleAuthority;
import com.erp.entity.RoleEmployee;
import com.erp.service.RoleAuthorityService;
import com.erp.service.RoleEmployeeService;
import com.erp.service.RoleService;
import com.erp.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2024-03-16
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleEmployeeService roleEmployeeService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;

    @RequestMapping("/list")
    public String list(PageObject pageObject, Model model){
        model.addAttribute("page", this.roleService.roleList(pageObject));
        return "authorityList";
    }


    @PostMapping("/save")
    @ResponseBody
    public String save(Integer roleId,String roleName){
        if(roleId == null) {
            Role role = new Role();
            role.setRoleName(roleName);
            boolean save = this.roleService.save(role);
            if(save) return "success";
            return "fail";
        } else {
            Role role = new Role();
            role.setRoleId(roleId);
            role.setRoleName(roleName);
            boolean updateById = this.roleService.updateById(role);
            if(updateById) return "success";
            return "fail";
        }
    }
    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        boolean removeById = this.roleService.removeById(id);
        QueryWrapper<RoleAuthority> roleAuthorityQueryWrapper = new QueryWrapper<>();
        roleAuthorityQueryWrapper.eq("role_id", id);
        boolean remove = this.roleAuthorityService.remove(roleAuthorityQueryWrapper);
        QueryWrapper<RoleEmployee> roleEmployeeQueryWrapper = new QueryWrapper<>();
        roleEmployeeQueryWrapper.eq("role_id", id);
        boolean remove1 = this.roleEmployeeService.remove(roleEmployeeQueryWrapper);
        if(removeById && remove && remove1) return "success";
        return "fail";
    }
}

