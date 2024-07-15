package com.erp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.erp.entity.OrderDetail;
import com.erp.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2024-02-12
 */
@Controller
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/getOrderDetail")
    @ResponseBody
    public List<OrderDetail> getOrderDetail(String orderNo){
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        List<OrderDetail> list = this.orderDetailService.list(queryWrapper);
        return list;
    }
    @PostMapping("/checkBatchNoForEdit")
    @ResponseBody
    public String checkBatchNoForEdit(String orderNo,String batchNoStr){
        boolean checkBatchNo = this.orderDetailService.checkBatchNo(orderNo, batchNoStr);
        if(checkBatchNo) return "success";
        return "fail";
    }

}

