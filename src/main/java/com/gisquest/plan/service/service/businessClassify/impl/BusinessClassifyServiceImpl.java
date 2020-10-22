package com.gisquest.plan.service.service.businessClassify.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.fasterxml.jackson.databind.ser.std.IterableSerializer;
import com.gisquest.plan.service.dao.*;
import com.gisquest.plan.service.model.District.District;
import com.gisquest.plan.service.model.TargetDesignParent.TargetDesignParent;
import com.gisquest.plan.service.model.targetClassify.TargetClassify;
import com.gisquest.plan.service.model.targetDesign.TargetDesign;
import com.gisquest.plan.service.service.businessClassify.BusinessClassifyService;
import com.gisquest.plan.service.utils.TargetDesignParentTreeUtil;
import com.gisquest.plan.service.utils.TargetDesignTreeDataUtil;
import com.gisquest.plan.service.utils.TransformUtil;
import com.gisquest.plan.service.utils.UUIDUtils;
import com.gisquest.plan.service.vo.ResponseResult;
import com.gisquest.plan.service.vo.quata.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
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
    TargetClassifyMapper targetClassifyMapper;

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
        List<TargetDesignParent> list = targetDesignParentMapper.seletAll();
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
        TargetDesignParent targetDesignParent = new TargetDesignParent();
        targetDesignParent.setId(UUIDUtils.getUUID());
        targetDesignParent.setType(quataSearchResponse.getName());
        targetDesignParent.setExtend1(quataSearchResponse.getFileOrDir());
        targetDesignParent.setParentid(quataSearchResponse.getPid());
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
        //根据指标表设计父id查询数据
        List<TargetDesign> list = targetDesignMapper.getTargetDesignDataByTargetDesignParentId(targetDesignParentId);
        return TargetDesignTreeDataUtil.getTree(list);
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
            //文件 ,从指标直面选择数据添加 target_classify的id  看前端怎么传递 应该是个集合
            List<String> targetClassifyIds = quataSearchResponse.getTargetClassifyIds();
            for (String targetClassifyId : targetClassifyIds) {
                targetDesign.setTargetParentid(targetClassifyId);
                targetDesignMapper.insertSelective(targetDesign);
            }
            return 1;

        }
    }
}
