package com.casic.weixin.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XMLUtil {

    public static Map<String,String> reqMsg2Map(HttpServletRequest request){
        String mxlstr=regXml2String(request);
        Map<String,String> map=new HashMap<>();
        try {
            Document document= DocumentHelper.parseText(mxlstr);
            Element root=document.getRootElement();
            List<Element> eles=root.elements();
            for(Element e:eles){
                map.put(e.getName(),e.getTextTrim());
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析request中的xml为string
     * @param request
     * @return
     */
    public static String regXml2String(HttpServletRequest request){
        try {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(request.getInputStream()));
            String str=null;
            StringBuilder stringBuilder=new StringBuilder();
            while ((str=bufferedReader.readLine())!=null){
                stringBuilder.append(str);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * map转换xml
     * @param paramMap
     * @return
     */
    public static String map2Xml(Map<String,String>paramMap){
        Document document=DocumentHelper.createDocument();
        Element root=document.addElement("xml");
        Set<String>keys=paramMap.keySet();
        for(String key:keys){
            root.addElement(key).addText(paramMap.get(key));
        }
        StringWriter stringWriter=new StringWriter();
        try {
            document.write(stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
