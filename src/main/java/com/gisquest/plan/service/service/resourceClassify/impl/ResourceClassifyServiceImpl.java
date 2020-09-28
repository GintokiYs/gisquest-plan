package com.gisquest.plan.service.service.resourceClassify.impl;

import com.gisquest.plan.service.dao.ResourceClassifyMapper;
import com.gisquest.plan.service.service.resourceClassify.ResourceClassifyService;
import com.gisquest.plan.service.vo.quata.QuataVo;
import com.gisquest.plan.service.vo.quata.TargetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author honght
 * @date 2020/9/28 9:28
 */
@Service
public class ResourceClassifyServiceImpl implements ResourceClassifyService {

    @Autowired
    ResourceClassifyMapper resourceClassifyMapper;

    @Override
    public List<QuataVo> getAllTopic() {
        return resourceClassifyMapper.getAllTopic();
    }
}
