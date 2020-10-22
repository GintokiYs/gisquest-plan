package com.gisquest.plan.service.utils;

import com.gisquest.plan.service.model.TargetDesignParent.TargetDesignParent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: TargetDesignParentTreeUtil
 * @ProjectName gisquest-plan
 * @Description: TODO
 * @Date 2020/10/22 9:35
 * @Version 2.0
 */
public class TargetDesignParentTreeUtil {
    public static List<Map<String, Object>> getTree(List<TargetDesignParent> list) {
        List<Map<String, Object>> firstNodeList = new ArrayList<Map<String, Object>>();
        List<TargetDesignParent> removeList = new ArrayList<TargetDesignParent>();
        for (TargetDesignParent dt:list) {
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
        firstNodeList = getChildTree(firstNodeList, list);
        return firstNodeList;
    }
    public static List<Map<String, Object>> getChildTree (List<Map<String, Object>> nodeList, List<TargetDesignParent> list) {
        for (Map<String, Object> node:nodeList ) {
            List<Map<String, Object>> nextNodeList = new ArrayList<Map<String, Object>>();
            List<TargetDesignParent> removeList = new ArrayList<TargetDesignParent>();
            for (TargetDesignParent dt:list) {
                if (dt.getParentid().equals(String.valueOf(node.get("id")))) {
                    Map<String, Object> treeMap = new HashMap<String, Object>();
                    treeMap.put("id", dt.getId());
                    treeMap.put("isfile", "2".equals(dt.getExtend1()));
                    treeMap.put("label", dt.getType());
                    treeMap.put("pId", dt.getParentid());
                    treeMap.put("children",new ArrayList<Map<String, Object>>());
                    nextNodeList.add(treeMap);
                    removeList.add(dt);
                }
            }
            list.removeAll(removeList);
            nextNodeList = getChildTree(nextNodeList, list);
            node.put("children",nextNodeList);
        }
        return nodeList;
    }
}
