package com.erp.service;

import com.erp.entity.RoleAuthority;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2024-03-16
 */
public interface RoleAuthorityService extends IService<RoleAuthority> {
    List<Map<String,String>> getAllRoleAuthorityByRoleId(Integer roleId);
}
