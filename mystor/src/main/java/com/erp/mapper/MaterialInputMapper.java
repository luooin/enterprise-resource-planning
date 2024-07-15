package com.erp.mapper;

import com.erp.entity.MaterialInput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.mo.MaterialInputMO;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2023-12-25
 */
@Controller
public interface MaterialInputMapper extends BaseMapper<MaterialInput> {
    public  int verify(MaterialInputMO materialInputMO);
}
