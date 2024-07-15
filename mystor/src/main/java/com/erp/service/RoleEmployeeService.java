package com.erp.service;

import com.erp.entity.RoleEmployee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.vo.InterceptorAuthorityVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2024-03-16
 */
public interface RoleEmployeeService extends IService<RoleEmployee> {
    public List<InterceptorAuthorityVO> interceptorAuthorityList(Integer employeeId);
}
