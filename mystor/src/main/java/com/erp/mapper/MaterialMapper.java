package com.erp.mapper;

import com.erp.entity.Material;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.vo.MaterialVO;
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
public interface MaterialMapper extends BaseMapper<Material> {
    public List<MaterialVO> list(Long index,Long length);
    public Long count();
}
