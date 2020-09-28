package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.targetClassify.TargetClassify;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetClassifyMapper {
    TargetClassify getTable(String quataId);
}
