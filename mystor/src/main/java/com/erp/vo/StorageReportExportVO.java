package com.erp.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StorageReportExportVO {
    @ExcelProperty("仓库")
    private String storageName;
    @ExcelProperty("物料编码")
    private String materialCode;
    @ExcelProperty("物料名称")
    private String materialName;
    @ExcelProperty("规格")
    private String style;
    @ExcelProperty("批号")
    private String batchNo;
    @ExcelProperty("单位")
    private String unitName;
    @ExcelProperty("订单号")
    private String orderId;
    @ExcelProperty("数量")
    private BigDecimal orderCount;
    @ExcelProperty("日期")
    private String orderDate;
}
