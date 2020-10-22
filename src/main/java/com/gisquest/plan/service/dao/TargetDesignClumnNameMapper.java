package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.targetDesignClumnName.TargetDesignClumnName;

import java.util.List;

public interface TargetDesignClumnNameMapper {
    int deleteByPrimaryKey(String id);

    int insert(TargetDesignClumnName record);

    int insertSelective(TargetDesignClumnName record);

    TargetDesignClumnName selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TargetDesignClumnName record);

    int updateByPrimaryKey(TargetDesignClumnName record);

    List<TargetDesignClumnName> selectAll();
}