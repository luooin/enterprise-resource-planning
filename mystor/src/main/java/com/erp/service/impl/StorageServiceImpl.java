package com.erp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.entity.Storage;
import com.erp.mapper.StorageMapper;
import com.erp.service.StorageService;
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
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {
    @Autowired
    private StorageMapper storageMapper;
    @Override
    public PageObject list(PageObject pageObject) {
        Page<Storage> page=new Page<>(pageObject.getCurrent(),pageObject.getSize());
        Page<Storage> resultPage=this.storageMapper.selectPage(page,null);
        PageObject result=new PageObject();
        result.setTotal(resultPage.getTotal());
        result.setCurrent(resultPage.getCurrent());
        result.setSize(resultPage.getSize());
        result.setData(resultPage.getRecords());
        return result;
    }
}
