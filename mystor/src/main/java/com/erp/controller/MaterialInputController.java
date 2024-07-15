package com.erp.controller;

import com.alibaba.excel.EasyExcel;
import com.erp.form.MaterialInputSearchForm;
import com.erp.service.MaterialInputService;
import com.erp.service.SupplierService;
import com.erp.util.CustomCellWriteHandler;
import com.erp.util.ImportResult;
import com.erp.util.MaterialInputExportModel;
import com.erp.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-12-25
 */
@Controller
@RequestMapping("/materialInput")
public class MaterialInputController {
    @Autowired
    private MaterialInputService materialInputService;
    @Autowired
    private SupplierService supplierService;
    //数据导入
    @PostMapping("/import")
    public String materialImport(@RequestParam("file") MultipartFile file,Model model) throws Exception{
        ImportResult importResult = this.materialInputService.excelImport(file.getInputStream());
        if(importResult.getCode()==0) return "redirect:/materialInput/list";
        model.addAttribute("errorMsg",importResult.getMsg());
        return "materialInput";
    }
    //数据展示
    @RequestMapping("/list")
    public String list(Model model, PageObject pageObject, MaterialInputSearchForm materialInputSearchForm){
        PageObject resultPage = this.materialInputService.materialInputList(pageObject,materialInputSearchForm);
        model.addAttribute("page", resultPage);
        model.addAttribute("supplierList",this.supplierService.list());
        model.addAttribute("form",materialInputSearchForm);
        return "materialInputList";
    }
    //数据导出
    @GetMapping("/export")
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode("采购数据", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //获取数据
            List<MaterialInputExportModel> list = this.materialInputService.getExportList();
            EasyExcel.write(response.getOutputStream(), MaterialInputExportModel.class)
                    .registerWriteHandler(new CustomCellWriteHandler())
                    .sheet("采购数据")
                    .doWrite(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //入库
    @PostMapping("/verify")
    @ResponseBody
    public String verify(Integer status,String idArray){
        boolean verify = this.materialInputService.verify(status, idArray);
        if(verify) return "success";
        return "fail";
    }
    //删除
    @PostMapping("delete")
    @ResponseBody
    public String delete(String idArray){
        boolean delete = this.materialInputService.delete(idArray);
        if(delete) return "success";
        return "fail";
    }
}

