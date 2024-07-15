package com.erp.entity;

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
 * @since 2024-02-12
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Orders implements Serializable {

    private static final long serialVersionUID=1L;

      private String orderNo;

    private Integer supplierId;

    private Integer invalid;

    private Integer status;

    private String verifyPerson;

    private LocalDateTime verifyDate;

    private String employeeName;

    private LocalDateTime orderDate;

    private String remark;

    private Integer orderType;

  public Orders(Integer status, Integer orderType, Integer invalid, LocalDateTime verifyDate, LocalDateTime orderDate) {
    this.status = status;
    this.orderType = orderType;
    this.invalid = invalid;
    this.verifyDate = verifyDate;
    this.orderDate = orderDate;
  }

  public Orders() {
  }

}
