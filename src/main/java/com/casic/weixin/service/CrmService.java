package com.casic.weixin.service;

import com.casic.weixin.bean.Customer;
import com.casic.weixin.common.Result;
import com.casic.weixin.dao.CrmDao;
import com.casic.weixin.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrmService {
    @Autowired
    CustomerDao customerDao;
    @Autowired
    CrmDao crmDao;

    public Map<String, Object> getCustomerPageList(Map<String,Object> paramMap) {
        Long totalCount=customerDao.getTotalCustomerCount(paramMap);
        List<Customer> customerList= customerDao.getCustomerPageList(paramMap);
        Map<String,Object>resultMap=new HashMap<>();
        resultMap.put("data",customerList);
        resultMap.put("count",totalCount);
        resultMap.put("code",0);
        resultMap.put("msg","ok");
        return resultMap;
    }

    public Map<String,Object> getDicPageList(Map<String,Object> paramMap){
        Long totalCount=crmDao.getTotalDicCount(paramMap);
        List<Map<String,Object>> dicMapList= crmDao.getDicPageList(paramMap);
        List<Map<String,Object>> data=crmDao.getDicMapListByParentId(0);
        for(int i=0;i<data.size();i++){
            data.set(i,getChildDicMap((Integer) data.get(i).get("id")));
        }
        System.out.println(data);
        Map<String,Object>resultMap=new HashMap<>();
        resultMap.put("data",data);
        resultMap.put("count",totalCount);
        resultMap.put("code",0);
        resultMap.put("msg","ok");
        return resultMap;
    }
    public Map<String,Object> getChildDicMap(int id){
        Map<String,Object>dicMap=crmDao.getDicMapById(id);
        List<Map<String,Object>>dicMapList=crmDao.getDicMapListByParentId(id);
        if(dicMapList.size()==0){
            return dicMap;
        }else {
            for(int i=0;i<dicMapList.size();i++){
                Map<String,Object>childDicMap=dicMapList.get(i);
                dicMapList.set(i,getChildDicMap((Integer) childDicMap.get("id")));
            }
            dicMap.put("treeList",dicMapList);
            return dicMap;
        }

    }
}
