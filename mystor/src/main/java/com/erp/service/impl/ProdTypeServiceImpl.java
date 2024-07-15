package com.erp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.entity.ProdType;
import com.erp.mapper.ProdTypeMapper;
import com.erp.service.ProdTypeService;
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
public class ProdTypeServiceImpl extends ServiceImpl<ProdTypeMapper, ProdType> implements ProdTypeService {
    @Autowired
    private ProdTypeMapper prodTypeMapper;
    @Override
    public PageObject list(PageObject pageObject) {
        Page<ProdType> page=new Page<>(pageObject.getCurrent(),pageObject.getSize());
        Page<ProdType> resultPage=this.prodTypeMapper.selectPage(page,null);
        PageObject result=new PageObject();
        result.setTotal(resultPage.getTotal());
        result.setCurrent(resultPage.getCurrent());
        result.setSize(resultPage.getSize());
        result.setData(resultPage.getRecords());
        return result;
    }
}
