package com.erp.service;

import com.erp.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.util.PageObject;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2024-03-16
 */
public interface RoleService extends IService<Role> {
    public PageObject roleList(PageObject pageObject);
}
