package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.TargetDesignClumnData.TargetDesignClumnData;
import com.gisquest.plan.service.model.targetDesignClumnName.TargetDesignClumnName;

import java.util.List;

public interface TargetDesignClumnDataMapper {
    int deleteByPrimaryKey(String id);

    int insert(TargetDesignClumnData record);

    int insertSelective(TargetDesignClumnData record);

    TargetDesignClumnData selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TargetDesignClumnData record);

    int updateByPrimaryKey(TargetDesignClumnData record);

    List<TargetDesignClumnData> selectAll();
}