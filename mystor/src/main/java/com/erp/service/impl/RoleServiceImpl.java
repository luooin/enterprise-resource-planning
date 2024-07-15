package com.erp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.entity.Role;
import com.erp.mapper.RoleMapper;
import com.erp.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2024-03-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageObject roleList(PageObject pageObject) {
        Page<Role> page = new Page<>(pageObject.getCurrent(), pageObject.getSize());
        Page<Role> resultPage = this.roleMapper.selectPage(page, null);
        PageObject result = new PageObject();
        result.setTotal(resultPage.getTotal());
        result.setCurrent(resultPage.getCurrent());
        result.setSize(resultPage.getSize());
        result.setData(resultPage.getRecords());
        return result;
    }
}
