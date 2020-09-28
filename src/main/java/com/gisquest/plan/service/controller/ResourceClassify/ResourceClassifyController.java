package com.gisquest.plan.service.controller.ResourceClassify;

import com.gisquest.plan.service.service.resourceClassify.ResourceClassifyService;
import com.gisquest.plan.service.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author honght
 * @date 2020/9/28 9:24
 */

@Api(tags = "主题", description = "主题接口")
@RestController
@RequestMapping("/resourceClassify")
public class ResourceClassifyController {

    @Autowired
    ResourceClassifyService resourceClassifyService;

    @ApiOperation("获取主题")
    @GetMapping("/getAllTopic")
    public ResponseResult getAllTopic() {
        return ResponseResult.ok(resourceClassifyService.getAllTopic());
    }
}
