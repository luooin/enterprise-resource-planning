package com.erp.mapper;

import com.erp.entity.RoleAuthority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2024-03-16
 */
public interface RoleAuthorityMapper extends BaseMapper<RoleAuthority> {
    List<Map<String,String>> getAllRoleAuthorityByRoleId(Integer roleId);
}
