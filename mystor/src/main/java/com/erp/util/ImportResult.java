package com.erp.util;

import lombok.Data;

@Data
public class ImportResult {
    //0表示成功-1表示失败
    private Integer code;
    //记录失败原因
    private String msg;
}
