package com.casic.weixin.controller;

import com.casic.weixin.bean.Customer;
import com.casic.weixin.common.Result;
import com.casic.weixin.service.CommonService;
import com.casic.weixin.util.CheckoutUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
public class CommonController {
    @Autowired
    CommonService commonService;
    /**
     * 完成微信认证
     * @param request
     * @param response
     */
    @GetMapping("/")
    public void auth(HttpServletRequest request, HttpServletResponse response){
//        System.out.println(request.getParameter("signature"));
//        System.out.println(request.getParameter("timestamp"));
//        System.out.println(request.getParameter("nonce"));
//        System.out.println(request.getParameter("echostr"));
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        PrintWriter print;
        if (isGet) {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
                try {
                    print = response.getWriter();
                    print.write(echostr);
                    print.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




    /**
     * 设置微信公众号菜单
     * @return
     */
    public Result setMenu(){
        String url="";
        RestTemplate restTemplate=new RestTemplate();
//        restTemplate.postForObject();
        return Result.ok();
    }

    /**
     * 获取用户基本信息
     * @return
     */
    public Customer getUserInfo(String openid){
        return commonService.getBasicUserInfo(openid);
    }
}
