package com.erp.controller;

import com.alibaba.excel.EasyExcel;
import com.erp.form.ReportForm;
import com.erp.service.OrdersService;
import com.erp.service.StorageService;

import com.erp.util.CustomCellWriteHandler;
import com.erp.util.PageObject;
import com.erp.vo.ReportExportVO;
import com.erp.vo.ReportVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("report")
public class ReportController{
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private StorageService storageService;
    @RequestMapping("/list")
    private String list(PageObject pageObject, ReportForm reportForm, Model model){
        model.addAttribute("page",this.ordersService.reportList(pageObject,reportForm));
        model.addAttribute("reportForm",reportForm);
        model.addAttribute("storageList",this.storageService.list());
        return "reportList";
    }

    @GetMapping("/export")
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode("仓库明细报表", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //获取数据
            List<ReportVO> reportVOList = this.ordersService.reportList();
            List<ReportExportVO> exportVOList = new ArrayList<>();
            for (ReportVO reportVO : reportVOList) {
                ReportExportVO vo = new ReportExportVO();
                BeanUtils.copyProperties(reportVO, vo);
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                vo.setOrderData(reportVO.getOrderDate().format(dateTimeFormatter));
                String orderType = null;
                switch (reportVO.getOrderType()){
                    case 1:
                        orderType = "采购入库单";
                        break;
                    case 2:
                        orderType = "采购退货单";
                        break;
                    case 3:
                        orderType = "销售出库单";
                        break;
                    case 4:
                        orderType = "销售退货单";
                        break;
                }
                vo.setOrderType(orderType);
                exportVOList.add(vo);
            }
            EasyExcel.write(response.getOutputStream(), ReportExportVO.class)
                    .registerWriteHandler(new CustomCellWriteHandler())
                    .sheet("仓库明细")
                    .doWrite(exportVOList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
