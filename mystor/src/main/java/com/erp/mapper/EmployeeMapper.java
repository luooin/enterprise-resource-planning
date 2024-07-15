package com.erp.mapper;

import com.erp.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.vo.EmployeeVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2024-03-15
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    public List<EmployeeVO> list(Long index, Long length,Employee employee);
    public Long count(Employee employee);
    public int setStatus(Integer status,Integer id);
    public int resetPassword(String password,Integer id);
}
