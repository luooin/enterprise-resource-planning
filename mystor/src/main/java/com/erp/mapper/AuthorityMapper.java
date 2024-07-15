package com.erp.mapper;

import com.erp.entity.Authority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.vo.AuthorityVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2024-03-16
 */
public interface AuthorityMapper extends BaseMapper<Authority> {
    public List<AuthorityVO> list();
}
