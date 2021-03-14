package com.casic.weixin.dao;

import com.casic.weixin.bean.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CrmDao {

    Long getTotalDicCount(Map<String, Object> paramMap);

    List<Map<String,Object>> getDicPageList(Map<String, Object> paramMap);

    Map<String, Object> getDicMapById(@Param("id") int id);

    List<Map<String, Object>> getDicMapListByParentId(@Param("parentId") int parentId);
}
