package com.erp.service;

import com.erp.entity.ProdType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.util.PageObject;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2024-03-15
 */
@Service
public interface ProdTypeService extends IService<ProdType> {
    public PageObject list(PageObject pageObject);
}
