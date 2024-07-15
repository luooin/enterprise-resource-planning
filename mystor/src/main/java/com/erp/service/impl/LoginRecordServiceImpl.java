package com.erp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.entity.LoginRecord;
import com.erp.mapper.LoginRecordMapper;
import com.erp.service.LoginRecordService;
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
public class LoginRecordServiceImpl extends ServiceImpl<LoginRecordMapper, LoginRecord> implements LoginRecordService {

    @Autowired
    private LoginRecordMapper loginRecordMapper;
    @Override
    public PageObject loginRecordList(PageObject pageObject) {
        Page<LoginRecord> page=new Page<>(pageObject.getCurrent(), pageObject.getSize());
        Page<LoginRecord> resultPage = this.loginRecordMapper.selectPage(page, null);
        PageObject result=new PageObject();
        result.setData(resultPage.getRecords());
        result.setSize(resultPage.getSize());
        result.setCurrent(resultPage.getCurrent());
        result.setTotal(resultPage.getTotal());
        return result;
    }
}
