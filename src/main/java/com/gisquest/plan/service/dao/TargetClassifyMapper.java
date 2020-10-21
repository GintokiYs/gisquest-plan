package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.targetClassify.TargetClassify;
import org.springframework.stereotype.Repository;


@Repository
public interface TargetClassifyMapper {
    TargetClassify getTable(String quataId);
}
