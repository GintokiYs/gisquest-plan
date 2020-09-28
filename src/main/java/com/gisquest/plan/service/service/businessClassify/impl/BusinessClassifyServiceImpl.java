package com.gisquest.plan.service.service.businessClassify.impl;

import com.gisquest.plan.service.dao.BusinessClassifyMapper;
import com.gisquest.plan.service.dao.SearchMapper;
import com.gisquest.plan.service.dao.TargetClassifyMapper;
import com.gisquest.plan.service.model.targetClassify.TargetClassify;
import com.gisquest.plan.service.service.businessClassify.BusinessClassifyService;
import com.gisquest.plan.service.utils.TransformUtil;
import com.gisquest.plan.service.vo.quata.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author honght
 * @date 2020/9/28 10:33
 */
@Service
public class BusinessClassifyServiceImpl implements BusinessClassifyService {

    @Autowired
    BusinessClassifyMapper businessClassifyMapper;

    @Autowired
    SearchMapper searchMapper;

    @Autowired
    TargetClassifyMapper targetClassifyMapper;

    @Override
    public List<TargetResponse> getTargetById(String resourceParentid) {
        return businessClassifyMapper.getTargetById(resourceParentid);
    }

    @Override
    public List<QuataResponse> getTargetList(QuataSearchResponse quataSearchResponse) {
        List<String> list = quataSearchResponse.getQuataIdList();
        List<QuataResponse> responses = new ArrayList<>();
        if(list.size()>0){
            for(int i=0;i<list.size();i++){

                TargetClassify targetClassify = targetClassifyMapper.getTable(list.get(i));
                String tableName ="t" + TransformUtil.frontCompWithZore(targetClassify.getAlias(), 6);
                quataSearchResponse.setTableName(tableName);
                List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                for(int j=0;j<list1.size();j++){
                    list1.get(j).setTrage(targetClassify.getType());
                }
                responses.addAll(list1);
            }
        }
        return responses;
    }
}
