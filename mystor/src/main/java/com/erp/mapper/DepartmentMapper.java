package com.erp.mapper;

import com.erp.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.vo.DepartmentVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2024-03-15
 */
public interface DepartmentMapper extends BaseMapper<Department> {
    public List<DepartmentVO> list();
}
