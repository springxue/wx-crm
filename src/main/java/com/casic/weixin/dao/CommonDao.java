package com.casic.weixin.dao;

import com.casic.weixin.bean.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface CommonDao {
    //根据账号查询用户
    public Customer getCustomerByOpenId(@Param("openid") String openid);
    public void addCustomer(Customer user);
}
