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
    void contextLoads() {
    }

    @Test
    void searchConditionTest() {
        List<String> quataIdList = new ArrayList<>();
        quataIdList.add("1");
        quataIdList.add("2");
        String fieldName = "area";
        ResponseResult responseResult = searchService.searchCondition(quataIdList, fieldName);
        System.out.println(responseResult.getData());
    }
@Test
void frontZeroComp(){
    String s = frontCompWithZore(123, 6);
    System.out.println(s);
}

}
