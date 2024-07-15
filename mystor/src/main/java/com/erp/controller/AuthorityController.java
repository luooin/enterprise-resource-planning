package com.erp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erp.entity.Employee;
import com.erp.entity.RoleAuthority;
import com.erp.entity.RoleEmployee;
import com.erp.service.*;
import com.erp.vo.AuthorityVO;
import com.erp.vo.DepartmentVO;
import com.erp.vo.EmployeeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2024-03-16
 */
@Controller
@RequestMapping("/authority")
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleEmployeeService roleEmployeeService;

    @GetMapping("/list")
    public String list(Integer id, Model model){
        //权限
        List<AuthorityVO> authorityVOList = this.authorityService.authorityVOList();
        //当前角色拥有的权限
        QueryWrapper<RoleAuthority> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", id);
        List<RoleAuthority> list = this.roleAuthorityService.list(queryWrapper);
        for (AuthorityVO authorityVO : authorityVOList) {
            for (RoleAuthority roleAuthority : list) {
                if(authorityVO.getAuthorityId().equals(roleAuthority.getAuthorityId())) authorityVO.setHave(true);
            }
        }
        model.addAttribute("authorityList", authorityVOList);
        return "authorityEdit";
    }

    @PostMapping("/roleAuthority")
    @ResponseBody
    public String roleAuthority(Integer roleId,String authorityIdArr){
        QueryWrapper<RoleAuthority> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        this.roleAuthorityService.remove(queryWrapper);
        if(!authorityIdArr.equals("")){
            String[] split = authorityIdArr.split(",");
            for (String id : split) {
                RoleAuthority roleAuthority = new RoleAuthority();
                roleAuthority.setAuthorityId(Integer.parseInt(id));
                roleAuthority.setRoleId(roleId);
                boolean save = this.roleAuthorityService.save(roleAuthority);
                if(!save) return "fail";
            }
            return "success";
        }
        return "success";
    }

    @PostMapping("/employeeAuthority")
    @ResponseBody
    public String employeeAuthority(Integer roleId,String employeeIdArr){
        QueryWrapper<RoleEmployee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        this.roleEmployeeService.remove(queryWrapper);
        if(!employeeIdArr.equals("")){
            String[] split = employeeIdArr.split(",");
            for (String id : split) {
                RoleEmployee roleEmployee = new RoleEmployee();
                roleEmployee.setRoleId(roleId);
                roleEmployee.setEmployeeId(Integer.parseInt(id));
                boolean save = this.roleEmployeeService.save(roleEmployee);
                if(!save) return "fail";
            }
            return "success";
        }
        return "success";
    }

    @GetMapping("/employeeAuthority")
    public String employeeAuthority(Integer roleId,Model model){
        List<DepartmentVO> departmentVOS = this.departmentService.departmentList();
        for (DepartmentVO departmentVO : departmentVOS) {
            Integer departmentId = departmentVO.getDepartmentId();
            QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("department_id", departmentId);
            List<Employee> employeeList = this.employeeService.list(queryWrapper);
            List<EmployeeVO> employeeVOList = new ArrayList<>();
            for (Employee employee : employeeList) {
                EmployeeVO vo = new EmployeeVO();
                BeanUtils.copyProperties(employee, vo);
                //判断当前用户是否拥有当前角色
                QueryWrapper<RoleEmployee> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("role_id", roleId);
                List<RoleEmployee> list = this.roleEmployeeService.list(queryWrapper1);
                for (RoleEmployee roleEmployee : list) {
                    if (roleEmployee.getEmployeeId().equals(vo.getEmployeeId())) {
                        vo.setHave(true);
                    }
                }
                employeeVOList.add(vo);
            }
            departmentVO.setEmployees(employeeVOList);
        }
        model.addAttribute("departmentList", departmentVOS);
        return "authorityEmployee";
    }
}

