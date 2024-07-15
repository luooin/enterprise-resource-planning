package com.erp.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReportExportVO {
    @ExcelProperty("单据编号")
    private String orderNo;
    @ExcelProperty("单据类型")
    private String orderType;
    @ExcelProperty("供应商")
    private String supplierName;
    @ExcelProperty("开单人")
    private String employeeName;
    @ExcelProperty("开单时间")
    private String orderData;
    @ExcelProperty("物料编码")
    private String materialCode;
    @ExcelProperty("物料名称")
    private String materialName;
    @ExcelProperty("批号")
    private String batchNo;
    @ExcelProperty("采购编码")
    private String orderId;
    @ExcelProperty("仓库")
    private String storageName;
    @ExcelProperty("数量")
    private BigDecimal orderCount;
    @ExcelProperty("冲红")
    private String orderFlag;
}
