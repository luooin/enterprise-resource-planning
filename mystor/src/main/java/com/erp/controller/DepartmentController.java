package com.erp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erp.entity.Department;
import com.erp.service.DepartmentService;
import com.erp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2024-03-15
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("depList",this.departmentService.list());
        model.addAttribute("empList",this.employeeService.list());
        return "departmentList";
    }
    @PostMapping("/save")
    @ResponseBody
    public String save(Department department){
        if(department.getDepartmentId() == null){
            boolean save = this.departmentService.save(department);
            if(save) return "success";
            return "fail";
        } else {
            boolean updateById = this.departmentService.updateById(department);
            if(updateById) return "success";
            return "fail";
        }
    }

    @GetMapping("/findById")
    @ResponseBody
    public Department findById(Integer id){
        return this.departmentService.getById(id);
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        List<Department> list = this.departmentService.list(queryWrapper);
        if(list.size() > 0) return "parentNode";
        boolean remove = this.departmentService.removeById(id);
        if(remove) return "success";
        return "fail";
    }

}

