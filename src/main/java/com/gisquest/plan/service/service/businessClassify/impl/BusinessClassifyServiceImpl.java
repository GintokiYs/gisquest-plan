package com.gisquest.plan.service.service.businessClassify.impl;
import com.google.common.collect.Lists;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.gisquest.plan.service.dao.*;
import com.gisquest.plan.service.model.District.District;
import com.gisquest.plan.service.model.TargetDesignClumnData.TargetDesignClumnData;
import com.gisquest.plan.service.model.TargetDesignParent.TargetDesignParent;
import com.gisquest.plan.service.model.UserTargetDesignClumnname.UserTargetDesignClumnname;
import com.gisquest.plan.service.model.UserTargetDesignParentid.UserTargetDesignParentid;
import com.gisquest.plan.service.model.tag.Tag;
import com.gisquest.plan.service.model.targetClassify.TargetClassify;
import com.gisquest.plan.service.model.targetDesign.TargetDesign;
import com.gisquest.plan.service.model.targetDesignClumnName.TargetDesignClumnName;
import com.gisquest.plan.service.service.businessClassify.BusinessClassifyService;
import com.gisquest.plan.service.utils.*;
import com.gisquest.plan.service.vo.ResponseResult;
import com.gisquest.plan.service.vo.quata.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    DistrictMapper districtMapper;
    @Autowired
    TargetDesignParentMapper targetDesignParentMapper;
    @Autowired
    TargetDesignMapper targetDesignMapper;
    @Autowired
    TagMapper tagMapper;
    @Autowired
    TargetClassifyMapper targetClassifyMapper;
    @Autowired
    TargetDesignClumnDataMapper targetDesignClumnDataMapper;
    @Autowired
    TargetDesignClumnNameMapper targetDesignClumnNameMapper;
    @Autowired
    UserTargetDesignParentidMapper userTargetDesignParentidMapper;
    @Autowired
    UserTargetDesignClumnnameMapper userTargetDesignClumnnameMapper;

    @Override
    public List<TargetResponse> getTargetById(String resourceParentid) {
        return businessClassifyMapper.getTargetById(resourceParentid);
    }

    @Override
    public Map<String, Object> getTargetList(QuataSearchResponse quataSearchResponse) {

        List<QuataResponse> responses = new ArrayList<>();
        List<QuataResponse> avgList = new ArrayList<>();
        List<QuataResponse> medianList = new ArrayList<>();

        TargetClassify targetClassify = targetClassifyMapper.getTable(quataSearchResponse.getQuataId());

        if(quataSearchResponse.getEqually().equals("true")){ //同源
            if(!StringUtils.isEmpty(quataSearchResponse.getAreaSoure())){
                String tableName = "t" + TransformUtil.frontCompWithZore(targetClassify.getAlias(), 6);
                quataSearchResponse.setTableName(tableName);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < quataSearchResponse.getYearList().size(); j++) {
                    if (j == quataSearchResponse.getYearList().size()-1){
                        sb.append(quataSearchResponse.getYearList().get(j));
                    }else {
                        sb.append(quataSearchResponse.getYearList().get(j));
                        sb.append(",");
                    }
                }
                StringBuilder sb1 = new StringBuilder();
                for (int k = 0; k < quataSearchResponse.getAreaList().size(); k++) {
                    if (k == quataSearchResponse.getAreaList().size()-1){
                        sb1.append(quataSearchResponse.getAreaList().get(k));
                    }else {
                        sb1.append(quataSearchResponse.getAreaList().get(k));
                        sb1.append(",");
                    }
                }
                quataSearchResponse.setYearString(sb.toString());
                quataSearchResponse.setAreaString(sb1.toString());


                List<QuataResponse> list1= searchMapper.getTargetList(quataSearchResponse);
                responses.addAll(list1);


                List<String> listYear = searchMapper.getTargetYear(tableName);

                if (quataSearchResponse.getAve().equals("true")) {
                    for (int k = 0; k < listYear.size(); k++) {
                        float avg = searchMapper.getTargetAvg(tableName, Integer.parseInt(listYear.get(k)));
                        QuataResponse quataResponse = new QuataResponse();
                        quataResponse.setTrage(targetClassify.getType());
                        quataResponse.setTarget(String.valueOf(avg));
                        quataResponse.setYear(Integer.parseInt(listYear.get(k)));
                        avgList.add(quataResponse);

                    }
                }
                if (quataSearchResponse.getMedian().equals("true")) {
                    for (int z = 0; z < listYear.size(); z++) {
                        List<QuataResponse> midel = searchMapper.getTargetMedian(tableName, Integer.parseInt(listYear.get(z)));
                        int length = midel.size();
                        if (length % 2 == 0) { // 偶数
                            int number = length / 2;
                            float number1 = Float.parseFloat(midel.get(number).getTarget());
                            float number2 = Float.parseFloat(midel.get(number + 1).getTarget());
                            float ave = (number1 + number2) / 2;
                            QuataResponse quataResponse = new QuataResponse();
                            quataResponse.setTrage(targetClassify.getType());
                            quataResponse.setTarget(String.valueOf((ave)));
                            quataResponse.setYear(Integer.parseInt(listYear.get(z)));
                            medianList.add(quataResponse);
                        } else {
                            int number = (length + 1) / 2;
                            QuataResponse quataResponse = new QuataResponse();
                            quataResponse.setTrage(targetClassify.getType());
                            quataResponse.setTarget(midel.get(number).getTarget());
                            quataResponse.setYear(Integer.parseInt(listYear.get(z)));
                            medianList.add(quataResponse);
                        }
                    }
                }

            }else {  //同源页面初始化
                String tableName = "t" + TransformUtil.frontCompWithZore(targetClassify.getAlias(), 6);
                quataSearchResponse.setTableName(tableName);
                quataSearchResponse.setAreaSoure("0");
                List<QuataResponse> list1= searchMapper.getTargetList(quataSearchResponse);
                if(list1.size() ==0){
                    quataSearchResponse.setAreaSoure("1");
                    list1 = searchMapper.getTargetList(quataSearchResponse);
                    if(list1.size() ==0){
                        quataSearchResponse.setAreaSoure("2");
                        list1 = searchMapper.getTargetList(quataSearchResponse);
                        if(list1.size() ==0){
                            quataSearchResponse.setAreaSoure("3");
                            list1 = searchMapper.getTargetList(quataSearchResponse);
                        }
                    }

                }
                responses.addAll(list1);
            }
        }else {
            String tableName = "t" + TransformUtil.frontCompWithZore(targetClassify.getAlias(), 6);
            quataSearchResponse.setTableName(tableName);
            quataSearchResponse.setAreaSoure("0");
            List<QuataResponse> list1= searchMapper.getTargetList(quataSearchResponse);
            if(list1.size() ==0){
                quataSearchResponse.setAreaSoure("1");
                list1 = searchMapper.getTargetList(quataSearchResponse);
                if(list1.size() ==0){
                    quataSearchResponse.setAreaSoure("2");
                    list1 = searchMapper.getTargetList(quataSearchResponse);
                    if(list1.size() ==0){
                        quataSearchResponse.setAreaSoure("3");
                        list1 = searchMapper.getTargetList(quataSearchResponse);
                    }
                }

            }
            responses.addAll(list1);
        }


        Map<String, Object> hashMap = new HashMap();
        hashMap.put("topic", responses);
        hashMap.put("avg", avgList);
        hashMap.put("median", medianList);
        hashMap.put("source", quataSearchResponse.getAreaSoure());
        return hashMap;
    }

    @Override
    public Map<String, Object> getTargetListBySource(QuataSearchResponse quataSearchResponse) {
        return null;
    }

    @Override
    public ResponseResult downloadTargetList(QuataSearchResponse quataSearchResponse) {
        // 获得request对象,response对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        Map<String, Object> result = getTargetList(quataSearchResponse);
        List<QuataResponse> responses = (List<QuataResponse>) result.get("topic");
        List<QuataResponse> avgList = (List<QuataResponse>) result.get("avg");
        List<QuataResponse> medianList = (List<QuataResponse>) result.get("median");
        responses.addAll(avgList);
        responses.addAll(medianList);
        List<SimpleQuataResponse> collect = responses.stream().map((p) -> {
            return new SimpleQuataResponse(p.getTrage(), p.getArea(), p.getYear(), p.getSource(), p.getTarget());
        }).collect(Collectors.toList());
        // 通过工具类创建writer，创建xlsx格式
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("trage", "指标名");
        writer.addHeaderAlias("area", "区域");
        writer.addHeaderAlias("year", "年份");
        writer.addHeaderAlias("source", "数据来源");
//        writer.addHeaderAlias("sourceYear", "数据收集年份");
        writer.addHeaderAlias("target", "指标量");
        // 一次性写出内容
        writer.write(collect, true);
        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        ServletOutputStream out = null;
        String fileName = collect.get(0).getTrage() + ".xlsx";
        System.out.println(fileName);
        try {
            //todo 下载文档存在乱码问题，待解决
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "ISO-8859-1"));
            out = response.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.flush(out, true);
        // 关闭writer，释放内存
        writer.close();
        //关闭输出Servlet流
        IoUtil.close(out);
        return ResponseResult.ok();
    }
    /**
     * @Author
     * @Description //指标表设计区域数据获取
     * @Date 2020/10/21 15:43
     * @Param []
     * @return java.util.List<com.gisquest.plan.service.model.District.District>
     **/
    @Override
    public List<District> getAreaList() {
        return districtMapper.selectAll();
    }
    /**
     * @Author
     * @Description //指标表数据树形结构
     * @Date 2020/10/21 16:21
     * @Param []
     * @return java.util.List<com.gisquest.plan.service.model.TargetDesignParent.TargetDesignParent>
     **/
    @Override
    public List<Map<String, Object>>  getTargetDesignParentTree() {
        // FIXME 当前登录用户id
        // 获取当前登录人员id
        String userid = "2583";
        List<UserTargetDesignParentid> userTargetDesignParentidList = userTargetDesignParentidMapper.selectByUserid(userid);
        List<String> collect = userTargetDesignParentidList.stream().map(item -> item.getTargetDesignParentid()).collect(Collectors.toList());
        List<TargetDesignParent> list = targetDesignParentMapper.seletAll(collect);
        // 使用递归查询
        List<Map<String, Object>> tree = TargetDesignParentTreeUtil.getTree(list);

        return tree;
    }




    /**
     * @Author dingyf
     * @Description //添加表设计器目录或者是设计
     * @Date 2020/10/21 18:50
     * @Param [quataSearchResponse]
     * @return int
     **/
    @Override
    public int addTargetDesignParentTree(QuataSearchResponse quataSearchResponse) {
        if (Strings.isNotBlank(quataSearchResponse.getTargetDesignParentId())){
            //编辑
            TargetDesignParent targetDesignParent = targetDesignParentMapper.selectByPrimaryKey(quataSearchResponse.getTargetDesignParentId());
            targetDesignParent.setType(quataSearchResponse.getName());
            return targetDesignParentMapper.updateByPrimaryKey(targetDesignParent);

        }
        TargetDesignParent targetDesignParent = new TargetDesignParent();
        String uuid = UUIDUtils.getUUID();
        targetDesignParent.setId(uuid);
        targetDesignParent.setType(quataSearchResponse.getName());
        targetDesignParent.setExtend1(quataSearchResponse.getFileOrDir());
        targetDesignParent.setParentid(quataSearchResponse.getPid());

        UserTargetDesignParentid userTargetDesignParentid = new UserTargetDesignParentid();
        userTargetDesignParentid.setId(UUIDUtils.getUUID());
        // FIXME 当前登录用户id
        userTargetDesignParentid.setUserid("2583");
        userTargetDesignParentid.setTargetDesignParentid(uuid);
        userTargetDesignParentidMapper.insertSelective(userTargetDesignParentid);


        return targetDesignParentMapper.insertSelective(targetDesignParent);
    }
    /**
     * @Author
     * @Description //根据指标表设计id获取属性结构数据
     * @Date 2020/10/22 9:03
     * @Param [TargetDesignParentId]
     * @return java.util.List<com.gisquest.plan.service.model.targetDesign.TargetDesign>
     **/
    @Override
    public List<Map<String, Object>>  getTargetDesignDataByTargetDesignParentId(QuataSearchResponse quataSearchResponse) {
        // 指标表设计父id
        String targetDesignParentId = quataSearchResponse.getTargetDesignParentId();
        // 区域
        String area = quataSearchResponse.getArea();
        // 查询列名
        // FIXME 当前登录用户id
        String userId = "2583";
        List<UserTargetDesignClumnname> clumnNamesList = userTargetDesignClumnnameMapper.selectByUseridAndTarDesParId(userId,targetDesignParentId);
        List<String> collect = clumnNamesList.stream().map(item -> item.getClumnid()).collect(Collectors.toList());
        List<TargetDesignClumnName> clumnNames = null;
        if (collect.size()>0){
            clumnNames = targetDesignClumnNameMapper.selectAll(collect);
        }else{
            clumnNames = new ArrayList<>();
        }

        List<TargetDesignClumnData> clumnDatas = targetDesignClumnDataMapper.selectAll();
        //根据指标表设计父id查询数据
        List<TargetDesign> list = targetDesignMapper.getTargetDesignDataByTargetDesignParentId(targetDesignParentId);

        return TargetDesignTreeDataUtil.getTree(list,clumnNames,clumnDatas);
    }

    @Override
    public int addTargetDesignTree(QuataSearchResponse quataSearchResponse) {
        TargetDesign targetDesign = new TargetDesign();
        targetDesign.setId(UUIDUtils.getUUID());
        targetDesign.setType(quataSearchResponse.getName());
        targetDesign.setExtend1(quataSearchResponse.getFileOrDir());
        targetDesign.setParentid(quataSearchResponse.getPid());
        targetDesign.setTargetDesignParentId(quataSearchResponse.getTargetDesignParentId());
        if (Objects.equals("1", quataSearchResponse.getFileOrDir())){
            //文件夹
            return targetDesignMapper.insertSelective(targetDesign);
        }else{
            //文件 ,从指标直面选择数据添加 target_Design的id  看前端怎么传递 应该是个集合
            List<String> targetClassifyIds = quataSearchResponse.getTargetIds();
            for (String targetClassifyId : targetClassifyIds) {
                targetDesign.setTargetParentid(targetClassifyId);
                targetDesignMapper.insertSelective(targetDesign);
            }
            return 1;

        }
    }
    /**
     * @Description //指标表设计添加列下拉框数据获取
     * @Date 2020/10/23 10:38
     * @Param []
     * @return java.util.Map<java.lang.String,java.util.List<java.lang.Object>>
     **/
    @Override
    public Map<String, Set<Object>> getTargetDesignAddColumnDropDownBoxData() {
        Map<String, Set<Object>> map = new HashMap<>();
        List<Tag>  tagList = tagMapper.selectOrderByKeword();
        // 获取国家省市县
        List<String> collect = tagList.stream().map(item -> item.getAreaType()).collect(Collectors.toList());
        Set<String>  areaTypes = new HashSet<String>(collect);
        Set<Object>  area = new HashSet<Object>(areaTypes.size());
        for (String areaType : areaTypes) {
            //0为国家，1为省，2为市，3为区县
            if ("0".equals(areaType)){
                area.add("国家");
            }else if ("1".equals(areaType)){
                area.add("省");
            }else if ("2".equals(areaType)){
                area.add("市");
            }else if ("3".equals(areaType)){
                area.add("县");
            }
        }
        // 数据来源年份
        List<Integer> collect1 = tagList.stream().map(item -> item.getSourceYear()).collect(Collectors.toList());
        Set<Object>  sourceYears = new HashSet<Object>(collect1);
        // 获取数据采集年份
        List<Integer> collect2 = tagList.stream().map(item -> item.getYear()).collect(Collectors.toList());
        Set<Object>  years = new HashSet<Object>(collect2);
        map.put("dataSources",area);
        map.put("sourceYears",sourceYears);
        map.put("years",years);
        return map;
    }
    /**
     * @Description //添加列 并查询将数据返回
     * @Date 2020/10/23 11:05
     * @Param [quataSearchResponse]
     * @return java.util.List<com.gisquest.plan.service.vo.quata.targetClassifyResponse>
     **/
    @Override
    @Transactional
    public List<targetClassifyResponse> addTargetDesignAddColumn(QuataSearchResponse quataSearchResponse) {

        String addColumnType = quataSearchResponse.getAddColumnType();
        // 采集年份
        int sourceYear = quataSearchResponse.getSourceYear();
        // 年份
        int year = quataSearchResponse.getYear();
        // 获取区域编码
        String areaCode = quataSearchResponse.getAreaCode();
        //0为国家，1为省，2为市，3为区县
        String areaSoure = quataSearchResponse.getAreaSoure();
        if ("国家".equals(areaSoure)){
            areaSoure = "0";
        }else if ("省".equals(areaSoure)){
            areaSoure = "1";
        }else if ("市".equals(areaSoure)){
            areaSoure = "2";
        }else if ("县".equals(areaSoure)){
            areaSoure = "3";
        }
        List<targetClassifyResponse> list = new ArrayList<>();
        // 数据对应的ids
        List<String> targetIds = quataSearchResponse.getTargetIds();
        // 自定义列添加后 直接返回
        String targetDesignParentId = quataSearchResponse.getTargetDesignParentId();
        int max = targetDesignClumnNameMapper.selectMax(targetDesignParentId);
        TargetDesignClumnName targetDesignClumnName = new TargetDesignClumnName();
        String clumnId = UUIDUtils.getUUID();
        targetDesignClumnName.setId(clumnId);
        targetDesignClumnName.setSequence(String.valueOf(max+1));

        targetDesignClumnName.setExtend3(targetDesignParentId);
        // 默认不删
        targetDesignClumnName.setIsdelete("0");
        // 添加用户列关联表

        UserTargetDesignClumnname userTargetDesignClumnname = new UserTargetDesignClumnname();
        userTargetDesignClumnname.setId(UUIDUtils.getUUID());
        // FIXME 当前登录用户id
        userTargetDesignClumnname.setUserid("2583");
        userTargetDesignClumnname.setTarDesParid(targetDesignParentId);
        userTargetDesignClumnname.setClumnid(clumnId);
        userTargetDesignClumnnameMapper.insertSelective(userTargetDesignClumnname);

        // 1:年份列 2:区域列 3:自定义列
        if (Objects.equals("3",addColumnType)){
            // 列名
            targetDesignClumnName.setType(quataSearchResponse.getColumnName());

            // 将数据返回  将数据根据叶子节点组装数据返回为空
            for (String targetId : targetIds) {
                targetClassifyResponse targetClassifyResponse = new targetClassifyResponse();
                targetClassifyResponse.setId(targetId);
                targetClassifyResponse.setColumnId(clumnId);
                targetClassifyResponse.setColumnData("");
                list.add(targetClassifyResponse);
            }
            // 新增列名
            targetDesignClumnNameMapper.insertSelective(targetDesignClumnName);
            return list;
        }else if (Objects.equals("1",addColumnType)){
            // 列名
            targetDesignClumnName.setType(String.valueOf(year));
            // 将数据组装返回
            // 查询数据  查询tag表数据
            Tag tag = new Tag();
            tag.setSourceYear(sourceYear);
            tag.setYear(year);
            // 数据来源
            tag.setAreaType(areaSoure);
            tag.setAreaCode(areaCode);
            List<TagResponse> tags = tagMapper.selectOrderByCondition(tag);
            for (String targetClassifyId : targetIds) {
                targetClassifyResponse targetClassifyResponse = new targetClassifyResponse();
                targetClassifyResponse.setId(targetClassifyId);
                targetClassifyResponse.setColumnId(clumnId);
                List<TagResponse> collect = tags.stream().filter(item -> targetClassifyId.equals(item.getTargetId())).collect(Collectors.toList());
                if (null != collect && collect.size()>0){
                    double target = collect.get(0).getTarget();
                    targetClassifyResponse.setColumnData(String.valueOf(target));
                }else {
                    targetClassifyResponse.setColumnData("");
                }
                list.add(targetClassifyResponse);
            }
        }else{
            // 区域列
            District district = districtMapper.selectByareaCode(areaCode);
            // 列名
            if(Strings.isBlank(quataSearchResponse.getColumnName())){
                // 组装列名  地区加年份
                targetDesignClumnName.setType(district.getName()+ String.valueOf(year));
            }else{
                targetDesignClumnName.setType(quataSearchResponse.getColumnName());
            }
            // 将数据组装返回
            // 查询数据
            Tag tag = new Tag();
            tag.setSourceYear(sourceYear);
            tag.setYear(year);
            // 数据来源
            tag.setAreaType(areaSoure);
            tag.setAreaCode(areaCode);
            List<TagResponse> tags = tagMapper.selectOrderByCondition(tag);
            for (String targetClassifyId : targetIds) {
                targetClassifyResponse targetClassifyResponse = new targetClassifyResponse();
                targetClassifyResponse.setId(targetClassifyId);
                targetClassifyResponse.setColumnId(clumnId);
                List<TagResponse> collect = tags.stream().filter(item -> targetClassifyId.equals(item.getTargetId())).collect(Collectors.toList());
                if (null != collect && collect.size()>0){
                    double target = collect.get(0).getTarget();
                    targetClassifyResponse.setColumnData(String.valueOf(target));
                }else {
                    targetClassifyResponse.setColumnData("");
                }
                list.add(targetClassifyResponse);
            }

        }
        // 新增列名
        targetDesignClumnNameMapper.insertSelective(targetDesignClumnName);
        return list;
    }
    /**
     * @Description //查询指标表设计生成EXCEL并下载
     * @Date 2020/10/24 10:53
     * @Param [quataSearchResponse]
     * @return com.gisquest.plan.service.vo.ResponseResult
     **/
    @Override
    public ResponseResult downloadTargetDesignList(QuataSearchResponse quataSearchResponse) {
        String targetDesignParentId = quataSearchResponse.getTargetDesignParentId();
        TargetDesignParent targetDesignParent = targetDesignParentMapper.selectByPrimaryKey(targetDesignParentId);
        // 获得request对象,response对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        List<Map<String, Object>> result = getTargetDesignDataByTargetDesignParentId(quataSearchResponse);
        // 将数据转换
        List<String> list = new ArrayList<>();
        List<List<String>> lists = new ArrayList<>();
        Map<String, Object> map = new HashMap();

        map.put("id","");
        map.put("isfile",false);
        map.put("pId","");
        map.put("children",result);
        List<List<String>> resultList = treeToListUtil.treeToList2(map,lists,list);







        // 通过工具类创建writer，创建xlsx格式
        // 查询列名
        // FIXME 当前登录用户id
        String userId = "2583";
        List<UserTargetDesignClumnname> clumnNamesList = userTargetDesignClumnnameMapper.selectByUseridAndTarDesParId(userId,targetDesignParentId);
        List<String> collect = clumnNamesList.stream().map(item -> item.getClumnid()).collect(Collectors.toList());
        List<TargetDesignClumnName> targetDesignClumnNames = null;
        if (collect.size()>0){
            targetDesignClumnNames = targetDesignClumnNameMapper.selectAll(collect);
        }else{
            targetDesignClumnNames = new ArrayList<>();
        }
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(targetDesignClumnNames.size()+2, targetDesignParent.getType());
        //excel内容
        List<List<String>> data = new ArrayList<>();
        //标题
        List<String> rowData = new ArrayList();
//        rowData.add("序号");
        rowData.add("领域");
        rowData.add("指标");
        //标题
        for (TargetDesignClumnName targetDesignClumnName : targetDesignClumnNames) {
            rowData.add(targetDesignClumnName.getType());
        }
        writer.writeHeadRow(rowData);

        writer.renameSheet(targetDesignParent.getType());
        // 设置数据
        for (Map<String, Object> targetDesignData : result) {
            /**
             * @Description //写不下去了
             * @Date 2020/10/24 16:11
             * @Param [quataSearchResponse]
             * @return com.gisquest.plan.service.vo.ResponseResult
             **/

            //添加行数据
            List<String> row = new ArrayList<>(rowData.size());

            // 通过key获取值
            String type = targetDesignData.get("label").toString();
            // 是否是文件 true:文件
            Boolean flag = (Boolean) targetDesignData.get("isfile");
            // 子数据
            List<Map<String, Object>> children = (List) targetDesignData.get("children");
            //一级
            row.add(type);
            if (null != children && children.size()>0){
                //二级 这里有合并之类的操作



            }else{
                // 空数据

        }
        }
        writer.write(data);
        // 清空缓存
        response.reset();
        String fileName = targetDesignParent.getType() + ".xlsx";
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            fileName = UrlEncodeChinese.urlEncodeChinese(fileName);
            // 定义浏览器响应表头，顺带定义下载名
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "ISO-8859-1"));
            // 设置response的Header
            // 定义下载的类型，标明是excel文件
           // response.setContentType("application/vnd.ms-excel");
            response.setContentType("application/octet-stream");
            // 这时候把创建好的excel写入到输出流
            writer.flush(output);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ResponseResult.ok();
    }
    /**
     * @Description //指标表设计洗新增指标树形结构
     * @Date 2020/10/26 11:01
     * @Param []
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Override
    public List<Map<String, Object>> getTargetDesignAddIndicatorParentTree() {
        List<targetClassifyResponse> targetDesignAddIndicatorParentTree = targetClassifyMapper.getTargetDesignAddIndicatorParentTree();
        List<targetClassifyResponse> targetDesignAddIndicatorParentTree1 = targetClassifyMapper.getTargetDesignAddIndicatorParentTree1();
        List<targetClassifyResponse> targetDesignAddIndicatorParentTree2 = targetClassifyMapper.getTargetDesignAddIndicatorParentTree2();


        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        for (targetClassifyResponse targetClassifyResponse : targetDesignAddIndicatorParentTree) {
            Map<String, Object> treeMap = new HashMap<String, Object>();
            treeMap.put("id", targetClassifyResponse.getId());
            treeMap.put("label", targetClassifyResponse.getType());
            treeMap.put("pId", "0");
            treeMap.put("children",new ArrayList<Map<String, Object>>());
            listMap.add(treeMap);
            List<targetClassifyResponse> collect1 = targetDesignAddIndicatorParentTree1.stream().filter(item -> item.getParentid().equals(targetClassifyResponse.getId())).collect(Collectors.toList());
            List<Map<String, Object>> listMap1 = new ArrayList<Map<String, Object>>();
            for (com.gisquest.plan.service.vo.quata.targetClassifyResponse classifyResponse : collect1) {
                Map<String, Object> treeMap1 = new HashMap<String, Object>();
                treeMap1.put("id", classifyResponse.getId());
                treeMap1.put("label", classifyResponse.getType());
                treeMap1.put("pId", classifyResponse.getParentid());
                treeMap1.put("children",new ArrayList<Map<String, Object>>());
                listMap1.add(treeMap1);
                List<targetClassifyResponse> collect2 = targetDesignAddIndicatorParentTree2.stream().filter(item -> item.getParentid().equals(classifyResponse.getId())).collect(Collectors.toList());
                List<Map<String, Object>> listMap2 = new ArrayList<Map<String, Object>>();
                for (com.gisquest.plan.service.vo.quata.targetClassifyResponse response : collect2) {
                    Map<String, Object> treeMap2 = new HashMap<String, Object>();
                    treeMap2.put("id", response.getId());
                    treeMap2.put("label", response.getType());
                    treeMap2.put("pId", response.getParentid());
                    treeMap2.put("children",new ArrayList<Map<String, Object>>());
                    listMap2.add(treeMap2);
                }
                treeMap1.put("children",listMap2);
            }
            treeMap.put("children",listMap1);
        }

        return listMap;
    }
    /**
     * @Description //指标表设计保存或者另存为
     * @Date 2020/10/26 13:15
     * @Param [quataSearchResponse]
     * @return int
     **/
    @Override
    @Transactional
    public int addTargetDesignSaveOrSaveAs(QuataSearchResponse quataSearchResponse) {
        try {
            String saveOrSaveAs = quataSearchResponse.getSaveOrSaveAs();
            // 另存为 也要将数据返回给我进行保存

            if (Objects.equals("2",saveOrSaveAs)){
                String targetDesignParentId = quataSearchResponse.getTargetDesignParentId();
                TargetDesignParent targetDesignParent = targetDesignParentMapper.selectByPrimaryKey(targetDesignParentId);
                String uuid = UUIDUtils.getUUID();
                targetDesignParent.setId(uuid);
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                targetDesignParent.setType(targetDesignParent.getType()+formatter.format(date));
                targetDesignParentMapper.insertSelective(targetDesignParent);


                // 另存为
                List<TargetDesign> list = targetDesignMapper.getTargetDesignDataByTargetDesignParentId(targetDesignParentId);
                // 将数据保存
                String uuid1 = UUIDUtils.getUUID();
                String uuid2 = uuid1.substring(0, 8);

                UserTargetDesignParentid userTargetDesignParentid =userTargetDesignParentidMapper.selectByTargetDesignParentId(targetDesignParentId);
                userTargetDesignParentid.setId(UUIDUtils.getUUID());
                // FIXME 当前登录用户id
                String userId1 = "2583";
                userTargetDesignParentid.setUserid(userId1);
                userTargetDesignParentid.setTargetDesignParentid(uuid);
                userTargetDesignParentidMapper.insertSelective(userTargetDesignParentid);
                for (TargetDesign targetDesign : list) {
                    // 这里从前台传递  是个集合  TargetDesignId ClumnNameId 及对应的数据 Type  不应该做查询
                    List<TargetDesignClumnData> list1 = targetDesignClumnDataMapper.selectBytargetDesignId(targetDesign.getId());
                    for (TargetDesignClumnData targetDesignClumnData : list1) {
                        targetDesignClumnData.setTargetDesignId(targetDesign.getId()+uuid2);
                        targetDesignClumnData.setId(UUIDUtils.getUUID());
                        // FIXME 根据ClumnNameId 和 targetDesign id 确定唯一数据 设置进值
                        //targetDesignClumnData.setType();
                        targetDesignClumnData.setClumnNameId(targetDesignClumnData.getClumnNameId()+ uuid2);
                        targetDesignClumnDataMapper.insertSelective(targetDesignClumnData);
                    }
                    // 将数据copy
                    targetDesign.setTargetDesignParentId(uuid);
                    targetDesign.setId(targetDesign.getId()+uuid2);
                    targetDesign.setParentid(targetDesign.getParentid()+uuid2);
                    targetDesign.setType(targetDesign.getExtend2());
                    targetDesignMapper.insertSelective(targetDesign);
                }
                // 将对应的列  和 对应的数据保存
                // FIXME 当前登录用户id
                String userId = "2583";
                List<UserTargetDesignClumnname> clumnNamesList = userTargetDesignClumnnameMapper.selectByUseridAndTarDesParId(userId,targetDesignParentId);
                List<String> collect = clumnNamesList.stream().map(item -> item.getClumnid()).collect(Collectors.toList());

                List<TargetDesignClumnName> targetDesignClumnNames = null;
                if (collect.size()>0){
                    targetDesignClumnNames = targetDesignClumnNameMapper.selectAll(collect);
                }else{
                    targetDesignClumnNames = new ArrayList<>();
                }
                for (TargetDesignClumnName targetDesignClumnName : targetDesignClumnNames) {
                    targetDesignClumnName.setId(targetDesignClumnName.getId()+uuid2);
                    targetDesignClumnNameMapper.insertSelective(targetDesignClumnName);
                }
            }else{
                // 保存
            }
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 2;
        }
    }



    /**
     * @Author dingyf
     * @Description //指标表设计树形结构删除校验
     * @Date 2020/10/28 16:13
     * @Param [quataSearchResponse]
     * @return int
     **/
    @Override
    @Transactional
    public int targetDesignParentOrTargetDesignDelete(QuataSearchResponse quataSearchResponse) {
        try {
            String targetDesignParentId = quataSearchResponse.getTargetDesignParentId();
            // 根据DesignParentId删除
            if (Strings.isNotBlank(targetDesignParentId)){
                // 根据父id查询子数据(递归查询)
                List<TargetDesignParent> list = targetDesignParentMapper.seletByParentId(targetDesignParentId);
                // 循环删除
                for (TargetDesignParent targetDesignParent : list) {
                    //删除
                    targetDesignParentMapper.deleteByPrimaryKey(targetDesignParent.getId());
                    //用户删除
                    userTargetDesignClumnnameMapper.deleteByTargetDesignParentid(targetDesignParent.getId());
                    List<TargetDesign> targetDesignDataByTargetDesignParentId = targetDesignMapper.getTargetDesignDataByTargetDesignParentId(targetDesignParent.getId());
                    for (TargetDesign targetDesign : targetDesignDataByTargetDesignParentId) {
                        // 删除
                        targetDesignClumnDataMapper.deleteByTargetDesignId(targetDesign.getId());
                        //删除
                    }
                    //删除
                    targetDesignMapper.deleteByTargetDesignParentid(targetDesignParent.getId());
                    //删除
                    userTargetDesignParentidMapper.deleteByTargetDesignParentid(targetDesignParent.getId());
                }
            }else{
                // 根据DesignId删除
                String targetDesignId = quataSearchResponse.getTargetDesignId();
                // 根据父id查询子数据(递归查询)
                List<TargetDesign> list = targetDesignMapper.seletByParentId(targetDesignId);
                for (TargetDesign targetDesign : list) {
                    // 删除
                    targetDesignClumnDataMapper.deleteByTargetDesignId(targetDesign.getId());
                }
            }
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 2;
        }

    }
}
