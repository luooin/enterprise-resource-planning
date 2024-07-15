package com.erp.service;

import com.erp.entity.LoginRecord;
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
public interface LoginRecordService extends IService<LoginRecord> {
    public PageObject loginRecordList(PageObject pageObject);
}
