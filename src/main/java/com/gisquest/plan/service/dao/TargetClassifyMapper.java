package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.targetClassify.TargetClassify;
import com.gisquest.plan.service.vo.quata.targetClassifyResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TargetClassifyMapper {
    TargetClassify getTable(String quataId);
    List<targetClassifyResponse> getTargetDesignAddIndicatorParentTree();

    List<targetClassifyResponse> getTargetDesignAddIndicatorParentTree1();
    List<targetClassifyResponse> getTargetDesignAddIndicatorParentTree2();
}
