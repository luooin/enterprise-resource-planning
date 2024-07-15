package com.erp.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StorageReportVO {
    private String storageName;
    private String materialCode;
    private String materialName;
    private String style;
    private String batchNo;
    private String unitName;
    private String orderId;
    private BigDecimal orderCount;
    private LocalDateTime orderDate;
}
