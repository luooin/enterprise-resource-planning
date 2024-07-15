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
 * @since 2024-03-15
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class ProdType implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "prod_type_id", type = IdType.AUTO)
      private Integer prodTypeId;

    private String prodTypeCode;

    private String prodTypeName;


}
