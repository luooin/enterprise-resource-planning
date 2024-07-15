package com.erp.controller;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erp.entity.Storage;
import com.erp.form.ReportForm;
import com.erp.service.OrderDetailService;
import com.erp.service.StorageService;
import com.erp.util.CustomCellWriteHandler;
import com.erp.util.PageObject;
import com.erp.vo.StorageReportExportVO;
import com.erp.vo.StorageReportVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-12-28
 */
@Controller
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("report/list")
    public String reportList(PageObject pageObject, ReportForm form, Model model){
        model.addAttribute("storageList",this.storageService.list());
        model.addAttribute("reportForm",form);
        model.addAttribute("page",this.orderDetailService.storageReportList(pageObject,form));
        return "storageReportList";
    }
    @GetMapping("/report/export")
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode("仓库统计报表", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //获取数据
            List<StorageReportVO> storageReportVOS = this.orderDetailService.storageReportVOExportList();
            List<StorageReportExportVO> storageReportExportVOS = new ArrayList<>();
            for (StorageReportVO storageReportVO : storageReportVOS) {
                StorageReportExportVO vo = new StorageReportExportVO();
                BeanUtils.copyProperties(storageReportVO, vo);
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                vo.setOrderDate(storageReportVO.getOrderDate().format(dateTimeFormatter));
                storageReportExportVOS.add(vo);
            }
            EasyExcel.write(response.getOutputStream(), StorageReportExportVO.class)
                    .registerWriteHandler(new CustomCellWriteHandler())
                    .sheet("仓库统计")
                    .doWrite(storageReportExportVOS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/list")
    public String list(PageObject pageObject, Model model){
        model.addAttribute("page", this.storageService.list(pageObject));
        return "storageList";
    }

    @PostMapping("/save")
    @ResponseBody
    public String save(Storage storage){
        QueryWrapper<Storage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("storage_code", storage.getStorageCode());
        Storage one = this.storageService.getOne(queryWrapper);
        if(storage.getStorageId() == null){
            if(one != null) return "fail";
            boolean save = this.storageService.save(storage);
            if(save) return "success";
        } else {
            if(one != null && !one.getStorageId().equals(storage.getStorageId())) return "fail";
            boolean updateById = this.storageService.updateById(storage);
            if(updateById) return "success";
        }
        return null;
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        boolean remove = this.storageService.removeById(id);
        if(remove) return "success";
        return "fail";
    }


}

