package com.gisquest.plan.service.controller.search;

import com.gisquest.plan.service.service.search.SearchService;
import com.gisquest.plan.service.vo.ResponseResult;
import com.gisquest.plan.service.vo.quata.QuataDataVo;
import com.gisquest.plan.service.vo.quata.QuataSearchVo;
import com.gisquest.plan.service.vo.quata.QuataVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：yeyh
 * @date ：Created in 2020/9/27 15:14
 * @description：
 * @modified By：
 */
@Api(tags = "查询", description = "查询接口")
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;


    /*@ApiOperation("查询所有可选主题")
    @GetMapping("/topics")
    public ResponseResult searchTopics() {
        List<String> topicList = searchService.searchTopics();
        ResponseResult<List<String>> ok = ResponseResult.ok();
        ok.setData(topicList);
        return ok;
    }*/

    @ApiOperation("根据输入框内容搜索符合模糊匹配规则的指标")
    @GetMapping("/fuzzy")
    public ResponseResult fuzzySearch(@RequestParam(required = false) String searchContent) {
        return searchService.fuzzySearch(searchContent);
    }

    @ApiOperation("根据所选主题展示一级指标，二级指标")
    @GetMapping("/byTopic/{topicId}")
    public ResponseResult searchByTopic(@PathVariable String topicId) {
        return searchService.searchByTopic(topicId);
    }

    @ApiOperation("根据指标获取 筛选条件的数值")
    @GetMapping("/condition")
    public ResponseResult searchCondition(@RequestParam("quataIdList") List<String> quataIdList, @RequestParam("fieldName") String fieldName) {
        //todo 创建工具类可以对fieldName 做匹配转换
        return searchService.searchCondition(quataIdList, fieldName);
    }

    /*@ApiOperation("根据选择的指标列表，年份，区域等条件查询指标明细")
    @GetMapping("/searchQuataDetail")
    public ResponseResult searchQuataDetail(@RequestParam("quataSearchVo") List<QuataSearchVo> quataSearchVoList) {
        List<QuataDataVo> quataDataVoList = searchService.searchQuataDetail(quataSearchVoList);
        ResponseResult<List<QuataDataVo>> ok = ResponseResult.ok();
        ok.setData(quataDataVoList);
        return ok;
    }*/
}