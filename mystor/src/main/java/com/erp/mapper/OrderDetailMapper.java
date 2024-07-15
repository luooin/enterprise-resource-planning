package com.erp.mapper;

import com.erp.entity.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.form.ReportForm;
import com.erp.mo.OrderDetailMO;
import com.erp.mo.OrdersMO;
import com.erp.vo.StorageReportVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2024-02-12
 */
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
    public int batchDelete(OrdersMO ordersMO);
    public int checkBatchNo(OrderDetailMO orderDetailMO);
    public List<OrderDetail> orderDetailList();
    public List<OrderDetail> saleReturnOrdersDetailList();
    public int updateOrderCount(BigDecimal count, String batchNo, Integer orderType);
    public BigDecimal getOrderCount(String batchNo, Integer orderType);
    public List<StorageReportVO>  storageReportVOList(Long index, Long length, ReportForm form);
    public Long storageReportCount(ReportForm form);
    public List<StorageReportVO> storageReportVOExportList();
}
