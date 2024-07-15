package com.erp.form;

import lombok.Data;

@Data
public class ReportForm {
    private Integer storageId;
    private Integer orderType;
    private String materialName;
    private String orderDate1;
    private String orderDate2;
}
