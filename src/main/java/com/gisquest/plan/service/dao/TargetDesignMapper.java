package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.targetDesign.TargetDesign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TargetDesignMapper {
     List<TargetDesign> getTargetDesignDataByTargetDesignParentId(@Param("targetDesignParentId") String targetDesignParentId);
    int deleteByPrimaryKey(String id);

    int insert(TargetDesign record);

    int insertSelective(TargetDesign record);

    TargetDesign selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TargetDesign record);

    int updateByPrimaryKey(TargetDesign record);
}