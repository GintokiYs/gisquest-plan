package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.TargetDesignClumnData.TargetDesignClumnData;
import com.gisquest.plan.service.model.targetDesignClumnName.TargetDesignClumnName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TargetDesignClumnDataMapper {
    int deleteByPrimaryKey(String id);

    int insert(TargetDesignClumnData record);

    int insertSelective(TargetDesignClumnData record);

    TargetDesignClumnData selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TargetDesignClumnData record);

    int updateByPrimaryKey(TargetDesignClumnData record);

    List<TargetDesignClumnData> selectAll();

    List<TargetDesignClumnData> selectBytargetDesignId(@Param("designId")  String id);

    int deleteByTargetDesignId(@Param("designId") String id);
}