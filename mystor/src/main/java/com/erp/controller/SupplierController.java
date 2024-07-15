package com.erp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erp.entity.Supplier;
import com.erp.service.SupplierService;
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
 * @since 2023-12-28
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @RequestMapping("/list")
    public String list(PageObject pageObject, Model model){
        model.addAttribute("page", this.supplierService.list(pageObject));
        return "supplierList";
    }

    @PostMapping("/save")
    @ResponseBody
    public String save(Supplier supplier){
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("supplier_code", supplier.getSupplierCode());
        Supplier one = this.supplierService.getOne(queryWrapper);
        if(supplier.getSupplierId() == null){
            if(one != null) return "fail";
            boolean save = this.supplierService.save(supplier);
            if(save) return "success";
        } else {
            if(one != null && !one.getSupplierId().equals(supplier.getSupplierId())) return "fail";
            boolean updateById = this.supplierService.updateById(supplier);
            if(updateById) return "success";
        }
        return null;
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        boolean remove = this.supplierService.removeById(id);
        if(remove) return "success";
        return "fail";
    }
}

