package com.erp.mapper;

import com.erp.entity.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2023-12-28
 */
@Controller
public interface StorageMapper extends BaseMapper<Storage> {
    public List<Storage> findByUser(Storage storageVO);
}
