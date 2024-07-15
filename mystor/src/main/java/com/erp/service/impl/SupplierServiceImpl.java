package com.erp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.entity.Supplier;
import com.erp.mapper.SupplierMapper;
import com.erp.service.SupplierService;
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
 * @since 2023-12-28
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {
    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public PageObject list(PageObject pageObject) {
        Page<Supplier> page = new Page<>(pageObject.getCurrent(), pageObject.getSize());
        Page<Supplier> resultPage = this.supplierMapper.selectPage(page, null);
        PageObject result = new PageObject();
        result.setTotal(resultPage.getTotal());
        result.setCurrent(resultPage.getCurrent());
        result.setSize(resultPage.getSize());
        result.setData(resultPage.getRecords());
        return result;
    }
}
