package com.erp.service;

import com.erp.entity.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.util.PageObject;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2023-12-28
 */
public interface SupplierService extends IService<Supplier> {
    public PageObject list(PageObject pageObject);
}
