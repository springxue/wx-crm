package com.casic.weixin.service;

import com.casic.weixin.util.XMLUtil;
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
    /**
     * 自动处理用户发送的消息
     * @param request
     * @param response
     */
    public  void autoReply(HttpServletRequest request, HttpServletResponse response) {
        Map<String,String> messageMap=XMLUtil.reqMsg2Map(request);
        System.out.println(messageMap);
        String msgType=messageMap.get("MsgType");
        Map<String,String>replyMap=new HashMap<>();
        replyMap.put("ToUserName",messageMap.get("FromUserName"));
        replyMap.put("FromUserName",messageMap.get("ToUserName"));
        replyMap.put("CreateTime",String.valueOf(new Date().getTime()));
        replyMap.put("MsgType","text");
        String content="发送消息成功！";
        replyMap.put("Content",content);
        PrintWriter writer = null;
        try {
            response.setContentType("application/xml;charset=UTF-8");
            response.setContentType("UTF-8");
            writer=response.getWriter();
            writer.write(XMLUtil.map2Xml(replyMap));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.close();
        }

    }

}
