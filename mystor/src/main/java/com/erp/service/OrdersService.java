package com.erp.service;

import com.erp.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.form.OrdersAddForm;
import com.erp.form.OrdersSearchForm;
import com.erp.form.ReportForm;
import com.erp.util.PageObject;
import com.erp.vo.ReportVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2024-02-12
 */
public interface OrdersService extends IService<Orders> {
    public PageObject ordersList(PageObject pageObject, OrdersSearchForm form);
    public PageObject ordersReturnList(PageObject pageObject, OrdersSearchForm form);
    public boolean batchDelete(String orderNoArr);
    public boolean batchVerify(String orderNoArr);
    public boolean batchInvalid(String orderNoArr);
    public boolean save(OrdersAddForm ordersAddForm);
    public boolean update(OrdersAddForm ordersAddForm);
    public boolean delete(String orderNo);
    public boolean verify(String orderNo);
    public boolean invalid(String orderNo);
    public boolean ordersReturn(OrdersAddForm ordersAddForm);
    public PageObject saleList(PageObject pageObject, OrdersSearchForm form);
    public boolean saleSave(OrdersAddForm ordersAddForm);
    public PageObject saleReturnList(PageObject pageObject, OrdersSearchForm form);
    public boolean saleReturn(OrdersAddForm ordersAddForm);
    public PageObject reportList(PageObject pageObject, ReportForm reportForm);
    public List<ReportVO> reportList();

}
