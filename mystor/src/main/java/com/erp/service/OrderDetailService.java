package com.erp.service;

import com.erp.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.form.ReportForm;
import com.erp.util.PageObject;
import com.erp.vo.StorageReportVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2024-02-12
 */
public interface OrderDetailService extends IService<OrderDetail> {
    public boolean checkBatchNo(String batchNoStr);
    public boolean checkBatchNo(String orderNo,String batchNoStr);
    public List<OrderDetail> orderDetailList();
    public List<OrderDetail> saleReturnOrdersDetailList();
    public PageObject storageReportList(PageObject pageObject, ReportForm form);
    public List<StorageReportVO> storageReportVOExportList();

}
