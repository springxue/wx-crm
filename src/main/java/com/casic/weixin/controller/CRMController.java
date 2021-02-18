package com.casic.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CRMController {

    @RequestMapping("/toCompanyInfo")
    public String toCompanyInfo(){
        return "views/index";
    }
    @RequestMapping("/toConsole")
    public String toConsole(){
        return "views/home/console";
    }
    @RequestMapping("/myBusiness")
    public String toMyBusiness(){
        return "views/crm/myBusiness";
    }
}
