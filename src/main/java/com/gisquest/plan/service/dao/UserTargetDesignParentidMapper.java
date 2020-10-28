package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.UserTargetDesignParentid.UserTargetDesignParentid;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserTargetDesignParentidMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserTargetDesignParentid record);

    int insertSelective(UserTargetDesignParentid record);

    UserTargetDesignParentid selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserTargetDesignParentid record);

    int updateByPrimaryKey(UserTargetDesignParentid record);

    List<UserTargetDesignParentid> selectByUserid(@Param("userId") String userid);

    UserTargetDesignParentid selectByTargetDesignParentId(@Param("targetDesignParentId")String targetDesignParentId);

    int deleteByTargetDesignParentid(@Param("targetDesignParentId")String id);
}