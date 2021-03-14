package com.casic.weixin.controller;

import com.casic.weixin.common.Result;
import com.casic.weixin.service.CrmService;
import com.sun.org.apache.xpath.internal.objects.XObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CRMController {
    @Autowired
    CrmService crmService;

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
        return "views/crm/wxpage/myBusiness";
    }
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "views/crm/wxpage/register";
    }

    @RequestMapping("/toCustomerList")
    public String toCustomerList(){
        return "views/customer/customerList";
    }

    @RequestMapping("/getCustomerPageList")
    @ResponseBody
    public Map getCustomerPageList(@RequestBody Map<String,Object> param){
        int page = (int) param.get("page");
        int limit=(int) param.get("limit");
        page=page*limit-page;
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("page",page);
        paramMap.put("limit",limit);
        return crmService.getCustomerPageList(paramMap);
    }
    @RequestMapping("/customerLayer")
    public String customerLayer(){
        return "views/customer/customerLayer";
    }
    @RequestMapping("/toDic")
    public String toDic(){
        return "views/crm/dic/dic";
    }
    @RequestMapping("/getDicPageList")
    @ResponseBody
    public Map getDicPageList(@RequestParam("page")String page,@RequestParam("limit")String limit){
        int pageNum = Integer.parseInt(page);
        int limitNum=Integer.parseInt(limit);
        pageNum=pageNum*limitNum-pageNum;
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("page",pageNum);
        paramMap.put("limit",limitNum);
        return crmService.getDicPageList(paramMap);
    }
}
