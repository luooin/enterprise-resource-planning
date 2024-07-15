package com.erp.service;

import com.erp.entity.MaterialType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.util.PageObject;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2024-03-15
 */
public interface MaterialTypeService extends IService<MaterialType> {
    public PageObject list(PageObject pageObject);
}
