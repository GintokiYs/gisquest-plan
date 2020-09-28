package com.gisquest.plan.service.service.businessClassify.impl;

import com.gisquest.plan.service.dao.BusinessClassifyMapper;
import com.gisquest.plan.service.dao.SearchMapper;
import com.gisquest.plan.service.dao.TargetClassifyMapper;
import com.gisquest.plan.service.model.targetClassify.TargetClassify;
import com.gisquest.plan.service.service.businessClassify.BusinessClassifyService;
import com.gisquest.plan.service.utils.TransformUtil;
import com.gisquest.plan.service.vo.quata.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> getTargetList(QuataSearchResponse quataSearchResponse) {
        List<String> list = quataSearchResponse.getQuataIdList();
        List<QuataResponse> responses = new ArrayList<>();
        List<QuataResponse> avgList = new ArrayList<>();
        List<QuataResponse> medianList = new ArrayList<>();
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

                List<String> listYear = searchMapper.getTargetYear(tableName);

                if(quataSearchResponse.getAve().equals("true")){
                    for(int k=0;k<listYear.size();k++){
                        float avg = searchMapper.getTargetAvg(tableName, Integer.parseInt(listYear.get(k)));
                        QuataResponse quataResponse = new QuataResponse();
                        quataResponse.setTrage(targetClassify.getType());
                        quataResponse.setTarget(String.valueOf(avg));
                        quataResponse.setYear(Integer.parseInt(listYear.get(k)));
                        avgList.add(quataResponse);

                    }
                }
                if(quataSearchResponse.getMedian().equals("true")){
                    for(int z =0;z<listYear.size();z++){
                        List<QuataResponse> midel =searchMapper.getTargetMedian(tableName, Integer.parseInt(listYear.get(z)));
                        int length = midel.size();
                        if(length%2 == 0){ // 偶数
                            int number = length/2;
                            float number1 = Float.parseFloat(midel.get(number).getTarget());
                            float number2= Float.parseFloat(midel.get(number+1).getTarget());
                            float ave  = (number1+number2)/2;
                            QuataResponse quataResponse = new QuataResponse();
                            quataResponse.setTrage(targetClassify.getType());
                            quataResponse.setTarget(String.valueOf((ave)));
                            quataResponse.setYear(Integer.parseInt(listYear.get(z)));
                            medianList.add(quataResponse);
                        }else {
                            int number = (length+1)/2;
                            QuataResponse quataResponse = new QuataResponse();
                            quataResponse.setTrage(targetClassify.getType());
                            quataResponse.setTarget(midel.get(number).getTarget());
                            quataResponse.setYear(Integer.parseInt(listYear.get(z)));
                            medianList.add(quataResponse);
                        }
                    }
                }



            }
        }

        Map<String, Object> hashMap = new HashMap();
        hashMap.put("tipoc", responses);
        hashMap.put("avg",avgList);
        hashMap.put("median",medianList);
        return hashMap;
    }
}
