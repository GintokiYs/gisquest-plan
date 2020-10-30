package com.gisquest.plan.service.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Title: treeToListUtil
 * @ProjectName plan
 * @Description: TODO
 * @Author dingyf
 * @Date 2020/10/29 11:13
 * @Version 2.0
 */
public class treeToListUtil {

    public static List<List<String>> treeToList(List<Map<String, Object>> result) {
        List<List<String>> list = new ArrayList<>();
        for (Map<String, Object> map : result) {
            //设置当前节点的必要数据
            List<String> listadd = new ArrayList<>();
            String name = map.get("label").toString();
            listadd.add(name);
            list.add(listadd);
            //遍历递归子节点
            List<Map<String, Object>> childrens = (List<Map<String, Object>>) map.get("children");

            if (childrens!= null && childrens.size()>0 ){
                List<List<String>> list1 = treeToList(childrens);
                list.addAll(list1);
            }
        }
        return list;
    }
    /**
     * 把树 转成list
     * @return
     */
    public static List<List<String>> getTreeChangeList(List<Map<String, Object>> result){
        List<List<String>> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        result.forEach(o->{
            list.clear();
            list2.clear();
            List<List<String>> list1 = treeToList2(o, list, list2);

            System.out.println(list1);
        });

        return list;
    }
        public static List<List<String>> treeToList2(Map<String, Object> map, List<List<String>> list1, List<String> list2){
            //遍历递归子节点
            List<Map<String, Object>> childrens = (List<Map<String, Object>>) map.get("children");
            if (childrens.size() == 0 ){
                list1.add(list2);
            }else {
                for (Map<String, Object> children : childrens) {
                    String label = children.get("label").toString();
                    list2.add(label);

                    treeToList2(children, list1, list2);
                }

            }

            return list1;


//        parseTreeToRow(node, data = [], row = []) {
//            if (node.children.length == 0) {
//                data.push(row);
//            } else {
//                node.children.map((item,index)=>{
//                           const obj = { index:item.label, };
//                    this.parseTreeToRow(item, data, [...row, obj]);
//                })
//            }
//            return data;
//        },


//
//        if(list1==null){
//            list1=new ArrayList<List<String>>();
//        }
//        //设置当前节点的必要数据
//        List<String> listadd = new ArrayList<>();
//        String name = map.get("label").toString();
//        listadd.add(name);
//
//        //遍历递归子节点
//        List<Map<String, Object>> childrens = (List<Map<String, Object>>) map.get("children");
//
//        if (childrens.size()>0){
//            for (int i = 0; i < childrens.size(); i++) {
//                Map<String, Object> map1 = childrens.get(i);
//                String childname = map.get("label").toString();
//                listadd.add(childname);
//                list1.add(listadd);
//                treeToList(map1,list1);
//
//            }
//        }
//
//        list1.add(listadd);
    }

    /**
     * 将tree结构数据转成List结构
     *
     * @param node
     * @param list
     * @return
     */
    public static void treeToList(Map<String, Object> node, List<List<String>> list){
        if(list==null){
            list=new ArrayList<List<String>>();
        }
        //设置当前节点的必要数据
        List<String> listadd = new ArrayList<>();
        String name = node.get("label").toString();
        listadd.add(name);

        //遍历递归子节点
        List<Map<String, Object>> childrens = (List<Map<String, Object>>) node.get("children");

        if (childrens.size()>0){
            for (int i = 0; i < childrens.size(); i++) {
                Map<String, Object> map = childrens.get(i);
                String childname = map.get("label").toString();
                listadd.add(childname);
                list.add(listadd);
                treeToList(map,list);

            }
        }

        list.add(listadd);
    }




    public static List<List<String>> treeToListUtil(List<Map<String,Object>> result, List<String> list, List<List<String>> lists ) {
        for (Map<String, Object> map : result) {
            // 通过key获取value
            List<Map<String, Object>> childrens = (List<Map<String, Object>>) map.get("children");
            //获取深度
            List<Integer> is = new ArrayList<>();
            int rows = getRows(childrens,2,is);
            for (int i = 0; i < rows; i++) {
                // 通过key获取value
                String name = map.get("label").toString();
                list.add(name);

                if (childrens.size()>0){
                    treeToListUtil(childrens,list,lists);
                }
            }
            lists.add(list);
        }
        return  lists;
//        for (Map<String, Object> map : result) {
//            // 通过key获取value
//            List<Map<String, Object>> childrens = (List<Map<String, Object>>) map.get("children");
//            // 获取行数
//            List<Integer> is = new ArrayList<>();
//            int rows = getRows(childrens,2,is);
//            // 深度
//            int deep = getRows(childrens,2,is);
//            List<String> list1 = new ArrayList<>();
//            for (int i = 0; i < rows; i++) {
//                // 通过key获取value
//                String name = map.get("label").toString();
//                list1.add(name);
//
//                lists.add(list1);
//            }
//        }
//        return  lists;
    }
    // 获取tree的深度
    public static int deep(List<Map<String,Object>> result,int i,List<Integer> is ) {
        int deep = i;

        for (Map<String, Object> map : result) {
            boolean name = (boolean) map.get("isfile");

            //获取深度
            List<Map<String, Object>> childrens = (List<Map<String, Object>>) map.get("children");
            if (childrens.size()>0){
                deep(childrens,deep,is);
                deep++;
            }
            // 取最大
            is.add(deep);
            deep = 1;
        }
        Integer max = 0;
        if (is.size()==0){
            max = 0;
        }else {
            Collections.max(is);
        }

        return  max;
    }
    // 获取tree的深度
    public static int getRows(List<Map<String,Object>> result,int i,List<Integer> is ) {
        int deep = i;

        for (Map<String, Object> map : result) {
            boolean name = (boolean) map.get("isfile");
            if (name){
                ++deep;
            }
            //获取深度
            List<Map<String, Object>> childrens = (List<Map<String, Object>>) map.get("children");
            if (childrens.size()>0){
                deep(childrens,deep,is);

            }
            // 取最大
            is.add(deep);
        }
        return  is.size();
    }

}
