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
        List<String> year = quataSearchResponse.getYearList();
        List<String> areaList = quataSearchResponse.getAreaList();
        List<String> collectList = quataSearchResponse.getCollectList();
        List<String> dataSourcetList = quataSearchResponse.getDataSourcetList();
        List<QuataResponse> responses = new ArrayList<>();
        List<QuataResponse> avgList = new ArrayList<>();
        List<QuataResponse> medianList = new ArrayList<>();
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                TargetClassify targetClassify = targetClassifyMapper.getTable(list.get(i));
                String tableName ="t" + TransformUtil.frontCompWithZore(targetClassify.getAlias(), 6);
                if(year.size()==0&&areaList.size()==0&&collectList.size()==0&&dataSourcetList.size()==0){
                    quataSearchResponse.setTableName(tableName);
                    List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                    for(int j=0;j<list1.size();j++){
                        list1.get(j).setTrage(targetClassify.getType());
                    }
                    responses.addAll(list1);
                }else if(year.size()>0&&areaList.size()==0&&collectList.size()==0&&dataSourcetList.size()==0){
                    for(int m=0;m<year.size();m++){
                        quataSearchResponse.setTableName(tableName);
                        quataSearchResponse.setYear(Integer.parseInt(year.get(m)));
                        List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                        for(int j=0;j<list1.size();j++){
                            list1.get(j).setTrage(targetClassify.getType());
                        }
                        responses.addAll(list1);
                    }
                }else if(areaList.size()>0&&year.size()==0&&collectList.size()==0&&dataSourcetList.size()==0){
                    for(int m=0;m<areaList.size();m++){
                        quataSearchResponse.setTableName(tableName);
                        quataSearchResponse.setArea(areaList.get(m));
                        List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                        for(int j=0;j<list1.size();j++){
                            list1.get(j).setTrage(targetClassify.getType());
                        }
                        responses.addAll(list1);
                    }
                }else if(collectList.size()>0&&areaList.size()==0&&year.size()==0&&dataSourcetList.size()==0){
                    for(int m=0;m<collectList.size();m++){
                        quataSearchResponse.setTableName(tableName);
                        quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(m)));
                        List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                        for(int j=0;j<list1.size();j++){
                            list1.get(j).setTrage(targetClassify.getType());
                        }
                        responses.addAll(list1);
                    }
                }else if(collectList.size()==0&&areaList.size()==0&&year.size()==0&&dataSourcetList.size()>=0){
                    for(int m=0;m<dataSourcetList.size();m++){
                        quataSearchResponse.setTableName(tableName);
                        quataSearchResponse.setDataSource(dataSourcetList.get(m));
                        List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                        for(int j=0;j<list1.size();j++){
                            list1.get(j).setTrage(targetClassify.getType());
                        }
                        responses.addAll(list1);
                    }
                }else if(year.size()>0&&areaList.size()>0&&collectList.size()==0&&dataSourcetList.size()==0){
                    for(int m=0;m<areaList.size();m++){
                        for( int n=0;n<year.size();n++){
                            quataSearchResponse.setTableName(tableName);
                            quataSearchResponse.setArea(areaList.get(m));
                            quataSearchResponse.setYear(Integer.parseInt(year.get(n)));
                            List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                            for(int j=0;j<list1.size();j++){
                                list1.get(j).setTrage(targetClassify.getType());
                            }
                            responses.addAll(list1);
                        }
                    }
                }else if(year.size()>0&&areaList.size()==0&&collectList.size()>0&&dataSourcetList.size()==0){
                    for(int m=0;m<collectList.size();m++){
                        for( int n=0;n<year.size();n++){
                            quataSearchResponse.setTableName(tableName);
                            quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(m)));
                            quataSearchResponse.setYear(Integer.parseInt(year.get(n)));
                            List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                            for(int j=0;j<list1.size();j++){
                                list1.get(j).setTrage(targetClassify.getType());
                            }
                            responses.addAll(list1);
                        }
                    }
                }else if(year.size()==0&&areaList.size()>0&&collectList.size()>0&&dataSourcetList.size()==0){
                    for(int m=0;m<areaList.size();m++){
                        for( int n=0;n<collectList.size();n++){
                            quataSearchResponse.setTableName(tableName);
                            quataSearchResponse.setArea(areaList.get(m));
                            quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(n)));
                            List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                            for(int j=0;j<list1.size();j++){
                                list1.get(j).setTrage(targetClassify.getType());
                            }
                            responses.addAll(list1);
                        }
                    }
                }else if(year.size()>0&&areaList.size()==0&&collectList.size()==0&&dataSourcetList.size()>0){
                    for(int m=0;m<dataSourcetList.size();m++){
                        for( int n=0;n<year.size();n++){
                            quataSearchResponse.setTableName(tableName);
                            quataSearchResponse.setDataSource(dataSourcetList.get(m));
                            quataSearchResponse.setYear(Integer.parseInt(year.get(n)));
                            List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                            for(int j=0;j<list1.size();j++){
                                list1.get(j).setTrage(targetClassify.getType());
                            }
                            responses.addAll(list1);
                        }
                    }
                }else if(year.size()==0&&areaList.size()>0&&collectList.size()==0&&dataSourcetList.size()>0){
                    for(int m=0;m<dataSourcetList.size();m++){
                        for( int n=0;n<areaList.size();n++){
                            quataSearchResponse.setTableName(tableName);
                            quataSearchResponse.setDataSource(dataSourcetList.get(m));
                            quataSearchResponse.setArea(areaList.get(n));
                            List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                            for(int j=0;j<list1.size();j++){
                                list1.get(j).setTrage(targetClassify.getType());
                            }
                            responses.addAll(list1);
                        }
                    }
                }else if(year.size()==0&&areaList.size()==0&&collectList.size()>0&&dataSourcetList.size()>0){
                    for(int m=0;m<dataSourcetList.size();m++){
                        for( int n=0;n<collectList.size();n++){
                            quataSearchResponse.setTableName(tableName);
                            quataSearchResponse.setDataSource(dataSourcetList.get(m));
                            quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(n)));
                            List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                            for(int j=0;j<list1.size();j++){
                                list1.get(j).setTrage(targetClassify.getType());
                            }
                            responses.addAll(list1);
                        }
                    }
                }else if(year.size()>0&&areaList.size()>0&&collectList.size()>0&&dataSourcetList.size()==0){
                    for(int m=0;m<areaList.size();m++){
                        for(int n=0;n<collectList.size();n++){
                            for(int q=0;q<year.size();q++){
                                quataSearchResponse.setTableName(tableName);
                                quataSearchResponse.setArea(areaList.get(m));
                                quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(n)));
                                quataSearchResponse.setYear(Integer.parseInt(year.get(q)));
                                List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                                for(int j=0;j<list1.size();j++){
                                    list1.get(j).setTrage(targetClassify.getType());
                                }
                                responses.addAll(list1);
                            }

                        }
                    }
                }else if(year.size()==0&&areaList.size()>0&&collectList.size()>0&&dataSourcetList.size()>0) {
                    for (int m = 0; m < areaList.size(); m++) {
                        for (int n = 0; n < collectList.size(); n++) {
                            for (int q = 0; q < dataSourcetList.size(); q++) {
                                quataSearchResponse.setTableName(tableName);
                                quataSearchResponse.setArea(areaList.get(m));
                                quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(n)));
                                quataSearchResponse.setDataSource(dataSourcetList.get(q));
                                List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                                for (int j = 0; j < list1.size(); j++) {
                                    list1.get(j).setTrage(targetClassify.getType());
                                }
                                responses.addAll(list1);
                            }

                        }
                    }
                }else if(year.size()>0&&areaList.size()==0&&collectList.size()>0&&dataSourcetList.size()>0) {
                    for (int m = 0; m < year.size(); m++) {
                        for (int n = 0; n < collectList.size(); n++) {
                            for (int q = 0; q < dataSourcetList.size(); q++) {
                                quataSearchResponse.setTableName(tableName);
                                quataSearchResponse.setYear(Integer.parseInt(year.get(m)));
                                quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(n)));
                                quataSearchResponse.setDataSource(dataSourcetList.get(q));
                                List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                                for (int j = 0; j < list1.size(); j++) {
                                    list1.get(j).setTrage(targetClassify.getType());
                                }
                                responses.addAll(list1);
                            }

                        }
                    }
                }else if(year.size()>0&&areaList.size()>0&&collectList.size()==0&&dataSourcetList.size()>0) {
                    for (int m = 0; m < year.size(); m++) {
                        for (int n = 0; n < areaList.size(); n++) {
                            for (int q = 0; q < dataSourcetList.size(); q++) {
                                quataSearchResponse.setTableName(tableName);
                                quataSearchResponse.setYear(Integer.parseInt(year.get(m)));
                                quataSearchResponse.setArea(areaList.get(n));
                                quataSearchResponse.setDataSource(dataSourcetList.get(q));
                                List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                                for (int j = 0; j < list1.size(); j++) {
                                    list1.get(j).setTrage(targetClassify.getType());
                                }
                                responses.addAll(list1);
                            }

                        }
                    }
                }else if(year.size()>0&&areaList.size()>0&&collectList.size()>0&&dataSourcetList.size()>0) {
                    for (int m = 0; m < year.size(); m++) {
                        for (int n = 0; n < areaList.size(); n++) {
                            for (int q = 0; q < dataSourcetList.size(); q++) {
                                for(int p=0;p<collectList.size();p++){
                                    quataSearchResponse.setTableName(tableName);
                                    quataSearchResponse.setYear(Integer.parseInt(year.get(m)));
                                    quataSearchResponse.setArea(areaList.get(n));
                                    quataSearchResponse.setDataSource(dataSourcetList.get(q));
                                    quataSearchResponse.setCollectYear(Integer.parseInt(collectList.get(p)));
                                    List<QuataResponse> list1 = searchMapper.getTargetList(quataSearchResponse);
                                    for (int j = 0; j < list1.size(); j++) {
                                        list1.get(j).setTrage(targetClassify.getType());
                                    }
                                    responses.addAll(list1);
                                }

                            }

                        }
                    }
                }


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
        hashMap.put("topic", responses);
        hashMap.put("avg",avgList);
        hashMap.put("median",medianList);
        return hashMap;
    }
}
