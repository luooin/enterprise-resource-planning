package com.erp.service;

import com.erp.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.vo.DepartmentVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2024-03-15
 */
public interface DepartmentService extends IService<Department> {
    public List<DepartmentVO> departmentList();
}
