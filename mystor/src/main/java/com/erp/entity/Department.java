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
    public class Department implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "department_id", type = IdType.AUTO)
      private Integer departmentId;

    private String departmentName;

    private Integer parentId;

    private String chairman;

    private String remark;


}
