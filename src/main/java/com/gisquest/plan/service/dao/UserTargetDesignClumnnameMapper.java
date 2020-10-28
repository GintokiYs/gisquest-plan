package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.UserTargetDesignClumnname.UserTargetDesignClumnname;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserTargetDesignClumnnameMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserTargetDesignClumnname record);

    int insertSelective(UserTargetDesignClumnname record);

    UserTargetDesignClumnname selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserTargetDesignClumnname record);

    int updateByPrimaryKey(UserTargetDesignClumnname record);

    List<UserTargetDesignClumnname> selectByUseridAndTarDesParId(@Param("userId") String userId,@Param("tarPid") String tarPid);

    int deleteByTargetDesignParentid(@Param("targetDesignParentId")String id);
}