package com.erp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
    public class LoginRecord implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "login_record_id", type = IdType.AUTO)
      private Integer loginRecordId;

    private Integer employeeId;

    private String employeeName;

    private LocalDateTime time;

    private String ipaddress;


}
