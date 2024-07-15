package com.erp.vo;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentVO {
    private Integer departmentId;
    private String departmentName;
    private Integer parentId;
    private String chairman;
    private String remark;
    private DepartmentVO department;
    private List<EmployeeVO> employees;
}
