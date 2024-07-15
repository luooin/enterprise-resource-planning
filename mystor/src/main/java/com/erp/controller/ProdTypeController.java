package com.erp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erp.entity.ProdType;
import com.erp.service.ProdTypeService;
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
 * @since 2024-03-15
 */
@Controller
@RequestMapping("/prodType")
public class ProdTypeController {
    @Autowired
    private ProdTypeService prodTypeService;
    @RequestMapping("/list")
    public String list(PageObject pageObject, Model model){
        model.addAttribute("page",this.prodTypeService.list(pageObject));
        return "prodTypeList";
    }
    @PostMapping("/save")
    @ResponseBody
    public String save(ProdType prodType){
        QueryWrapper<ProdType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("prod_type_code",prodType.getProdTypeCode() );
        ProdType one = this.prodTypeService.getOne(queryWrapper);
        if(prodType.getProdTypeId() == null){
            if(one != null) return "fail";
            boolean save = this.prodTypeService.save(prodType);
            if(save) return "success";
        } else {
            if(one != null && !one.getProdTypeId().equals(prodType.getProdTypeId())) return "fail";
            boolean updateById = this.prodTypeService.updateById(prodType);
            if(updateById) return "success";
        }
        return null;
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        boolean removeById = this.prodTypeService.removeById(id);
        if(removeById) return "success";
        return "fail";
    }
}

