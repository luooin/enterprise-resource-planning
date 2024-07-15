package com.erp.service.impl;

import com.erp.entity.Authority;
import com.erp.mapper.AuthorityMapper;
import com.erp.service.AuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.vo.AuthorityVO;
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
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public List<AuthorityVO> authorityVOList() {
        return this.authorityMapper.list();
    }
}
