package com.erp.vo;

import lombok.Data;

@Data
public class MaterialVO {
    private Integer materialId;
    private String materialCode;
    private String materialName;
    private String style;
    private String remark;
    private String materialUnit;
    private String bagUnit;
    private Integer prodTypeId;
    private Integer materialTypeId;
    private String formula;
    private String materialTypeCode;
    private String materialTypeName;
    private String prodTypeCode;
    private String prodTypeName;
}
