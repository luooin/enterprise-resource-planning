package com.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erp.entity.OrderDetail;
import com.erp.form.ReportForm;
import com.erp.mapper.OrderDetailMapper;
import com.erp.mo.OrderDetailMO;
import com.erp.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.util.PageObject;
import com.erp.vo.StorageReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2024-02-12
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Override
    public boolean checkBatchNo(String batchNoStr) {
        QueryWrapper<OrderDetail> orderDetailQueryWrapper=new QueryWrapper<>();
        String[] split =batchNoStr.split(",");
        List<String> batchNoList=new ArrayList<>();
        for (String batchNo : split) {
                batchNoList.add(batchNo);
        }
        OrderDetailMO orderDetailMO=new OrderDetailMO();
        orderDetailMO.setBatchNoList(batchNoList);
        int count = this.orderDetailMapper.checkBatchNo(orderDetailMO);
        if(count==0) return true;
        return false;
    }

    @Override
    public boolean checkBatchNo(String orderNo, String batchNoStr) {
        String[] split =batchNoStr.split(",");
        for (String batchNo : split) {
            QueryWrapper<OrderDetail> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("batch_no",batchNo);
            List<OrderDetail> orderDetails=this.orderDetailMapper.selectList(queryWrapper);
            for (OrderDetail orderDetail : orderDetails) {
                if(!orderDetail.getOrderNo().equals(orderNo)) return false;
            }
        }
        return true;
    }

    @Override
    public List<OrderDetail> orderDetailList() {
        return this.orderDetailMapper.orderDetailList();
    }

    @Override
    public List<OrderDetail> saleReturnOrdersDetailList() {
        return this.orderDetailMapper.saleReturnOrdersDetailList();
    }

    @Override
    public PageObject storageReportList(PageObject pageObject, ReportForm form) {
        Long index = (pageObject.getCurrent()-1)*pageObject.getSize();
        Long length = pageObject.getSize();
        List<StorageReportVO> reportVOS = this.orderDetailMapper.storageReportVOList(index,length,form);
        PageObject result=new PageObject();
        result.setData(reportVOS);
        result.setSize(pageObject.getSize());
        result.setCurrent(pageObject.getCurrent());
        result.setTotal(this.orderDetailMapper.storageReportCount(form));
        return result;
    }

    @Override
    public List<StorageReportVO> storageReportVOExportList() {
        return this.orderDetailMapper.storageReportVOExportList();
    }

}
