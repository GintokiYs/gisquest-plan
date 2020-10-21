package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.targetDesign.TargetDesign;
import com.gisquest.plan.service.model.targetDesign.TargetDesignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TargetDesignMapper {
    int countByExample(TargetDesignExample example);

    int deleteByExample(TargetDesignExample example);

    int insert(TargetDesign record);

    int insertSelective(TargetDesign record);

    List<TargetDesign> selectByExample(TargetDesignExample example);

    int updateByExampleSelective(@Param("record") TargetDesign record, @Param("example") TargetDesignExample example);

    int updateByExample(@Param("record") TargetDesign record, @Param("example") TargetDesignExample example);
}