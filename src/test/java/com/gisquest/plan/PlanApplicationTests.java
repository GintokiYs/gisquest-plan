package com.gisquest.plan;

import com.gisquest.plan.service.service.search.SearchService;
import com.gisquest.plan.service.vo.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.gisquest.plan.service.utils.TransformUtil.frontCompWithZore;

@SpringBootTest
class PlanApplicationTests {
    @Autowired
    private SearchService searchService;

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

}
