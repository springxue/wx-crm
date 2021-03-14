package com.casic.weixin.service;

import com.casic.weixin.bean.MessageType;
import com.casic.weixin.bean.Customer;
import com.casic.weixin.util.DateUtil;
import com.casic.weixin.util.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessageService {
    @Autowired
    CommonService commonService;
    /**
     * 自动处理用户发送的消息
     * @param request
     * @param response
     */
    public  void autoHandel(HttpServletRequest request, HttpServletResponse response) {
        Map<String,String> messageMap=XMLUtil.reqMsg2Map(request);
        System.out.println(messageMap);

        String msgType=messageMap.get("MsgType");
        Map<String,Object>replyMap=new HashMap<>();
        if(MessageType.MSG_IMAGE_TYPE.equals(msgType)){
            replyMap.put("ToUserName",messageMap.get("FromUserName"));
            replyMap.put("FromUserName",messageMap.get("ToUserName"));
            replyMap.put("CreateTime",String.valueOf(new Date().getTime()));
            replyMap.put("MsgType","text");
            String content="发送消息成功！";
            replyMap.put("Content",content);
        }else if(MessageType.MSG_TEXT_TYPE.equals(msgType)){
            replyMap.put("ToUserName",messageMap.get("FromUserName"));
            replyMap.put("FromUserName",messageMap.get("ToUserName"));
            replyMap.put("CreateTime",String.valueOf(new Date().getTime()));
            replyMap.put("MsgType","image");
            Map<String,String>mediaIdMap=new HashMap<>();
            mediaIdMap.put("MediaId","wHVsXUPbRgNHWOxFVEkMZ7OHEuWMTMCSKMydpbqQffjzu3AulLZ6WR5ZByUhNpAU");
            replyMap.put("Image",mediaIdMap);
        }else if(MessageType.MSG_EVENT_TYPE.equals(msgType)){
            if("subscribe".equals(messageMap.get("Event"))){
                //查询基本信息存入数据库
                Customer customer=commonService.getBasicUserInfo(messageMap.get("FromUserName"));
                customer.setSubscribe_time(DateUtil.timeStamp2Date(customer.getSubscribe_time(),null));
                commonService.addCustomer(customer);

                replyMap.put("ToUserName",messageMap.get("FromUserName"));
                replyMap.put("FromUserName",messageMap.get("ToUserName"));
                replyMap.put("CreateTime",String.valueOf(new Date().getTime()));
                replyMap.put("MsgType","text");
                String content="欢迎关注商机云!";
                replyMap.put("Content",content);
            }else if("unsubscribe".equals(messageMap.get("Event"))){
                //取消关注
                System.out.println("取消关注");
            }
        }
        PrintWriter writer = null;
        try {
            response.setContentType("application/xml;charset=UTF-8");
            response.setContentType("UTF-8");
            writer=response.getWriter();
            System.out.println(XMLUtil.multilayerMapToXml(replyMap,true));
            writer.write(XMLUtil.multilayerMapToXml(replyMap,true));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.close();
        }

    }

}
