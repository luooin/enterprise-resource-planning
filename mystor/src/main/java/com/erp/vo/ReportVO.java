package com.erp.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReportVO {
    private String orderNo;
    private Integer orderType;
    private String supplierName;
    private String employeeName;
    private LocalDateTime orderDate;
    private String materialCode;
    private String materialName;
    private String batchNo;
    private String orderId;
    private String storageName;
    private BigDecimal orderCount;
    private String orderFlag;
}
