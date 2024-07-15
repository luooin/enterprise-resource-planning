package com.erp.service;

import com.erp.entity.Employee;
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
public interface EmployeeService extends IService<Employee> {
    public PageObject list(PageObject pageObject,Employee employee);
    public boolean setStatus(Integer status,Integer id);
    public boolean resetPassword(Integer id);
}