package com.erp.vo;

import lombok.Data;

@Data
public class EmployeeVO {
    private Integer employeeId;
    private String employeeName;
    private String departmentName;
    private String sex;
    private String phone;
    private String address;
    private Integer status;
    private Boolean have = false;
}
