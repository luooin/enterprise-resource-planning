package com.erp.vo;

import lombok.Data;

@Data
public class AuthorityVO {
    private Integer authorityId;
    private String authorityName;
    private AuthorityVO authority;
    private Integer root;
    private Boolean have = false;
}
