package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.targetDesign.TargetDesign;

public interface TargetDesignMapper {
    int deleteByPrimaryKey(String id);

    int insert(TargetDesign record);

    int insertSelective(TargetDesign record);

    TargetDesign selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TargetDesign record);

    int updateByPrimaryKey(TargetDesign record);
}