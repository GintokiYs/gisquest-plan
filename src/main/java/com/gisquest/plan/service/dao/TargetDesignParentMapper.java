package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.TargetDesignParent.TargetDesignParent;

import java.util.List;

public interface TargetDesignParentMapper {
    int deleteByPrimaryKey(String id);

    int insert(TargetDesignParent record);

    int insertSelective(TargetDesignParent record);

    TargetDesignParent selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TargetDesignParent record);

    int updateByPrimaryKey(TargetDesignParent record);

    List<TargetDesignParent> seletAll();
}