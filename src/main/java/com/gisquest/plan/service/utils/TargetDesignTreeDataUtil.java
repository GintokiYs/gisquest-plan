package com.gisquest.plan.service.utils;

import com.fasterxml.jackson.databind.ser.std.IterableSerializer;
import com.gisquest.plan.service.model.TargetDesignClumnData.TargetDesignClumnData;
import com.gisquest.plan.service.model.targetDesign.TargetDesign;
import com.gisquest.plan.service.model.targetDesignClumnName.TargetDesignClumnName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Title: TargetDesignTreeUtil
 * @ProjectName gisquest-plan
 * @Description: TODO
 * @Date 2020/10/22 9:35
 * @Version 2.0
 */
public class TargetDesignTreeDataUtil {
    public static List<Map<String, Object>> getTree(List<TargetDesign> list, List<TargetDesignClumnName> clumnNames, List<TargetDesignClumnData> clumnDatas) {
        List<Map<String, Object>> firstNodeList = new ArrayList<Map<String, Object>>();
        List<TargetDesign> removeList = new ArrayList<TargetDesign>();
        for (TargetDesign dt:list) {
            //获取所有一级
            if ("0".equals(dt.getParentid())) {
                Map<String, Object> treeMap = new HashMap<String, Object>();
                treeMap.put("id", dt.getId());
                treeMap.put("isfile", "2".equals(dt.getExtend1()));
                treeMap.put("label", dt.getType());
                treeMap.put("pId", "0");
                treeMap.put("children",new ArrayList<Map<String, Object>>());
                firstNodeList.add(treeMap);
                removeList.add(dt);
            }
        }
        list.removeAll(removeList);
        List<String> clumns = clumnNames.stream().map(item -> item.getType()).collect(Collectors.toList());
        List<String> clumnIds = clumnNames.stream().map(item -> item.getId()).collect(Collectors.toList());

        List<String> ids = clumnNames.stream().map(item -> item.getId()).collect(Collectors.toList());
        firstNodeList = getChildTree(ids,clumns,clumnIds,firstNodeList, list,clumnNames,clumnDatas);
        return firstNodeList;
    }
    public static List<Map<String, Object>> getChildTree(List<String> ids,List<String> clumns,List<String> clumnIds,List<Map<String, Object>> nodeList, List<TargetDesign> list, List<TargetDesignClumnName> clumnNames, List<TargetDesignClumnData> clumnDatas) {
        for (Map<String, Object> node:nodeList ) {
            List<Map<String, Object>> nextNodeList = new ArrayList<Map<String, Object>>();
            List<TargetDesign> removeList = new ArrayList<TargetDesign>();
            for (TargetDesign dt:list) {
                if (dt.getParentid().equals(String.valueOf(node.get("id")))) {
                    Map<String, Object> treeMap = new HashMap<String, Object>();
                    treeMap.put("id", dt.getId());
                    treeMap.put("isfile", "2".equals(dt.getExtend1()));
                    treeMap.put("label", dt.getType());
                    treeMap.put("pId", dt.getParentid());
                    treeMap.put("clumnIds", clumnIds);
                    treeMap.put("clumnName", clumns);
                    List<String> clumndas = getClumndas(ids, dt.getId(), clumnDatas);
                    treeMap.put("clumnData", clumndas);
                    treeMap.put("children",new ArrayList<Map<String, Object>>());
                    nextNodeList.add(treeMap);
                    removeList.add(dt);
                }
            }
            list.removeAll(removeList);
            nextNodeList = getChildTree(ids,clumns,clumnIds,nextNodeList, list, clumnNames, clumnDatas);
            node.put("children",nextNodeList);
        }
        return nodeList;
    }
    public static List<String> getClumndas(List<String>  ids,String id ,List<TargetDesignClumnData> clumnDatas) {
        List<String> list = new ArrayList<>(ids.size());
        for (int i = 0; i < ids.size(); i++) {
            int finalI = i;
            // 先筛选出来对应列的数据
            List<TargetDesignClumnData> collect = clumnDatas.stream().filter(item -> ids.get(finalI).equals(item.getClumnNameId())).collect(Collectors.toList());
            if (null == collect || collect.size()==0){
                list.add("/");
            }else{
                //筛选数据
                List<TargetDesignClumnData> collect1 = collect.stream().filter(item -> id.equals(item.getTargetDesignId())).collect(Collectors.toList());
                if (null == collect1 || collect1.size()==0){
                    list.add("/");
                }else{
                    list.add(collect1.get(0).getClumnData());
                }
            }

        }

        return list;
    }




    public static List<List<String>> getListTree(List<TargetDesign> list, List<TargetDesignClumnName> clumnNames, List<TargetDesignClumnData> clumnDatas) {
        List<Map<String, Object>> firstNodeList = new ArrayList<Map<String, Object>>();
        List<TargetDesign> removeList = new ArrayList<TargetDesign>();
        for (TargetDesign dt:list) {
            //获取所有一级
            if ("0".equals(dt.getParentid())) {
                Map<String, Object> treeMap = new HashMap<String, Object>();
                treeMap.put("id", dt.getId());
                treeMap.put("isfile", "2".equals(dt.getExtend1()));
                treeMap.put("label", dt.getType());
                treeMap.put("pId", "0");
                treeMap.put("children",new ArrayList<Map<String, Object>>());
                firstNodeList.add(treeMap);
                removeList.add(dt);
            }
        }
        list.removeAll(removeList);
        List<String> clumns = clumnNames.stream().map(item -> item.getType()).collect(Collectors.toList());
        List<String> clumnIds = clumnNames.stream().map(item -> item.getId()).collect(Collectors.toList());

        List<String> ids = clumnNames.stream().map(item -> item.getId()).collect(Collectors.toList());
        firstNodeList = getlistChildTree(ids,clumns,clumnIds,firstNodeList, list,clumnNames,clumnDatas);
        return null;
    }
    public static List<Map<String, Object>> getlistChildTree(List<String> ids,List<String> clumns,List<String> clumnIds,List<Map<String, Object>> nodeList, List<TargetDesign> list, List<TargetDesignClumnName> clumnNames, List<TargetDesignClumnData> clumnDatas) {
        for (Map<String, Object> node:nodeList ) {
            List<Map<String, Object>> nextNodeList = new ArrayList<Map<String, Object>>();
            List<List<String>> nextNodeListString = new ArrayList<>();
            List<List<String>> list1 = new ArrayList<>();
            List<TargetDesign> removeList = new ArrayList<TargetDesign>();
            for (TargetDesign dt:list) {
                if (dt.getParentid().equals(String.valueOf(node.get("id")))) {
                    Map<String, Object> treeMap = new HashMap<String, Object>();
                    treeMap.put("id", dt.getId());
                    treeMap.put("isfile", "2".equals(dt.getExtend1()));
                    treeMap.put("label", dt.getType());
                    treeMap.put("pId", dt.getParentid());
                    treeMap.put("clumnIds", clumnIds);
                    treeMap.put("clumnName", clumns);
                    List<String> clumndas = getClumndas(ids, dt.getId(), clumnDatas);
                    treeMap.put("clumnData", clumndas);
                    treeMap.put("children",new ArrayList<Map<String, Object>>());
                    nextNodeList.add(treeMap);
                    removeList.add(dt);
                }
            }
            list.removeAll(removeList);
            nextNodeList = getChildTree(ids,clumns,clumnIds,nextNodeList, list, clumnNames, clumnDatas);
            node.put("children",nextNodeList);
        }
        return nodeList;
    }




}
