package com.erp.controller;


import com.erp.service.LoginRecordService;
import com.erp.util.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2024-03-15
 */
@Controller
@RequestMapping("/loginRecord")
public class LoginRecordController {
    @Autowired
    private LoginRecordService loginRecordService;

    @RequestMapping("/list")
    public String list(PageObject pageObject, Model model){
        model.addAttribute("page", this.loginRecordService.loginRecordList(pageObject));
        return "loginRecordList";
    }
}

