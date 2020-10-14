package com.gisquest.plan;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.gisquest.plan.service.service.businessClassify.BusinessClassifyService;
import com.gisquest.plan.service.service.search.SearchService;
import com.gisquest.plan.service.utils.JsonMapper2;
import com.gisquest.plan.service.vo.Coding;
import com.gisquest.plan.service.vo.CodingListVo;
import com.gisquest.plan.service.vo.ResponseResult;
import com.gisquest.plan.service.vo.quata.QuataResponse;
import com.gisquest.plan.service.vo.quata.QuataSearchResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.ServletOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.gisquest.plan.service.utils.TransformUtil.frontCompWithZore;

@SpringBootTest
class PlanApplicationTests {
    @Autowired
    private SearchService searchService;

    @Autowired
    private BusinessClassifyService businessClassifyService;

    @Test
    void fuzzySearchTest() {
        ResponseResult result = searchService.fuzzySearch("电");
        System.out.println(result.getData());
    }

    @Test
    void searchByTopicTest() {
        ResponseResult result = searchService.searchByTopic("3");
        System.out.println(result.getData());
    }

    @Test
    void searchConditionTest() {
        List<String> quataIdList = new ArrayList<>();
        quataIdList.add("1");
        quataIdList.add("2");
        quataIdList.add("3");
        quataIdList.add("4");
        String fieldName = "year";
        ResponseResult responseResult = searchService.searchCondition(quataIdList, fieldName);
        System.out.println(responseResult.getData());
        fieldName = "area";
        ResponseResult responseResult2 = searchService.searchCondition(quataIdList, fieldName);
        System.out.println(responseResult2.getData());
    }

    @Test
    void frontZeroComp() {
        String s = frontCompWithZore(123, 6);
        System.out.println(s);
    }

    @Test
    void codingTest() {
        CodingListVo codingListVo = new CodingListVo();
        Coding coding1 = new Coding("330000", "浙江省", "1");
        Coding coding2 = new Coding("330300", "温州市", "2");
        Coding coding31 = new Coding("330381", "瑞安县", "3");
        Coding coding32 = new Coding("330382", "苍南县", "3");
        ArrayList<Coding> codingList0 = new ArrayList<>();
        ArrayList<Coding> codingList1 = new ArrayList<>();
        ArrayList<Coding> codingList2 = new ArrayList<>();
        codingList2.add(coding31);
        codingList2.add(coding32);
        coding2.setCodings(codingList2);
        codingList1.add(coding2);
        coding1.setCodings(codingList1);
        codingList0.add(coding1);
        codingListVo.setCodinglist(codingList0);
        System.out.println(coding1);
    }

    @Test
    void execlDown() {
        String quataSearchResponseJson = "{\"area\":\"\",\"areaList\":[330381],\"ave\":\"\",\"collectList\":[],\"collectYear\":0,\"countryCode\":\"\",\"dataSource\":\"\",\"dataSourcetList\":[],\"median\":\"\",\"provinceCode\":\"\",\"quataIdList\":[\"173\"],\"tableName\":\"\",\"year\":\"\",\"yearList\":[\"2016\",\"2017\"]}";
        QuataSearchResponse quataSearchResponse = (QuataSearchResponse) JsonMapper2.fromJsonString(quataSearchResponseJson, QuataSearchResponse.class);
        Map<String, Object> result = businessClassifyService.getTargetList(quataSearchResponse);
        List<QuataResponse> responses = (List<QuataResponse>) result.get("topic");
        List<QuataResponse> avgList = (List<QuataResponse>) result.get("avg");
        List<QuataResponse> medianList = (List<QuataResponse>) result.get("median");
        responses.addAll(avgList);
        responses.addAll(medianList);

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeBeanTest2.xlsx");
        //自定义标题别名
        writer.addHeaderAlias("trage", "姓名");
        writer.addHeaderAlias("area", "年龄");
        writer.addHeaderAlias("year", "分数");
        writer.addHeaderAlias("source", "是否通过");
        writer.addHeaderAlias("sourceYear", "考试时间");
        writer.addHeaderAlias("target", "考试时间");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(responses, true);
        // 关闭writer，释放内存
        writer.close();
    }

    @Test
    void execlDown2() {

    }
}
