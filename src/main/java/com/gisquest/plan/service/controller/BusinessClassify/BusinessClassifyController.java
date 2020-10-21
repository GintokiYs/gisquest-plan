package com.gisquest.plan.service.controller.BusinessClassify;

import com.gisquest.plan.service.service.businessClassify.BusinessClassifyService;
import com.gisquest.plan.service.vo.ResponseResult;
import com.gisquest.plan.service.vo.quata.QuataSearchResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author honght
 * @date 2020/9/28 10:31
 */
@Api(tags = "指标", description = "指标接口")
@RestController
@RequestMapping("/businessClassify")
public class BusinessClassifyController {

    @Autowired
    BusinessClassifyService businessClassifyService;

    /*@ApiOperation("根据主题id获取二级和三级指标")
    @GetMapping("/getTargetById")
    public ResponseResult getTargetById(String resourceParentid) {
        return ResponseResult.ok(businessClassifyService.getTargetById(resourceParentid));
    }*/

    @ApiOperation("查询指标列表")
    @PostMapping("/getTargetList")
    public ResponseResult getTargetList(@RequestBody QuataSearchResponse quataSearchResponse) {
        return ResponseResult.ok(businessClassifyService.getTargetList(quataSearchResponse));
    }

    @ApiOperation("查询指标生成EXCEL并下载")
    @PostMapping("/downloadTargetList")
    public ResponseResult downloadTargetList(@RequestBody QuataSearchResponse quataSearchResponse) {
        return businessClassifyService.downloadTargetList(quataSearchResponse);
    }
    @ApiOperation("指标表设计区域数据获取")
    @GetMapping("/getAreaList")
    public ResponseResult getAreaList() {
        return ResponseResult.ok(businessClassifyService.getAreaList());
    }
    @ApiOperation("指标表设计树形结构")
    @GetMapping("/TargetDesignParentTree")
    public ResponseResult getTargetDesignParentTree() {
        return ResponseResult.ok(businessClassifyService.getTargetDesignParentTree());
    }
}
