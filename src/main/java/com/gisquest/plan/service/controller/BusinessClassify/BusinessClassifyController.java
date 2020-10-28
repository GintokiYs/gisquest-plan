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

    @ApiOperation("不同源数据指标查询根据来源")
    @PostMapping("/getTargetListBySource")
    public ResponseResult getTargetListBySource(@RequestBody QuataSearchResponse quataSearchResponse) {
        return ResponseResult.ok(businessClassifyService.getTargetListBySource(quataSearchResponse));
    }

    @ApiOperation("查询指标表设计生成EXCEL并下载")
    @PostMapping("/downloadTargetDesignList")
    public ResponseResult downloadTargetDesignList(@RequestBody QuataSearchResponse quataSearchResponse) {
        return businessClassifyService.downloadTargetDesignList(quataSearchResponse);
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
    @ApiOperation("指标表设计树形结构添加")
    @PostMapping("/addTargetDesignParentTree")
    public ResponseResult addTargetDesignParentTree(@RequestBody QuataSearchResponse quataSearchResponse) {
        int i = businessClassifyService.addTargetDesignParentTree(quataSearchResponse);
        if (i == 1){
            return ResponseResult.ok(200,"成功");
        }else{
            return ResponseResult.error("指标表设计树形结构添加插入失败");
        }
    }
    @ApiOperation("指标表设计树形结构删除")
    @PostMapping("/targetDesignParentOrTargetDesignDelete")
    public ResponseResult targetDesignParentOrTargetDesignDelete(@RequestBody QuataSearchResponse quataSearchResponse) {
        int i = businessClassifyService.targetDesignParentOrTargetDesignDelete(quataSearchResponse);
        if (i == 1){
            return ResponseResult.ok(200,"删除成功");
        }else{
            return ResponseResult.error("删除失败");
        }
    }

    @ApiOperation("指标表设计树形结构")
    @GetMapping("/getTargetDesignParentTree")
    public ResponseResult getTargetDesignParentTree() {
        return ResponseResult.ok(businessClassifyService.getTargetDesignParentTree());
    }
    @ApiOperation("指标表设计器新增指标树形结构获取")
    @GetMapping("/getTargetDesignAddIndicatorParentTree")
    public ResponseResult getTargetDesignAddIndicatorParentTree() {
        return ResponseResult.ok(businessClassifyService.getTargetDesignAddIndicatorParentTree());
    }
    @ApiOperation("根据指标设计id获取相关联数据")
    @PostMapping ("/getTargetDesignDataByTargetDesignParentId")
    public ResponseResult getTargetDesignDataByTargetDesignParentId(@RequestBody QuataSearchResponse quataSearchResponse) {
        return ResponseResult.ok(businessClassifyService.getTargetDesignDataByTargetDesignParentId(quataSearchResponse));
    }
    @ApiOperation("指标表设计树形结构添加子")
    @PostMapping("/addTargetDesignTree")
    public ResponseResult addTargetDesignTree(@RequestBody QuataSearchResponse quataSearchResponse) {
        int i = businessClassifyService.addTargetDesignTree(quataSearchResponse);
        if (i == 1){
            return ResponseResult.ok(200,"成功");
        }else{
            return ResponseResult.error("指标表设计树形结构添加插入失败");
        }
    }
    @ApiOperation("指标表设计添加列下拉框数据获取")
    @GetMapping("/getTargetDesignAddColumnDropDownBoxData")
    public ResponseResult getTargetDesignAddColumnDropDownBoxData() {
        return ResponseResult.ok(businessClassifyService.getTargetDesignAddColumnDropDownBoxData());
    }

    @ApiOperation("指标表设计添加列")
    @PostMapping("/addTargetDesignAddColumn")
    public ResponseResult addTargetDesignAddColumn(@RequestBody QuataSearchResponse quataSearchResponse) {
        return ResponseResult.ok(businessClassifyService.addTargetDesignAddColumn(quataSearchResponse));
    }
    @ApiOperation("指标表设计保存或者另存为")
    @PostMapping("/addTargetDesignSaveOrSaveAs")
    public ResponseResult addTargetDesignSaveOrSaveAs(@RequestBody QuataSearchResponse quataSearchResponse) {
        int i = businessClassifyService.addTargetDesignSaveOrSaveAs(quataSearchResponse);
        if (i == 1){
            return ResponseResult.ok(200,"成功");
        }else{
            return ResponseResult.error("指标表设计保存或者另存为失败");
        }
    }
}
