package com.casic.weixin.dao;

import com.casic.weixin.bean.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CustomerDao {
    Long getTotalCustomerCount(Map<String,Object> paramMap);
    List<Customer> getCustomerPageList(Map<String,Object> paramMap);
}
