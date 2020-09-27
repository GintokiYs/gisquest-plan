package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.resourceClassify.resourceClassify;

public interface resourceClassifyMapper {
    int insert(resourceClassify record);

    int insertSelective(resourceClassify record);
}