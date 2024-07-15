package com.erp.controller;

import com.erp.entity.OrderDetail;
import com.erp.form.OrdersAddForm;
import com.erp.form.OrdersSearchForm;
import com.erp.service.OrderDetailService;
import com.erp.service.OrdersService;
import com.erp.service.SupplierService;
import com.erp.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private OrderDetailService orderDetailService;
    @RequestMapping("/list")
    public String list(PageObject pageObject, Model model, OrdersSearchForm form){
        model.addAttribute("page",this.ordersService.saleList(pageObject,form));
        model.addAttribute("ordersForm",form);
        model.addAttribute("supplierList",this.supplierService.list());
        return  "saleList";

    }

    @GetMapping("/init")
    public String init(Model model){
        model.addAttribute("supplierList",this.supplierService.list());
        return "saleAdd";
    }
    @GetMapping("/returnInit")
    public String returnInit(Model model){
        model.addAttribute("supplierList",this.supplierService.list());
        return "saleReturnAdd";
    }


    @PostMapping("/save")
    @ResponseBody
    public String save(OrdersAddForm ordersAddForm){
        boolean save = this.ordersService.saleSave(ordersAddForm);
        if(save) return "success";
        return "fail";
    }


    @RequestMapping("/returnList")
    public String returnList(PageObject pageObject, Model model, OrdersSearchForm form){
        model.addAttribute("page", this.ordersService.saleReturnList(pageObject, form));
        model.addAttribute("ordersForm", form);
        model.addAttribute("supplierList",this.supplierService.list());
        return "saleReturnList";
    }

    @GetMapping("/saleReturnOrdersDetailList")
    @ResponseBody
    public List<OrderDetail> ordersDetailList(){
        return this.orderDetailService.saleReturnOrdersDetailList();
    }

    @PostMapping("/return")
    @ResponseBody
    public String ordersReturn(OrdersAddForm ordersAddForm){
        boolean save = this.ordersService.saleReturn(ordersAddForm);
        if(save) return "success";
        return "fail";
    }

}
