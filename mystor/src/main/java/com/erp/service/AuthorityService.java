package com.erp.service;

import com.erp.entity.Authority;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.vo.AuthorityVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2024-03-16
 */
public interface AuthorityService extends IService<Authority> {
    public List<AuthorityVO> authorityVOList();
}
