package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.businessClassify.businessClassify;
import org.springframework.stereotype.Repository;

@Repository
public interface businessClassifyMapper {
    int insert(businessClassify record);

    int insertSelective(businessClassify record);
}
