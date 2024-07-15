package com.erp.service.impl;

import com.erp.entity.Employee;
import com.erp.mapper.EmployeeMapper;
import com.erp.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.util.AesUtils;
import com.erp.util.PageObject;
import com.erp.vo.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2024-03-15
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
   @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public PageObject list(PageObject pageObject,Employee employee) {
        Long index=(pageObject.getCurrent()-1)*pageObject.getSize();
        Long length=pageObject.getSize();
        List<EmployeeVO> list = this.employeeMapper.list(index, length,employee);
        PageObject result=new PageObject();
        result.setData(list);
        result.setSize(pageObject.getSize());
        result.setCurrent(pageObject.getCurrent());
        result.setTotal(this.employeeMapper.count(employee));
        return result;
    }

    @Override
    public boolean setStatus(Integer status, Integer id) {
        int setStatus = this.employeeMapper.setStatus(status, id);
        if(setStatus==1) return true;
        return false;
    }

    @Override
    public boolean resetPassword(Integer id) {
        String password= AesUtils.encryptStr("123456",AesUtils.SECRETKEY);
        int resetPassword = this.employeeMapper.resetPassword(password, id);
        if(resetPassword==1) return true;
        return false;
    }
}
