package com.erp.service.impl;

import com.erp.entity.RoleAuthority;
import com.erp.mapper.RoleAuthorityMapper;
import com.erp.service.RoleAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2024-03-16
 */
@Service
public class RoleAuthorityServiceImpl extends ServiceImpl<RoleAuthorityMapper, RoleAuthority> implements RoleAuthorityService {

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public List<Map<String,String>> getAllRoleAuthorityByRoleId(Integer roleId) {
        return roleAuthorityMapper.getAllRoleAuthorityByRoleId(roleId);
    }
}
