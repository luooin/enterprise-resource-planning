package com.erp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.entity.MaterialType;
import com.erp.mapper.MaterialTypeMapper;
import com.erp.service.MaterialTypeService;
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
 * @since 2024-03-15
 */
@Service
public class MaterialTypeServiceImpl extends ServiceImpl<MaterialTypeMapper, MaterialType> implements MaterialTypeService {
    @Autowired
    private MaterialTypeMapper materialTypeMapper;
    @Override
    public PageObject list(PageObject pageObject) {
        Page<MaterialType> page=new Page<>(pageObject.getCurrent(),pageObject.getSize());
        Page<MaterialType> resultPage=this.materialTypeMapper.selectPage(page,null);
        PageObject result=new PageObject();
        result.setTotal(resultPage.getTotal());
        result.setCurrent(resultPage.getCurrent());
        result.setSize(resultPage.getSize());
        result.setData(resultPage.getRecords());
        return result;
    }
}
