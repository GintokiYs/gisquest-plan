package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.targetDesign.TargetDesign;

public interface TargetDesignMapper {
    int insert(TargetDesign record);

    int insertSelective(TargetDesign record);
}