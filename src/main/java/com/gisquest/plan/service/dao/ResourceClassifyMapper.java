package com.gisquest.plan.service.dao;

import com.gisquest.plan.service.model.resourceClassify.resourceClassify;
import com.gisquest.plan.service.vo.quata.QuataVo;
import com.gisquest.plan.service.vo.quata.TargetResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceClassifyMapper {

    List<QuataVo> getAllTopic();
}
