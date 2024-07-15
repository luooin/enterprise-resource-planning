package com.erp.controller;


import com.erp.entity.Employee;
import com.erp.service.DepartmentService;
import com.erp.service.EmployeeService;
import com.erp.util.AesUtils;
import com.erp.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @since 2024-03-15
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @RequestMapping("/list")
    public String list(PageObject pageObject, Employee employee,Model model){
        model.addAttribute("page",this.employeeService.list(pageObject,employee));
        model.addAttribute("departmentList",this.departmentService.list());
        model.addAttribute("employee",employee);
        return "employeeList";
    }
    @PostMapping("/setStatus")
    @ResponseBody
    public String setStatus(Integer status,Integer id){
        boolean setStatus = this.employeeService.setStatus(status, id);
        if(setStatus) return "success";
        return "fail";
    }
    @PostMapping("/resetPassword")
    @ResponseBody
    public String resetPassword(Integer id){
        boolean resetPassword = this.employeeService.resetPassword(id);
        if(resetPassword) return "success";
        return "fail";
    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("departmentList",this.departmentService.list());
        return "employeeAdd";
    }
    @PostMapping("/save")
    public String save(Employee employee){
        employee.setStatus(1);
        employee.setPassword(AesUtils.encryptStr("123456",AesUtils.SECRETKEY));
        boolean save = this.employeeService.save(employee);
        if(save) return "redirect:/employee/list";
        return "employeeAdd";
    }
    @GetMapping("/findById")
    public String findById(Integer id,Model model){
        model.addAttribute("employee", this.employeeService.getById(id));
        model.addAttribute("departmentList", this.departmentService.list());
        return "employeeEdit";
    }

    @PostMapping("/update")
    public String update(Employee employee){
        employee.setStatus(1);
        employee.setPassword(AesUtils.encryptStr("123456",AesUtils.SECRETKEY));
        boolean save = this.employeeService.updateById(employee);
        if(save) return "redirect:/employee/list";
        return "employeeAdd";
    }
    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        boolean removeById = this.employeeService.removeById(id);
        if(removeById) return "success";
        return "fail";
    }

}

