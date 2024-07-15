package com.erp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2023-12-28
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Material implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "material_id", type = IdType.AUTO)
      private Integer materialId;

    private String materialCode;

    private String materialName;

    private String style;

    private String remark;

    private String materialUnit;

    private String bagUnit;

    private Integer prodTypeId;

    private Integer materialTypeId;

    private String formula;


}
