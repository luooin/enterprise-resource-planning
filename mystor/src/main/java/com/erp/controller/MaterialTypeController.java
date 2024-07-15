package com.erp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erp.entity.MaterialType;
import com.erp.service.MaterialTypeService;
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
@RequestMapping("/materialType")
public class MaterialTypeController {
    @Autowired
    private MaterialTypeService materialTypeService;
    @RequestMapping("/list")
    public String list(PageObject pageObject,Model model){
        model.addAttribute("page",this.materialTypeService.list(pageObject));
        return "materialTypeList";
    }
    @PostMapping("/save")
    @ResponseBody
    public String save(MaterialType materialType){
        QueryWrapper<MaterialType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("material_type_code", materialType.getMaterialTypeCode());
        MaterialType one = this.materialTypeService.getOne(queryWrapper);
        if(materialType.getMaterialTypeId() == null){
            if(one != null) return "fail";
            boolean save = this.materialTypeService.save(materialType);
            if(save) return "success";
        } else {
            if(one != null && !one.getMaterialTypeId().equals(materialType.getMaterialTypeId())) return "fail";
            boolean updateById = this.materialTypeService.updateById(materialType);
            if(updateById) return "success";
        }
        return null;
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        boolean removeById = this.materialTypeService.removeById(id);
        if(removeById) return "success";
        return "fail";
    }
}

