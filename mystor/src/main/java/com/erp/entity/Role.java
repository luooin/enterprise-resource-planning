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
 * @since 2024-03-16
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Role implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "role_id", type = IdType.AUTO)
      private Integer roleId;

    private String roleName;


}
