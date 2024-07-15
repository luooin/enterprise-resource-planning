package com.erp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erp.entity.Material;
import com.erp.service.MaterialService;
import com.erp.service.MaterialTypeService;
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
 * @since 2023-12-28
 */
@Controller
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;
    @Autowired
    private MaterialTypeService materialTypeService;
    @Autowired
    private ProdTypeService prodTypeService;

    @RequestMapping("/list")
    public String list(PageObject pageObject, Model model){
        model.addAttribute("page", this.materialService.list(pageObject));
        model.addAttribute("prodTypeList",this.prodTypeService.list());
        model.addAttribute("materialTypeList",this.materialTypeService.list());
        return "materialList";
    }
    @PostMapping("/save")
    @ResponseBody
    public String save(Material material){
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("material_code", material.getMaterialCode());
        Material one = this.materialService.getOne(queryWrapper);
        if(material.getMaterialId() == null){
            if(one != null) return "fail";
            boolean save = this.materialService.save(material);
            if(save) return "success";
        } else {
            if(one != null && !one.getMaterialId().equals(material.getMaterialId())) return "fail";
            boolean updateById = this.materialService.updateById(material);
            if(updateById) return "success";
        }
        return null;
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        boolean remove = this.materialService.removeById(id);
        if(remove) return "success";
        return "fail";
    }

}

