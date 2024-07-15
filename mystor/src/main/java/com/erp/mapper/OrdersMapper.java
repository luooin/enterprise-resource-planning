package com.erp.mapper;

import com.erp.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.form.OrdersSearchForm;
import com.erp.form.ReportForm;
import com.erp.mo.OrdersMO;
import com.erp.vo.OrdersVO;
import com.erp.vo.ReportVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2024-02-12
 */
public interface OrdersMapper extends BaseMapper<Orders> {
    public List<OrdersVO> ordersVOList(Long index, Long length, OrdersSearchForm form);
    public List<OrdersVO> ordersReturnVOList(Long index, Long length, OrdersSearchForm form);
    public Long ordersVOCount(OrdersSearchForm form);
    public Long ordersReturnVOCount(OrdersSearchForm form);
    public int batchDelete(OrdersMO ordersMO);
    public int batchVerify(OrdersMO ordersMO);
    public int batchInvalid(OrdersMO ordersMO);
    public List<OrdersVO> saleVOList(Long index, Long length, OrdersSearchForm form);
    public Long saleVOCount(OrdersSearchForm form);
    public List<OrdersVO> saleReturnVOList(Long index, Long length, OrdersSearchForm form);
    public Long saleReturnVOCount(OrdersSearchForm form);
    public List<ReportVO> reportList(Long index, Long length, ReportForm reportForm);
    public Long reportCount(ReportForm reportForm);
    public List<ReportVO> reportExportList();
}
