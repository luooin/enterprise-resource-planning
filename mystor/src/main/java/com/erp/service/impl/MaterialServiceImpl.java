package com.erp.service.impl;

import com.erp.entity.Material;
import com.erp.mapper.MaterialMapper;
import com.erp.service.MaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.util.PageObject;
import com.erp.vo.MaterialVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2023-12-28
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public PageObject list(PageObject pageObject) {
        Long index = (pageObject.getCurrent()-1)*pageObject.getSize();
        Long length = pageObject.getSize();
        List<MaterialVO> list = this.materialMapper.list(index, length);
        PageObject result = new PageObject();
        result.setData(list);
        result.setSize(pageObject.getSize());
        result.setCurrent(pageObject.getCurrent());
        result.setTotal(this.materialMapper.count());
        return result;
    }
}
