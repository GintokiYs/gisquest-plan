package com.gisquest.plan.service.service.businessClassify;

import com.gisquest.plan.service.model.District.District;
import com.gisquest.plan.service.model.TargetDesignParent.TargetDesignParent;
import com.gisquest.plan.service.vo.ResponseResult;
import com.gisquest.plan.service.vo.quata.*;

import java.util.List;
import java.util.Map;

/**
 * @author honght
 * @date 2020/9/28 10:32
 */
public interface BusinessClassifyService {

    List<TargetResponse> getTargetById(String resourceParentid);

    Map<String, Object> getTargetList(QuataSearchResponse quataSearchResponse);

    ResponseResult downloadTargetList(QuataSearchResponse quataSearchResponse);

    List<District> getAreaList();

    List<Map<String, Object>>  getTargetDesignParentTree();

    int addTargetDesignParentTree(QuataSearchResponse quataSearchResponse);
}
