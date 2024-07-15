package com.erp.form;

import lombok.Data;

@Data
public class OrdersAddForm {
     private String orderNo;
     private String orderDate;
     private Integer supplierId;
     private Integer orderType;
     private Integer invalid;
     private Integer status;
     private String remark;
     private String orderDetailsStr;
}
