package com.gisquest.plan.service.service.businessClassify;

import com.gisquest.plan.service.model.District.District;
import com.gisquest.plan.service.model.TargetDesignParent.TargetDesignParent;
import com.gisquest.plan.service.model.targetDesign.TargetDesign;
import com.gisquest.plan.service.vo.ResponseResult;
import com.gisquest.plan.service.vo.quata.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author honght
 * @date 2020/9/28 10:32
 */
public interface BusinessClassifyService {

    List<TargetResponse> getTargetById(String resourceParentid);

    Map<String, Object> getTargetList(QuataSearchResponse quataSearchResponse);

    Map<String, Object> getTargetListBySource(QuataSearchResponse quataSearchResponse);

    ResponseResult downloadTargetList(QuataSearchResponse quataSearchResponse);

    List<District> getAreaList();

    List<Map<String, Object>>  getTargetDesignParentTree();

    int addTargetDesignParentTree(QuataSearchResponse quataSearchResponse);

    List<Map<String, Object>> getTargetDesignDataByTargetDesignParentId(QuataSearchResponse quataSearchResponse);

    int addTargetDesignTree(QuataSearchResponse quataSearchResponse);

    Map<String, Set<Object>>  getTargetDesignAddColumnDropDownBoxData();

    List<targetClassifyResponse>  addTargetDesignAddColumn(QuataSearchResponse quataSearchResponse);
    // 查询指标表设计生成EXCEL并下载
    ResponseResult downloadTargetDesignList(QuataSearchResponse quataSearchResponse);

    List<Map<String, Object>> getTargetDesignAddIndicatorParentTree();

    int addTargetDesignSaveOrSaveAs(QuataSearchResponse quataSearchResponse);

    int targetDesignParentOrTargetDesignDelete(QuataSearchResponse quataSearchResponse);
}
