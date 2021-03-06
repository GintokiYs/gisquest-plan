package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.TargetDesignParent.TargetDesignParent;
import com.gisquest.plan.service.model.targetDesign.TargetDesign;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetDesignMapper {
     List<TargetDesign> getTargetDesignDataByTargetDesignParentId(@Param("targetDesignParentId") String targetDesignParentId);
    int deleteByPrimaryKey(String id);

    int insert(TargetDesign record);

    int insertSelective(TargetDesign record);

    TargetDesign selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TargetDesign record);

    int updateByPrimaryKey(TargetDesign record);

    int deleteByTargetDesignParentid(@Param("targetDesignParentId")String id);

    List<TargetDesign> seletByParentId(@Param("targetDesignId") String targetDesignId);
}
