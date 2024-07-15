package com.erp.service.impl;

import com.erp.entity.Department;
import com.erp.mapper.DepartmentMapper;
import com.erp.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.vo.DepartmentVO;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public List<DepartmentVO> departmentList() {
        List<DepartmentVO> list = this.departmentMapper.list();
        return list;
    }
}
