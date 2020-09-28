package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.targetClassify.targetClassify;
import org.springframework.stereotype.Repository;

@Repository
public interface targetClassifyMapper {
    int insert(targetClassify record);

    int insertSelective(targetClassify record);
}
