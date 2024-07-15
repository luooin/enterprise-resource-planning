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
    public class Authority implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "authority_id", type = IdType.AUTO)
      private Integer authorityId;

    private String authorityName;

    private Integer root;

    private String controller;


}
