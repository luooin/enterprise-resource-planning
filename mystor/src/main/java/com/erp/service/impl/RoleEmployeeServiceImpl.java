package com.erp.service.impl;

import com.erp.entity.RoleEmployee;
import com.erp.mapper.RoleEmployeeMapper;
import com.erp.service.RoleEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.vo.InterceptorAuthorityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2024-03-16
 */
@Service
public class RoleEmployeeServiceImpl extends ServiceImpl<RoleEmployeeMapper, RoleEmployee> implements RoleEmployeeService {
    @Autowired
    private RoleEmployeeMapper roleEmployeeMapper;

    @Override
    public List<InterceptorAuthorityVO> interceptorAuthorityList(Integer employeeId) {
        return this.roleEmployeeMapper.interceptorAuthorityList(employeeId);
    }
}
