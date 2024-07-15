package com.erp.mapper;

import com.erp.entity.RoleEmployee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.vo.InterceptorAuthorityVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2024-03-16
 */
public interface RoleEmployeeMapper extends BaseMapper<RoleEmployee> {
    public List<InterceptorAuthorityVO> interceptorAuthorityList(Integer employeeId);
}
